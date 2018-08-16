package ir.ord.application.biz_layer.biz;

import ir.ord.application.*;
import ir.ord.application.Convertor.*;
import ir.ord.application.accessories.*;
import ir.ord.application.biz_layer.rpc.OrderStruct;
import ir.ord.application.biz_layer.validation.OrderValidation;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import ir.ord.application.dto.OrderDto;
import ir.ord.application.dto.OrderFeedbackDto;
import ir.ord.application.dto.OrderListItemDto;
import ir.ord.application.dto.TimePeriodDto;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 5/3/17.
 */
@Stateless
public class OrderBiz {

    @Inject
    private OrderValidation orderValidation;
    @Inject
    private OrderDao orderDao;
    @Inject
    private OrderConvertor orderConvertor;
    @Inject
    private TimePeriodConvertor timePeriodConvertor;
    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private UnpaiedDao unpaiedDao;
    @Inject
    private CreditDao creditDao;
    @Inject
    private WalletConvertor walletConvertor;
    @Inject
    private StatusObjectConvertor statusObjectConvertor;
    @Inject
    private WalletBiz walletBiz;
    @Inject
    private TimePeriodBiz timePeriodBiz;
    @Inject
    private FeedBackConverter feedBackConverter;

    public OrderDto getOrderById(String orderId) throws CustomValidationException,DaoException, IOException {
        List<String> validationResultList = orderValidation.getOrderByIdValidation(orderId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        //todo check by sessionId
        OrderEntity orderEntity = orderDao.getById(orderId);
        OrderDto orderDto = orderConvertor.getDto(orderEntity);
        return orderDto;
    }

    public OrderDto cancelOrder(String sessionId, String orderId, Integer cancelReasonId, String cancelReasonVal)
            throws CustomValidationException, DaoException, IOException, IllegalStatusChangeException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = orderValidation.cancelOrderValidation(orderId);
        if(validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        final SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());

        final OrderEntity orderEntity = orderDao.getById(orderId);
        List<StatusObject> statusObjectList = orderEntity.getStatusObjectList();

        OrderEntity oldOrderEntity =
                (OrderEntity) DaoHelper.getNewInstanceFromExisting(orderEntity, orderEntity.getClass());
        StatusObject statusObject = new StatusObject();
        statusObject.setTimeStamp(Helper.getCurrentTime());
        statusObject.setDescription(StatusMessages.orderCanceledByUser);
        statusObject.setState(CommodityState.CANCELED.getCode());
        statusObject.setActor(ActorEnum.USER.getCode());
//        orderEntity.setCurrentStatus(statusObject);

//        if (statusObjectList == null)
//            statusObjectList = new ArrayList<StatusObject>();
        statusObjectList.add(statusObject);
        orderEntity.setStatusObjectList(statusObjectList);
        orderEntity.setCurrentStatus(statusObject);
        OptionObject cancelReason = new OptionObject(cancelReasonId, cancelReasonVal);
        orderEntity.setCancelReason(cancelReason);
        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(sessionInfoEntity.getAccountId());

        Boolean isChanged = orderChangeStatus(
                callableRowList,
                notifRunnableList,
                oldOrderEntity,
                orderEntity,
                devicePushToken,
                accountInfoEntity.getPhoneNumber()
        );

        if (!isChanged)
            throw new IllegalStatusChangeException();

        unpaiedDao.removeByOrderId(orderEntity.get_id());

        CreditEntity creditEntity = creditDao.getByAccountIdAndOrderId(sessionInfoEntity.getAccountId(), orderId);

        if (creditEntity != null){
            final CreditEntity newCreditEntity = getNewEntity(creditEntity);
            walletBiz.insertCreditRecord(
                    callableRowList,
                    notifRunnableList,
                    newCreditEntity.getAmount() * -1,
                    newCreditEntity.getBalance() - newCreditEntity.getAmount(),
                    newCreditEntity.getAccountId(),
                    newCreditEntity.getDescription(),
                    null,
                    newCreditEntity.getOrderId(),
                    newCreditEntity.getPayType(),
                    devicePushToken
            );

        }

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);

