package ir.ord.application.biz_layer.biz;

import ir.ord.application.*;
import ir.ord.application.Convertor.BankInfoConvertor;
import ir.ord.application.Convertor.GiftConvertor;
import ir.ord.application.Convertor.StatusObjectConvertor;
import ir.ord.application.Convertor.WalletConvertor;
import ir.ord.application.accessories.*;
import ir.ord.application.biz_layer.validation.WalletValidation;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import ir.ord.application.dto.CreditDto;
import ir.ord.application.dto.GiftDto;
import org.apache.log4j.Logger;
import org.parboiled.common.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.Collections;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 5/2/17.
 */
@Stateless
@Transactional
public class WalletBiz {

    @Inject
    private CreditDao creditDao;
    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private GiftDao giftDao;
    @Inject
    private OrderDao orderDao;
    @Inject
    private UnpaiedDao unpaiedDao;
    @Inject
    private WalletValidation walletValidation;
    @Inject
    private WalletConvertor walletConvertor;
    @Inject
    private StatusObjectConvertor statusObjectConvertor;
    @Inject
    private BankInfoConvertor bankInfoConvertor;
    @Inject
    private GiftConvertor giftConvertor;
    @EJB
    private OrderBiz orderBiz;
    @EJB
    private ButtonBiz buttonBiz;

    public void chargeWallet(String sessionId, Double amount) throws DaoException, CustomValidationException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = walletValidation.chargeWalletvalidation(sessionId, amount);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        // todo redirect to bank payment api with Helper function
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        walletChargeHelper(
                sessionInfoEntity.getAccountId(),
                amount,
                callableRowList,
                notifRunnableList,
                UpdateNotifCreditEvent.CHARGE.getCode(),
                null,
                null,
                null,
                null
        );
        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);
    }


    public List<CreditDto> walletReport(String sessionId) throws CustomValidationException, DaoException {
        List<String> validationresultList = walletValidation.walletReportValidation(sessionId);
        if (validationresultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationresultList, ", "));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        List<CreditEntity> creditEntityList = creditDao.getListByAccountId(sessionInfoEntity.getAccountId());
        List<CreditDto> creditDtoList = walletConvertor.getCreditDtoList(creditEntityList);
        return creditDtoList;
    }


    public void giftChargeWallet(String sessionId, String code) throws CustomValidationException, DaoException {
        List<CallableRow> callableRowList =  new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultlist = walletValidation.giftChargeWalletValidation(sessionId, code);
        if (validationResultlist.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultlist, ", "));

        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);

        final GiftEntity giftEntity = giftDao.getByCode(code);
        if (giftEntity == null)
            throw new CustomValidationException(ValidationMessages.giftCodeNotValid);
        if (giftEntity.getUsedTime()!= null && giftEntity.getUsedTime()!=0)
            throw new CustomValidationException(ValidationMessages.giftCodeHasBeenUsed);
        GiftEntity oldGiftEntity = (GiftEntity) DaoHelper.getNewInstanceFromExisting(giftEntity, giftEntity.getClass());

        if ((giftEntity == null) || (giftEntity.getExpirationTime()<Helper.getCurrentTime()))
            throw new CustomValidationException(ValidationMessages.giftExpired);

        if (
            (giftEntity.getAccountIdList().contains(sessionInfoEntity.getAccountId()) && !giftEntity.getIncludeOrExclude()) ||
            (!giftEntity.getAccountIdList().contains(sessionInfoEntity.getAccountId()) && giftEntity.getIncludeOrExclude() )
           )
            throw new CustomValidationException(ValidationMessages.notAllowedUserForGift);

        giftEntity.setUsedTime(Helper.getCurrentTime());
        giftEntity.setUserId(sessionInfoEntity.getAccountId());
        callableRowList.add(new CallableRow<GiftEntity>(
                OperationEnum.UPDATE.getCode(),
                giftEntity.get_id(),
                oldGiftEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        giftDao.update(giftEntity.get_id(), giftEntity);
                        return null;
                    }
                }
        ));
        walletChargeHelper(
                sessionInfoEntity.getAccountId(),
                giftEntity.getValue(),
                callableRowList,
                notifRunnableList,
                UpdateNotifCreditEvent.CHARGE_GIFT.getCode(),
                null,
                giftEntity.get_id(),
                null,
                code
        );
