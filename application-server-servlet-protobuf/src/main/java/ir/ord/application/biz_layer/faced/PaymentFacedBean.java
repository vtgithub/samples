package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.*;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.entities.SuccessPaymentObject;
import ir.ord.application.dal.entities.VerifyResponseObject;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

/**
 * Created by vahid on 5/8/17.
 */


@Stateless
public class PaymentFacedBean {

    @EJB
    private PaymentBiz paymentBiz;

    public ResponseObject paymentInitialize(String sessionId, Long amount, String specificOrderId) throws IOException, IllegalStatusChangeException {
        ResponseObject responseObject = new ResponseObject();
        try {
            InitializeReturnObject initializeReturnObject = paymentBiz.paymentInitialize(sessionId, amount, specificOrderId);
            responseObject.setData(initializeReturnObject);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("paymentInitialize:", e);
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("paymentInitialize:", e);
            return responseObject;
        } catch (GetTokenFromBankException e) {

            responseObject.setResponseCode(ResponseStatus.SERVICE_UNAVAILABLE.getCode());
            responseObject.setMessage(ResponseMessages.bankServiceIsUnavailable);
            Helper.appLogger.error("paymentInitialize:", e);
            return responseObject;
        } catch (EnoughBalanceAndCancelException e) {

            responseObject.setResponseCode(ResponseStatus.CONFLICT.getCode());
            responseObject.setMessage(ResponseMessages.enoughBalance‌ButCanceled);
            Helper.appLogger.error("paymentInitialize:", e);
            return responseObject;
        } catch (EnoughBalanceAndPayException e) {

            responseObject.setResponseCode(ResponseStatus.PRECONDITION_FAILED.getCode());
            responseObject.setMessage(ResponseMessages.enoughBalance);
            InitializeReturnObject initializeReturnObject = new InitializeReturnObject("/order/"+specificOrderId+"/pay", null);
            responseObject.setData(initializeReturnObject);
            Helper.appLogger.error("paymentInitialize:", e);
            return responseObject;
        }
    }

    public ResponseObject proccessRedirectedResponse(SuccessPaymentObject successPaymentObject) {
        ResponseObject responseObject = new ResponseObject();
        try {
            VerifyResponseObject verifyResponseObject = paymentBiz.proccessRedirectedResponse(successPaymentObject);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(verifyResponseObject);
            responseObject.setMessage(ResponseMessages.successTransaction);
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.dbOperationfailure);
            Helper.appLogger.error("proccessRedirectedResponseFromBank:",e );
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.PAYMENT_VERIFICATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("proccessRedirectedResponseFromBank:",e );
            return responseObject;
        } catch (PaymentVerificationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("proccessRedirectedResponseFromBank:",e );
            return responseObject;
        } catch (HasBeenVerifiedException e) {

            responseObject.setResponseCode(ResponseStatus.PAYMENT_VERIFICATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("proccessRedirectedResponseFromBank:",e );
            return responseObject;
        }
    }
}