        OrderDto orderDto = orderConvertor.getDto(orderEntity);
        return orderDto;
    }

    public List<OrderListItemDto> getOrderList(String sessionId) throws DaoException, CustomValidationException, IOException {
        String orderListValidation = OrderValidation.getOrderListValidation(sessionId);
        if (orderListValidation != null)
            throw new CustomValidationException(orderListValidation);
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        List<OrderEntity> orderEntityList = orderDao.getListByAccountId(sessionInfoEntity.getAccountId());
        List<OrderListItemDto> orderListItemDtos = orderConvertor.getListItemDtoList(orderEntityList);

        return orderListItemDtos;
    }

    public OrderDto changeOrderTime(String orderId, Long deliveryRequestTime, Integer fromTime, Integer toTime, Integer rescheduleReasonId, String rescheduleReasonVal) throws CustomValidationException, DaoException, IOException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();

        List<String> validationResultList = orderValidation.changeOrderTimeValidation(orderId, deliveryRequestTime, fromTime, toTime, rescheduleReasonId, rescheduleReasonVal);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        final OrderEntity orderEntity = orderDao.getById(orderId);
        OrderEntity oldOrderEntity = (OrderEntity) DaoHelper.getNewInstanceFromExisting(orderEntity, orderEntity.getClass());

        List<DeliveryEstimateObject> deliveryEstimateObjectList = orderEntity.getDeliveryEstimateObjectList();
        if (!orderValidation.validRescheduleState(orderEntity.getCurrentStatus()))
            throw new CustomValidationException(ValidationMessages.changeTimePeriodIsUnavailable);
        if (orderValidation.rescheduledByUser(orderEntity.getDeliveryEstimateObjectList()))
            throw new CustomValidationException(ValidationMessages.changeTimePeriodOneTimeAllowed);
        if (deliveryEstimateObjectList == null)
            deliveryEstimateObjectList = new ArrayList<DeliveryEstimateObject>();

        DeliveryEstimateObject deliveryEstimateObject = new DeliveryEstimateObject();
        deliveryEstimateObject.setActor(ActorEnum.USER.getCode());
        deliveryEstimateObject.setTimeStamp(Helper.getCurrentTime());
        deliveryEstimateObject.setDeliveryTimeMin(deliveryRequestTime + fromTime*Helper.getOneHourMiliSeconds());
        deliveryEstimateObject.setDeliveryTimeMax(deliveryRequestTime + toTime*Helper.getOneHourMiliSeconds());

        deliveryEstimateObjectList.add(deliveryEstimateObject);

        orderEntity.setCurrentDeliveryEstimate(deliveryEstimateObject);
        orderEntity.setDeliveryEstimateObjectList(deliveryEstimateObjectList);

        List<StatusObject> statusObjectList = orderEntity.getStatusObjectList();

        StatusObject statusObject = new StatusObject();
        statusObject.setActor(ActorEnum.USER.getCode());
        statusObject.setDescription(StatusMessages.timePeriodChangeByUser);
        statusObject.setState(statusObjectList.get(statusObjectList.size()-1).getState());
        statusObject.setTimeStamp(Helper.getCurrentTime());
        statusObjectList.add(statusObject);
        orderEntity.setStatusObjectList(statusObjectList);
        orderEntity.setCurrentStatus(statusObject);

        OptionObject optionObject = new OptionObject(rescheduleReasonId, rescheduleReasonVal);
        orderEntity.setRescheduleReason(optionObject);

        callableRowList.add(new CallableRow<OrderEntity>(
                OperationEnum.UPDATE.getCode(),
                oldOrderEntity.get_id(),
                oldOrderEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        orderDao.update(orderEntity.get_id(), orderEntity);
                        return null;
                    }
                }
        ));

        Helper.callCallableList(callableRowList);
        OrderDto orderDto = orderConvertor.getDto(orderEntity);
        return orderDto;
    }


    public long getOrderCount(String sessionId) throws CustomValidationException, DaoException {
        String orderListValidation = OrderValidation.getOrderListValidation(sessionId);
        if (orderListValidation != null)
            throw new CustomValidationException(orderListValidation);
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        long orderCount  = orderDao.getOrderCountByAccountId(sessionInfoEntity.getAccountId());
        return orderCount;
    }


    public List<OrderStruct> getWarehousePendingOrderStructList() throws DaoException {
        List<OrderEntity> orderWarehousePendingList = orderDao.getWarehousePendingList();
        List<OrderStruct> orderStructList = orderConvertor.getOrderStructList(orderWarehousePendingList);
        return orderStructList;
    }



    public List<OrderListItemDto> getUnresolvedOrderList(String sessionId) throws IOException, DaoException {
        List<Integer> orderList =  new ArrayList<Integer>();
        orderList.add(CommodityState.REQUESTED.getCode());
        orderList.add(CommodityState.PAID.getCode());
        orderList.add(CommodityState.USER_CONFIRMED.getCode());
        orderList.add(CommodityState.WAREHOUSE_PENDING.getCode());
        orderList.add(CommodityState.WAREHOUSE_PROCESSING.getCode());
        orderList.add(CommodityState.WAREHOUSE_CONFIRMED.getCode());
        orderList.add(CommodityState.LOGISTIC_PENDING.getCode());
        orderList.add(CommodityState.SENT.getCode());
        List<OrderListItemDto> unresolvedOrderList = getSpecificStatusesOrderList(sessionId, orderList);
        return unresolvedOrderList;
    }