//        Double newBalance = creditDao.getBalanceSum(sessionInfoEntity.getAccountId()) + Double.valueOf(giftEntity.getValue());
//        final CreditEntity creditEntity = new CreditEntity();
//        creditEntity.setAmount(giftEntity.getValue());
//        creditEntity.setAccountId(sessionInfoEntity.getAccountId());
//        creditEntity.setPayDate(Helper.getCurrentTime());
//        creditEntity.setInfo(giftEntity);
//        creditEntity.setBalance(newBalance);
//        creditEntity.setDescription(NotificationMessages.walletChargeViaGift);
//        creditEntity.setPayType(Integer.valueOf(UpdateNotifCreditEvent.CHARGE_GIFT.getCode()));
//
//        callableRowList.add(new CallableRow<CreditEntity>(
//                OperationEnum.INSERT.getCode(),
//                creditEntity.get_id(),
//                creditEntity,
//                new Callable<Void>() {
//                    public Void call() throws Exception {
//                        creditDao.save(creditEntity);
//                        return null;
//                    }
//                }
//        ));
//        // credit update notif
//        final Set<String> pushTokenList = sessionInfoDao.getPushTokenList(sessionInfoEntity.getAccountId());
//        notifRunnableList.add(new Runnable() {
//            public void run() {
//
//                Helper.Notification.sendContentUpdate(
//                        UpdateNotifType.CREDIT.getCode(),
//                        UpdateNotifCreditEvent.ORDER_CANCELATION.getCode(),
//                        UpdateNotifPriority.HIGHT.getCode(),
//                        null,
//                        pushTokenList
//                );
//            }
//        });
//
//
//        final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
//        AccountInfoEntity oldAccountInfoEntity =
//                (AccountInfoEntity) DaoHelper.getNewInstanceFromExisting(accountInfoEntity, accountInfoEntity.getClass());
//        accountInfoEntity.setBalance(newBalance);
//
//        callableRowList.add(new CallableRow<AccountInfoEntity>(
//                OperationEnum.UPDATE.getCode(),
//                accountInfoEntity.get_id(),
//                oldAccountInfoEntity,
//                new Callable<Void>() {
//                    public Void call() throws Exception {
//                        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);
//                        return null;
//                    }
//                }
//        ));

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);
    }
    //-------------- helper functions

    public void walletChargePlusTokenCheck(String accountId,
                                           Double amount,
                                           List<CallableRow> callableRowList,
                                           List<Runnable> notifRunnableList,
                                           final Integer creditEvent,
                                           BankInfoDto bankInfoDto,
                                           String specificOrderId) throws DaoException, HasBeenVerifiedException {
        CreditEntity creditEntity = creditDao.getByAccountIdAndBankToken(accountId, bankInfoDto.getToken());
        if (creditEntity != null){
            throw new HasBeenVerifiedException(ResponseMessages.hasBeenVerified);
        }
        walletChargeHelper(
                accountId,
                amount,
                callableRowList,
                notifRunnableList,
                creditEvent,
                bankInfoDto,
                null,
                specificOrderId,
                null
        );

    }

    public void walletChargeHelper(
            String accountId,
            final Double amount,
            List<CallableRow> callableRowList,
            List<Runnable> notifRunnableList,
            final Integer creditEvent,
            final BankInfoDto bankInfoDto,
            String orderId,
            String specificOrderId,
            final String chargeCode) throws DaoException {
        Double previousBalance = creditDao.getBalanceSum(accountId);

        final Double newBalance = ((previousBalance == null)?amount:(previousBalance+amount));
        final DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountId);

        insertCreditRecord(
                callableRowList,
                notifRunnableList,
                amount,
                newBalance,
                accountId,
                NotificationMessages.walletCharge,
                bankInfoDto,
                orderId,
                creditEvent,
                devicePushToken
        );

        updateUserBalance(newBalance, accountId, callableRowList);

        if (creditEvent == UpdateNotifCreditEvent.CHARGE.getCode()){
            final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(accountId);
            notifRunnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendPureSMS(
                            accountInfoEntity.getPhoneNumber(),
                            NotificationMessages.getBankChargeSMSMessage(amount, bankInfoDto.getBankId(), newBalance)
                    );
                }
            });

            notifRunnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendNotif(
                            NotificationMessages.walletCharge,
                            "",
                            NotificationMessages.getBankChargeNotificationMessage(amount, bankInfoDto.getBankId()),
                            devicePushToken
                    );
                }
            });


        } else if (creditEvent == UpdateNotifCreditEvent.CHARGE_GIFT.getCode()){
            final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(accountId);
            notifRunnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendPureSMS(
                            accountInfoEntity.getPhoneNumber(),
                            NotificationMessages.getGiftChargeSMSMessage(amount, chargeCode,newBalance)
                    );
                }
            });

            notifRunnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendNotif(
                            NotificationMessages.walletCharge,
                            "",
                            NotificationMessages.getGiftChargeNotificationMessage(amount),
                            devicePushToken
                    );
                }
            });
        }





