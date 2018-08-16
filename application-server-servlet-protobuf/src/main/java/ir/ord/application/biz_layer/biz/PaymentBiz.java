package ir.ord.application.biz_layer.biz;

import ir.ord.application.*;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.biz_layer.validation.PaymentValidation;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import org.apache.log4j.Logger;
import org.parboiled.common.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 5/8/17.
 */
@Stateless
public class PaymentBiz {


    @Inject
    private BankPaymentDao bankPaymentDao;
    @Inject
    private PaymentValidation paymentValidation;
    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private CountersDao countersDao;
    @Inject
    private UnpaiedDao unpaiedDao;
    @Inject
    private WalletBiz walletBiz;
    @EJB
    private OrderBiz orderBiz;
    @Inject
    private OrderDao orderDao;

    public InitializeReturnObject paymentInitialize(String sessionId, Long amount, String specificOrderId) throws
            CustomValidationException,
            DaoException,
            GetTokenFromBankException,
            IOException,
            IllegalStatusChangeException,
            EnoughBalanceAndPayException,
            EnoughBalanceAndCancelException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();

        List<String>  validationResultList = paymentValidation.paymentInitializeValidation(sessionId, amount, specificOrderId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));


        Long orderId = countersDao.getNextSequence("orderCounter");

        final BankPaymentEntity bankPaymentEntity = new BankPaymentEntity();
        if (specificOrderId != null && !specificOrderId.equals("")){
            bankPaymentEntity.setSpecificOrderId(specificOrderId);
            UnpaiedEntity unpaiedEntity = unpaiedDao.getByOrderId(specificOrderId);
            if (unpaiedEntity == null)
                throw new CustomValidationException(ValidationMessages.wrongOrderId);
            SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
            AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
            OrderEntity orderEntity = orderDao.getById(specificOrderId);
            amount = orderEntity.getPackageEntity().getPrice().longValue() - accountInfoEntity.getBalance().longValue();
            if (amount <= 0){
                if (Helper.getCurrentTime() - unpaiedEntity.getTimeStamp() < Helper.getOneDayMiliSeconds()) {
                    throw new EnoughBalanceAndPayException();
                }else {
                    orderBiz.cancelOrder(sessionId, specificOrderId, null, null);
                    throw new EnoughBalanceAndCancelException();
                }
            }
        }
        bankPaymentEntity.setTimeStamp(Helper.getCurrentTime());
        bankPaymentEntity.setAmount(amount);
        bankPaymentEntity.setOrderId(orderId);
        bankPaymentEntity.setSessionId(sessionId);

        bankPaymentEntity.setBankId(BankEnum.SADAD.getCode());

        callableRowList.add(new CallableRow<BankPaymentEntity>(
                OperationEnum.INSERT.getCode(),
                bankPaymentEntity.get_id(),
                bankPaymentEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        bankPaymentDao.save(bankPaymentEntity);
                        return null;
                    }
                }
        ));


        PaymentRequestObject paymentRequestObject = new PaymentRequestObject();
//        paymentRequestObject.setOrderId(bankPaymentEntity.get_id());
        Long rialAmount = amount * 10;
        paymentRequestObject.setAmount(Long.parseLong(rialAmount.toString()));
        paymentRequestObject.setOrderId(orderId);
        paymentRequestObject.setSignData(
                DaoHelper.getSignedRequest(
                paymentRequestObject.getTerminalId() ,
                orderId,
                String.valueOf(paymentRequestObject.getAmount())
        ));

        String requestStr = Helper.getJsonStr(paymentRequestObject);
        PaymentResponseObject paymentResponseObject = Helper.getBankResponse(paymentRequestObject);

        if (Integer.parseInt(paymentResponseObject.getResCode()) == PaymentResponseCodeEnum.SUCCESS.getCode()){
            Helper.callCallableList(callableRowList);
            InitializeReturnObject initializeReturnObject = new InitializeReturnObject(Helper.getBankPageUrI()+"?token="+paymentResponseObject.getToken(), amount);
            return initializeReturnObject;
        } else
            throw new GetTokenFromBankException();
    }


    public VerifyResponseObject proccessRedirectedResponse(SuccessPaymentObject successPaymentObject) throws DaoException, CustomValidationException, PaymentVerificationException, HasBeenVerifiedException {

        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = paymentValidation.processRedirectResponsevalidation(successPaymentObject);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        final BankPaymentEntity bankPaymentEntity = bankPaymentDao.getByOrderId(successPaymentObject.getOrderId());
        BankPaymentEntity oldBankPaymentEntity =
                (BankPaymentEntity) DaoHelper.getNewInstanceFromExisting(bankPaymentEntity, bankPaymentEntity.getClass());
        //verification
        VerifyRequestObject verifyRequestObject = new VerifyRequestObject();
        verifyRequestObject.setToken(successPaymentObject.getToken());
        verifyRequestObject.setSignData(
                DaoHelper.getVerificationSignData( successPaymentObject.getToken())
        );

        VerifyResponseObject verifyResponseObject = Helper.getBankVerificationResponse(verifyRequestObject);
        if (verifyResponseObject != null && verifyResponseObject.getResCode() == 0) {
            bankPaymentEntity.setSuccessPaymentObject(successPaymentObject);
            bankPaymentEntity.setVerifyResponseObject(verifyResponseObject);
        }
        else
            throw new PaymentVerificationException();


        callableRowList.add(new CallableRow<BankPaymentEntity>(
                OperationEnum.UPDATE.getCode(),
                oldBankPaymentEntity.get_id(),
                oldBankPaymentEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        bankPaymentDao.update(bankPaymentEntity.get_id(), bankPaymentEntity);
                        return null;
                    }
                }
        ));
        verifyResponseObject.setAmount( (verifyResponseObject.getAmount()/10) );
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(bankPaymentEntity.getSessionId());
        BankInfoDto bankInfoDto = new BankInfoDto(successPaymentObject.getToken(), bankPaymentEntity.getBankId());
        if (bankPaymentEntity.getSpecificOrderId() == null || bankPaymentEntity.getSpecificOrderId().equals("")){
            walletBiz.walletChargePlusTokenCheck(
                    sessionInfoEntity.getAccountId(),
                    verifyResponseObject.getAmount(),
                    callableRowList,
                    notifRunnableList,
                    UpdateNotifCreditEvent.CHARGE.getCode(),
                    bankInfoDto,
                    null
            );
        }else {
            walletBiz.walletChargePlusTokenCheck(
                    sessionInfoEntity.getAccountId(),
                    verifyResponseObject.getAmount(),
                    callableRowList,
                    notifRunnableList,
                    UpdateNotifCreditEvent.CHARGE.getCode(),
                    bankInfoDto,
                    bankPaymentEntity.getSpecificOrderId()
            );
        }

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);
        return verifyResponseObject;
    }
}