//-----------------helpers


    private CreditEntity getNewEntity(CreditEntity creditEntity) {
        CreditEntity newCreditEntity = new CreditEntity();
        newCreditEntity.setAccountId(creditEntity.getAccountId());
        newCreditEntity.setOrderId(creditEntity.getOrderId());
        newCreditEntity.setPayDate(creditEntity.getPayDate());
        newCreditEntity.setAmount(creditEntity.getAmount());
        newCreditEntity.setBalance(creditEntity.getBalance());
        newCreditEntity.setPayType(creditEntity.getPayType());
        newCreditEntity.setDescription(creditEntity.getDescription());
        return newCreditEntity;
    }

    public OrderDto changeOrderStatus(String orderId, String status) throws CustomValidationException, DaoException, IOException, IllegalStatusChangeException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResult = orderValidation.changeOrderStatusValidation(orderId, status);
        if (validationResult.size()>0)
            throw new CustomValidationException(StringUtils.join(validationResult, ", "));
        OrderEntity orderEntity = orderDao.getById(orderId);
        if (orderEntity == null){
            validationResult.add(ValidationMessages.wrongOrderId);
            throw new CustomValidationException(StringUtils.join(validationResult, ", "));
        }

        Boolean isChanged = false;
        if (status.equals("pay")) {

            Double newBalance= creditDao.getBalanceSum(orderEntity.getAccountId()) -orderEntity.getPackageEntity().getPrice() ;
            DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(orderEntity.getAccountId());

            walletBiz.insertCreditRecord(
                    callableRowList,
                    notifRunnableList,
                    -1*orderEntity.getPackageEntity().getPrice(),
                    newBalance,
                    orderEntity.getAccountId(),
                    orderEntity.getPackageEntity().getName(),
                    null,
                    orderEntity.get_id(),
                    UpdateNotifCreditEvent.ORDER.getCode(),
                    devicePushToken
            );
            walletBiz.updateUserBalance(newBalance, orderEntity.getAccountId(), callableRowList);

            unpaiedDao.removeByOrderId(orderId);

            OrderEntity oldOrderEntity = (OrderEntity) DaoHelper.getNewInstanceFromExisting(orderEntity, orderEntity.getClass());


            StatusObject newStatusObject = new StatusObject();
            newStatusObject.setActor(ActorEnum.USER.getCode());
            newStatusObject.setState(CommodityState.PAID.getCode());
            newStatusObject.setTimeStamp(Helper.getCurrentTime());
            newStatusObject.setDescription(StatusMessages.manualSingleOrder);

            List<StatusObject> statusObjectList = orderEntity.getStatusObjectList();
            statusObjectList.add(newStatusObject);
            orderEntity.setStatusObjectList(statusObjectList);
            orderEntity.setCurrentStatus(newStatusObject);
            isChanged = orderChangeStatus(callableRowList, notifRunnableList, oldOrderEntity, orderEntity, devicePushToken, null);

        }


        if (isChanged){
            Helper.callCallableList(callableRowList);
            Helper.runRunnableList(notifRunnableList);
            OrderDto orderDto = orderConvertor.getDto(orderEntity);
            return orderDto;
        }else
            throw new IllegalStatusChangeException();
    }



    public void cleanAllOrders() throws DaoException {
        orderDao.dropEntity();
        unpaiedDao.dropEntity();
    }


    public List<TimePeriodDto> getChangeTimePeriodList(String orderId) throws CustomValidationException, DaoException {
        List<String> validationResult = orderValidation.getchangeTimePeriodList(orderId);
        if (validationResult.size() > 0)
            throw new CustomValidationException(StringUtils.join(validationResult, ", "));
        OrderEntity orderEntity = orderDao.getById(orderId);
        if (orderEntity == null)
            throw new CustomValidationException(ValidationMessages.wrongOrderId);
        if (!orderValidation.validRescheduleState(orderEntity.getCurrentStatus()))
            throw new CustomValidationException(ValidationMessages.changeTimePeriodIsUnavailable);
        if (orderValidation.rescheduledByUser(orderEntity.getDeliveryEstimateObjectList()))
            throw new CustomValidationException(ValidationMessages.changeTimePeriodOneTimeAllowed);

        List<TimePeriodDto> timePeriodList = timePeriodBiz.getCustomTimePeriodList(
                Helper.getCurrentTime()/*todo remove tis comment currentStatus.getTimeStamp()*/ + Helper.ChangeTime.getBaseTimeAppend(),
                Helper.ChangeTime.getFutureDays(),
                orderEntity.getCurrentDeliveryEstimate().getDeliveryTimeMin()
        );
        return timePeriodList;
    }


    public Boolean orderChangeStatus(
            List<CallableRow> callableRowList,
            List<Runnable> notifRunnableList,
            OrderEntity oldOrderEntity,
            final OrderEntity orderEntity,
            final DevicePushToken devicePushToken,
            final String phoneNumber) {

        if(!isValidChangeStatus(oldOrderEntity.getCurrentStatus().getState() , orderEntity.getCurrentStatus().getState())){
                return false;
        }


        callableRowList.add(new CallableRow<OrderEntity>(
                OperationEnum.UPDATE.getCode(),
                orderEntity.get_id(),
                oldOrderEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        orderDao.update(orderEntity.get_id(), orderEntity);
                        return null;
                    }
                }
        ));


        notifRunnableList.add(new Runnable() {
            public void run() {
                Helper.Notification.sendContentUpdate(
                        UpdateNotifType.ORDER.getCode(),
                        UpdateNotifOrderEvent.STATUS_UPDATE.getCode(),
                        UpdateNotifPriority.HIGHT.getCode(),
                        orderEntity.get_id(),
                        devicePushToken
                );
            }
        });


        if (orderEntity.getCurrentStatus().getState() == CommodityState.CANCELED.getCode() && phoneNumber != null){

            notifRunnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendPureSMS(
                            phoneNumber,
                            NotificationMessages.getOrderCanceledMessage(orderEntity.getPackageEntity().getName(), orderEntity.get_id())
                    );
                }
            });
            notifRunnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendNotif(
                            NotificationMessages.orderCanceledTitle,
                            "",
                            NotificationMessages.getOrderCanceledMessage(orderEntity.getPackageEntity().getName(), orderEntity.get_id()),
                            devicePushToken
                    );
                }
            });
        }
        return true;
    }



    public void orderInsert(List<CallableRow> callableList,
                            List<Runnable> notifRunnableList,
                            final OrderEntity orderEntity,
                            final DevicePushToken devicePushToken) {

        callableList.add(new CallableRow<OrderEntity>(
                OperationEnum.INSERT.getCode(),
                orderEntity.get_id(),
                orderEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        orderDao.save(orderEntity);
                        return null;
                    }
                }
        ));
        notifRunnableList.add(new Runnable() {
            public void run() {
                Helper.Notification.sendContentUpdate(
                        UpdateNotifType.ORDER.getCode(),
                        UpdateNotifOrderEvent.SUCCESS_ORDER.getCode(),
                        UpdateNotifPriority.HIGHT.getCode(),
                        null,
                        devicePushToken
                );
            }
        });
    }

    public Double getNeededAmount(String orderId, String sessionId) throws DaoException, CustomValidationException {
        List<String> validationResult = orderValidation.getNeededAmmountValidation(orderId);
        if (validationResult.size()>0)
            throw new CustomValidationException(StringUtils.join(validationResult, ", "));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        Double balanceSum = creditDao.getBalanceSum(sessionInfoEntity.getAccountId());
        OrderEntity orderEntity = orderDao.getById(orderId);
        return orderEntity.getPackageEntity().getPrice() - balanceSum;
    }



    private boolean isValidChangeStatus(Integer state1, Integer state2) {
        if ( (state1 > CommodityState.WAREHOUSE_PENDING.getCode() || state1 == CommodityState.CANCELED.getCode()) &&
                state2 == CommodityState.CANCELED.getCode())
            return false;
        if ( (state1 == CommodityState.WAREHOUSE_PENDING.getCode() && state2 != CommodityState.WAREHOUSE_PROCESSING.getCode()) ||
                state1!=CommodityState.WAREHOUSE_PENDING.getCode() && state2 == CommodityState.WAREHOUSE_PROCESSING.getCode())//warehouse service change status
            return false;

        return true;
    }


    public boolean changeOrderStatusToWarehouseProcessing(String orderId) throws DaoException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> runnableList = new ArrayList<Runnable>();
        OrderEntity orderEntity = orderDao.getById(orderId);
        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(orderEntity.getAccountId());
        OrderEntity oldOrderEntity =
                (OrderEntity) DaoHelper.getNewInstanceFromExisting(orderEntity, orderEntity.getClass());
        StatusObject statusObject = new StatusObject();
        statusObject.setState(CommodityState.WAREHOUSE_PROCESSING.getCode());
        statusObject.setActor(ActorEnum.SYSTEM.getCode());
        statusObject.setTimeStamp(Helper.getCurrentTime());
        statusObject.setDescription(StatusMessages.warehouseChangeStatus);
        List<StatusObject> statusObjectList = orderEntity.getStatusObjectList();
        statusObjectList.add(statusObject);
        orderEntity.setCurrentStatus(statusObject);
        orderEntity.setStatusObjectList(statusObjectList);
        Boolean result = this.orderChangeStatus(
                callableRowList,
                runnableList,
                oldOrderEntity,
                orderEntity,
                devicePushToken,
                null
        );
        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(runnableList);
        return result;
    }

    public void submitOrderFeedback(String orderId, OrderFeedbackDto orderFeedbackDto) throws CustomValidationException, DaoException {
        List<String> validationResult = orderValidation.getOrderFeedbackValidation(orderId, orderFeedbackDto);
        if (validationResult.size() > 0)
            throw new CustomValidationException(StringUtils.join(validationResult , ", "));
        OrderEntity orderEntity = orderDao.getById(orderId);
        if (orderEntity.getCurrentStatus().getState() != CommodityState.DELIVERED.getCode())
            throw new CustomValidationException(ValidationMessages.feedbackFailed);
        if (orderEntity.getDeliveryFeedBack() != null || orderEntity.getPackingFeedBack() != null || orderEntity.getProductFeedBack() != null)
            throw new CustomValidationException(ValidationMessages.feedbackHasBeenReceived);
        orderEntity.setDeliveryFeedBack(feedBackConverter.getEntity(orderFeedbackDto.getDeliveryFeedback()));
        orderEntity.setPackingFeedBack(feedBackConverter.getEntity(orderFeedbackDto.getPackingFeedback()));
        orderEntity.setProductFeedBack(feedBackConverter.getEntity(orderFeedbackDto.getProductFeedback()));
        orderDao.update(orderEntity.get_id(), orderEntity);
    }


    private List<OrderListItemDto> getSpecificStatusesOrderList(String sessionId, List<Integer> statusList) throws DaoException, IOException {
//        List<String> resultList = orderValidation.orderStatusListValidation(statusList);
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        List<OrderEntity> orderEntityList = orderDao.getListByAccountId(sessionInfoEntity.getAccountId());

        Iterator<OrderEntity> orderEntityIterator= orderEntityList.iterator();
        while (orderEntityIterator.hasNext()){
            if (statusList.contains(orderEntityIterator.next().getCurrentStatus().getState()))
                orderEntityIterator.remove();
        }
        List<OrderListItemDto> orderListItemDtoList = orderConvertor.getListItemDtoList(orderEntityList);
        return orderListItemDtoList;

    }

}