//        final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(accountId);
//        AccountInfoEntity oldAcountInfoEntity =
//                (AccountInfoEntity) DaoHelper.getNewInstanceFromExisting(accountInfoEntity, accountInfoEntity.getClass());
//        accountInfoEntity.setBalance(newBalance);
//
//        callableRowList.add(new CallableRow<AccountInfoEntity>(
//                OperationEnum.UPDATE.getCode(),
//                oldAcountInfoEntity.get_id(),
//                oldAcountInfoEntity,
//                new Callable<Void>() {
//                    public Void call() throws Exception {
//                        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);
//                        return null;
//                    }
//                }
//        ));

        unpaiedOrdersCheck(accountId, newBalance, specificOrderId, callableRowList, notifRunnableList);

    }


    // -----------for unpaied orders
    private void unpaiedOrdersCheck(String accountId,
                                    Double balance,
                                    String specificOrderId,
                                    List<CallableRow> callableRowList,
                                    List<Runnable> notifRunnableList) throws DaoException {
        List<UnpaiedEntity> unpaiedEntityList = unpaiedDao.getByAccountId(accountId);


        if (unpaiedEntityList == null || unpaiedEntityList.size() == 0){
            return;
        }

        unpaiedEntityList = sortUnpaiedsByDate(unpaiedEntityList);
        if (specificOrderId!=null)
            swapSpecificOrder(unpaiedEntityList, specificOrderId, 0);

        for (final UnpaiedEntity unpaiedEntity: unpaiedEntityList){
            if (balance >= unpaiedEntity.getPrice()) {
                balance = balance - unpaiedEntity.getPrice();
                DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountId);
                insertCreditRecord(
                        callableRowList,
                        notifRunnableList,
                        unpaiedEntity.getPrice() * -1,
                        balance,
                        accountId,
                        unpaiedEntity.getPackageName(),
                        null,
                        unpaiedEntity.getOrderId(),
                        UpdateNotifCreditEvent.ORDER.getCode(),
                        devicePushToken
                );

                Boolean isChangedStatus = changeOrderStatusAndSetDeliveryEstimate(unpaiedEntity.getOrderId(), callableRowList, notifRunnableList);
                if (!isChangedStatus)
                    Helper.appLogger.error(StatusMessages.cannotChangeStatus +"- unpaidOrderCheck- orderId:"+unpaiedEntity.getOrderId());
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
            }
        }
        updateUserBalance(balance, accountId, callableRowList);

    }


    public void updateUserBalance(Double balance, String accountId, List<CallableRow> callableRowList) throws DaoException {
        final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(accountId);
        AccountInfoEntity oldAccountInfoEntity =
                (AccountInfoEntity) DaoHelper.getNewInstanceFromExisting(accountInfoEntity, accountInfoEntity.getClass());
        accountInfoEntity.setBalance(balance);

        callableRowList.add(new CallableRow<AccountInfoEntity>(
                OperationEnum.UPDATE.getCode(),
                oldAccountInfoEntity.get_id(),
                oldAccountInfoEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);
                        return null;
                    }
                }
        ));

    }

    private Boolean changeOrderStatusAndSetDeliveryEstimate(String orderId, List<CallableRow> callableRowList, List<Runnable> notifRunnableList) throws DaoException {
        final OrderEntity orderEntity = orderDao.getById(orderId);
        OrderEntity oldOrderEntity =
                (OrderEntity) DaoHelper.getNewInstanceFromExisting(orderEntity, orderEntity.getClass());

        if (orderEntity == null)
            return false;
        List<StatusObject> statusObjectList = orderEntity.getStatusObjectList();
        if (statusObjectList == null)
            statusObjectList = new ArrayList<StatusObject>();
        StatusObject statusObject = new StatusObject();
        statusObject.setDescription(StatusMessages.orderChangeStatusAfterCharge);
        statusObject.setActor(ActorEnum.SYSTEM.getCode());
        statusObject.setTimeStamp(Helper.getCurrentTime());
        statusObject.setState(CommodityState.PAID.getCode());
        statusObjectList.add(statusObject);
        orderEntity.setStatusObjectList(statusObjectList);
        orderEntity.setCurrentStatus(statusObject);

        List<DeliveryEstimateObject> deliveryEstimateObjectList = orderEntity.getDeliveryEstimateObjectList();
        if (deliveryEstimateObjectList == null)
            deliveryEstimateObjectList = new ArrayList<DeliveryEstimateObject>();
        DeliveryEstimateObject deliveryEstimateObject = buttonBiz.getDeliveryEstimateObject(orderEntity.getAddressObject().getTimePeriodEntityList());
        deliveryEstimateObjectList.add(deliveryEstimateObject);
        orderEntity.setDeliveryEstimateObjectList(deliveryEstimateObjectList);
        orderEntity.setCurrentDeliveryEstimate(deliveryEstimateObject);

        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(orderEntity.getAccountId());
        Boolean isChanged = orderBiz.orderChangeStatus(
                callableRowList,
                notifRunnableList,
                oldOrderEntity,
                orderEntity,
                devicePushToken,
                null
        );
        return isChanged;
    }

    public void insertCreditRecord(List<CallableRow> callableRowList,
                                   List<Runnable> notifRunnableList,
                                   final Double amount,
                                   final Double newBalance,
                                   String accountId,
                                   String description,
                                   final BankInfoDto bankInfoDto,
                                   String orderId,
                                   Integer payType,
                                   final DevicePushToken devicePushToken) throws DaoException {
        final CreditEntity creditEntity = new CreditEntity();
        creditEntity.setAccountId(accountId);
        creditEntity.setPayDate(Helper.getCurrentTime());
        creditEntity.setAmount(amount);
        creditEntity.setDescription(description);
        creditEntity.setBalance(newBalance);
        if (bankInfoDto != null)
            creditEntity.setBankInfoObject(bankInfoConvertor.getObject(bankInfoDto));
        if (orderId != null)
            creditEntity.setOrderId(orderId);
        creditEntity.setPayType(payType);

        callableRowList.add(new CallableRow<CreditEntity>(
                OperationEnum.INSERT.getCode(),
                creditEntity.get_id(),
                creditEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        creditDao.save(creditEntity);
                        return null;
                    }
                }
        ));
        // credit update notif
        notifRunnableList.add(new Runnable() {
            public void run() {

                Helper.Notification.sendContentUpdate(
                        UpdateNotifType.CREDIT.getCode(),
                        UpdateNotifCreditEvent.ORDER.getCode(),
                        UpdateNotifPriority.HIGHT.getCode(),
                        null,
                        devicePushToken
                );
            }
        });
    }

    public List<GiftDto> getUsedGiftList(String sessionId) throws DaoException {
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        List<GiftEntity> giftEntityList = giftDao.getByAccountId(sessionInfoEntity.getAccountId());
        List<GiftDto> giftDtoList = giftConvertor.getDtoList(giftEntityList);
        return giftDtoList;
    }

    //---------- hepler
    private List<UnpaiedEntity> sortUnpaiedsByDate(List<UnpaiedEntity> unpaiedEntityList) {
        Collections.sort(unpaiedEntityList, new Comparator<UnpaiedEntity>() {
            public int compare(UnpaiedEntity unpaiedEntity, UnpaiedEntity t1) {
                return unpaiedEntity.getTimeStamp().compareTo(t1.getTimeStamp());
            }
        });
        return unpaiedEntityList;
    }


    private void swapSpecificOrder(List<UnpaiedEntity> unpaiedEntityList, String specificOrderId, int index) {
        for (int i=0; i<unpaiedEntityList.size(); i++){
            if (unpaiedEntityList.get(i).getOrderId().equals(specificOrderId)){
                Collections.swap(unpaiedEntityList, 0, i);
            }
        }
    }

}
