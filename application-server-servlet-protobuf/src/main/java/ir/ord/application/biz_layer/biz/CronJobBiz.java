package ir.ord.application.biz_layer.biz;

import ir.ord.application.*;
import ir.ord.application.Convertor.StatusObjectConvertor;
import ir.ord.application.accessories.*;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 5/10/17.
 */
@Singleton
public class CronJobBiz {

    @Inject
    private OrderDao orderDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private UnpaiedDao unpaiedDao;
    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private StatusObjectConvertor statusObjectConvertor;
    @EJB
    private OrderBiz orderBiz;

    @Schedule( minute = "*/1", hour = "*", persistent = false)
    public void checkUnpaiedOrders(){
        try {
            List<CallableRow> callableRowList = new ArrayList<CallableRow>();
            List<Runnable> notifRunnableList = new ArrayList<Runnable>();

            List<UnpaiedEntity> unpaiedEntityList = unpaiedDao.getAll();
//            Map<String, String> statusMap = new HashMap<String, String>();
            for (final UnpaiedEntity unpaiedEntity:unpaiedEntityList){
                final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(unpaiedEntity.getAccountId());
                boolean isCreditLow = unpaiedEntity.getPrice() > accountInfoEntity.getBalance();
                long remindTime = Helper.getCurrentTime() - unpaiedEntity.getTimeStamp();
                boolean isTimeOver = (Helper.getCurrentTime() - unpaiedEntity.getTimeStamp()) > Helper.getOrderExpirationMiliSeconds();

                if ( isCreditLow && (!isTimeOver) ){
                    long passedTime = Helper.getCurrentTime() - unpaiedEntity.getTimeStamp();
                    long remainededTime = Helper.getOrderExpirationMiliSeconds() - passedTime;
                    short remainedHour = (short) (remainededTime / Helper.getOneHourMiliSeconds());
                    short remainedMinutes = (short) ((remainededTime % Helper.getOneHourMiliSeconds())/Helper.getOneMinuteMilliseconds());
                    if (remainedHour == 0 &&  remainedMinutes == 59) {
                        Helper.Notification.sendPureSMS(
                                accountInfoEntity.getPhoneNumber(),
                                NotificationMessages.getCreditLowMessage(
                                        unpaiedEntity.getPackageName(), unpaiedEntity.getOrderId(), (int) remainedHour +1)
                        );
//                        if (sendSmsResponse == null || sendSmsResponse.getStatus()!=0 || sendSmsResponse.getMsgIdArray()<100)
//                            statusMap.put(unpaiedEntity.getOrderId(), FollowUpState.FOLLOW_SMS_SENDING_FAILED.toString());
//                        else
//                            statusMap.put(unpaiedEntity.getOrderId(),FollowUpState.FOLLOW_SMS_SENT.toString());

                        Helper.Notification.sendNotif(
                                NotificationMessages.creditLowTitle,
                                "",
                                NotificationMessages.getCreditLowMessage(unpaiedEntity.getPackageName(), unpaiedEntity.getOrderId (),(int) remainedHour +1),
                                sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id())
                        );
                    }


                }else if ( isCreditLow && isTimeOver ){

                    callableRowList.add(new CallableRow<UnpaiedEntity>(
                            OperationEnum.DELETE.getCode(),
                            unpaiedEntity.get_id(),
                            unpaiedEntity,
                            new Callable<Void>() {
                                public Void call() throws Exception {
                                    unpaiedDao.removeById(unpaiedEntity.get_id());
                                    return null;
                                }
                            }
                    ));
                    final OrderEntity orderEntity = orderDao.getById(unpaiedEntity.getOrderId());
                    OrderEntity oldOrderEntity =
                            (OrderEntity) DaoHelper.getNewInstanceFromExisting(orderEntity, orderEntity.getClass());

                    StatusObject statusObject = new StatusObject();
                    statusObject.setState(CommodityState.CANCELED.getCode());
                    statusObject.setTimeStamp(Helper.getCurrentTime());
                    statusObject.setActor(ActorEnum.SYSTEM.getCode());
                    statusObject.setDescription(StatusMessages.orderCanceledLowCredit);
                    List<StatusObject> orderStatusObjectList = orderEntity.getStatusObjectList();
                    if (orderStatusObjectList == null )
                        orderStatusObjectList = new ArrayList<StatusObject>();
                    orderStatusObjectList.add(statusObject);
                    orderEntity.setStatusObjectList(orderStatusObjectList);
                    orderEntity.setCurrentStatus(statusObject);
                    DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());

                    Boolean isStatusChanged = orderBiz.orderChangeStatus(
                            callableRowList,
                            notifRunnableList,
                            oldOrderEntity,
                            orderEntity,
                            devicePushToken,
                            accountInfoEntity.getPhoneNumber()
                    );

                    if (!isStatusChanged)
                        Helper.appLogger.error(StatusMessages.cannotChangeStatus + "- cronJob - orderId:"+orderEntity.get_id());

                }
            }

            List<OrderEntity> orderEntityList = orderDao.getExpiredPaiedOrders();
            for (OrderEntity orderEntity : orderEntityList) {
                OrderEntity oldOrderEntity =
                        (OrderEntity) DaoHelper.getNewInstanceFromExisting(orderEntity, orderEntity.getClass());
                StatusObject newStatusObject = new StatusObject();
                newStatusObject.setState(CommodityState.WAREHOUSE_PENDING.getCode());
                newStatusObject.setTimeStamp(Helper.getCurrentTime());
                newStatusObject.setActor(ActorEnum.SYSTEM.getCode());
                newStatusObject.setDescription(StatusMessages.confirmedAfter15min);
                List<StatusObject> statusObjectList = orderEntity.getStatusObjectList();
                statusObjectList.add(newStatusObject);
                orderEntity.setCurrentStatus(newStatusObject);
                DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(orderEntity.getAccountId());
                orderBiz.orderChangeStatus(
                        callableRowList,
                        notifRunnableList,
                        oldOrderEntity,
                        orderEntity,
                        devicePushToken,
                        null
                );

            }

            Helper.callCallableList(callableRowList);
            callableRowList.clear();
            Helper.runRunnableList(notifRunnableList);
            notifRunnableList.clear();

        } catch (DaoException e) {
            Helper.appLogger.error("checkUnpaiedOrders", e);
        }
    }

}
