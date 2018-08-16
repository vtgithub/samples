package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.services.ResponseObject;
import ir.ord.application.biz_layer.biz.*;
import ir.ord.application.dto.AccountInfoDto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vahid on 4/22/17.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AccountFacedBean {

    @EJB
    private AccountBiz accountBiz;

    public ResponseObject registerAccountInfo(AccountInfoDto accountInfoDto, String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            AccountInfoDto updatedAccountInfo = accountBiz.registerAccountInfo(accountInfoDto, sessionId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(updatedAccountInfo);
            responseObject.setMessage(ResponseMessages.infoInsertionComplete);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("registerAccountInfo", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("registerAccountInfo", e);
            return responseObject;
        }
    }

    public ResponseObject signIn(String phoneNumber, String deviceId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Integer responseCode = accountBiz.signIn(phoneNumber, deviceId);
            responseObject.setResponseCode(responseCode);

            if (responseCode == ResponseStatus.FAILED_DEPENDENCY.getCode()){
                responseObject.setMessage(ResponseMessages.sendActivationCodeViaSmsFailed);
                Helper.appLogger.info("signIn: responseCode="+ responseCode);
            }
            else if (responseCode == ResponseStatus.UNAVAILABLE_FOR_LEGAL_REASONS.getCode()) {
                responseObject.setMessage(ResponseMessages.sendActivationCodeViaSmsFailedForU);
                Helper.appLogger.info("signIn: responseCode="+ responseCode);
            }else{
                responseObject.setMessage(ResponseMessages.sendActivationCode);
                Map<String, Integer> timeOut = new HashMap<String, Integer>();
                timeOut.put("timeOut", Helper.getActivationCodeTimeOut());
                responseObject.setData(timeOut);
            }
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("signIn", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("signIn", e);
            return responseObject;
        } catch (InvalidAccountException e) {
            responseObject.setResponseCode(ResponseStatus.INVALID_ACCOUNT.getCode());
            responseObject.setMessage(ResponseMessages.notActiveAccount);
            Helper.appLogger.error("signIn", e);
            return responseObject;
        } catch (TooManyActivationCodeGenerated e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("signIn", e);
            return responseObject;
        }
    }

    public ResponseObject activate(String activationCode,
                                   String deviceId,
                                   Integer platform,
                                   String platformVersion,
                                   String appVersion,
                                   String deviceName,
                                   String deviceModel,
                                   String ip) {
        ResponseObject responseObject = new ResponseObject();
        try{
            Map<String, String> responseMap = accountBiz.activate(
                    activationCode, deviceId, platform, platformVersion, appVersion, deviceName, deviceModel, ip);
            responseObject.setMessage(ResponseMessages.activationComplete);

            if (responseMap.get("responseCode") == null || responseMap.get("responseCode").equals(""))
                responseObject.setResponseCode(ResponseStatus.OK.getCode());
            else {
                responseObject.setResponseCode(Integer.valueOf(responseMap.get("responseCode")));
                Helper.appLogger.info("activate: "+responseMap);
            }
            Map<String, String> data = new HashMap<String, String>();
            data.put("sessionId", responseMap.get("sessionId"));
            responseObject.setData(data);
            return  responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("activate", e);
            return responseObject;
        } catch (DaoException e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("activate", e);
            return responseObject;
        } catch (ActivationCodeNotFoundException e) {
            responseObject.setResponseCode(ResponseStatus.ACTIVATION_CODE_IS_USED.getCode());
            responseObject.setMessage(ResponseMessages.activationCodeIsUsed);
            Helper.appLogger.error("activate", e);
            return responseObject;
        }
    }



    public ResponseObject getAccountInfo(String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            AccountInfoDto accountInfoDtoList = accountBiz.getAccountInfo(sessionId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(accountInfoDtoList);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getAccountInfo", e);
            return responseObject;
        }
    }

    public ResponseObject changePhoneNumber(String sessionId, String phoneNumber) {
        ResponseObject responseObject = new ResponseObject();

        try {
            Integer timeOut = accountBiz.changePhoneNumber(sessionId, phoneNumber);
            if (timeOut == -1){
                responseObject.setResponseCode(ResponseStatus.FAILED_DEPENDENCY.getCode());
                responseObject.setMessage(ResponseMessages.sendActivationCodeViaSmsFailed);
                Helper.appLogger.info("changePhoneNumber:"+Helper.getJsonStr(responseObject));
            }else if (timeOut == -2){
                responseObject.setResponseCode(ResponseStatus.UNAVAILABLE_FOR_LEGAL_REASONS.getCode());
                responseObject.setMessage(ResponseMessages.sendActivationCodeViaSmsFailedForU);
                Helper.appLogger.info("changePhoneNumber:"+Helper.getJsonStr(responseObject));
            }else{
                responseObject.setResponseCode(ResponseStatus.OK.getCode());
                responseObject.setMessage(ResponseMessages.sendActivationCode);
                Map<String, Integer> timeOutMap = new HashMap<String, Integer>();
                timeOutMap.put("timeOut", timeOut);
                responseObject.setData(timeOutMap);
            }

            return responseObject;
        } catch (PhoneNumberAlreadyRegisteredException e) {
            responseObject.setResponseCode(ResponseStatus.ALREADY_REPORTED.getCode());
            responseObject.setMessage(ResponseMessages.duplicatePhoneNumber);
            Helper.appLogger.info("changePhoneNumber:"+Helper.getJsonStr(responseObject));
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.info("changePhoneNumber:"+Helper.getJsonStr(responseObject));
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.info("changePhoneNumber:"+Helper.getJsonStr(responseObject));
            return responseObject;
        }
    }

    public ResponseObject changePhoneActivation(String sessionId, String activationCode) {
        ResponseObject responseObject = new ResponseObject();
        try {
            accountBiz.changePhoneActivation(sessionId, activationCode);
            responseObject.setMessage(ResponseMessages.changePhoneNumberComplete);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("changePhoneActivation", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changePhoneActivation", e);
            return responseObject;
        } catch (ActivationCodeNotFoundException e) {
            responseObject.setResponseCode(ResponseStatus.ACTIVATION_CODE_IS_USED.getCode());
            responseObject.setMessage(ResponseMessages.activationCodeIsUsed);
            Helper.appLogger.error("changePhoneActivation", e);
            return responseObject;
        }
    }

    public ResponseObject getQRCode(String sessionId) {
        ResponseObject responseObject = new ResponseObject();

        try {
            Map<String, Object> data = accountBiz.getQRCode(sessionId);
            responseObject.setData(data);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getQRCode", e);
            return responseObject;
        }
    }

    public ResponseObject signUpWithQRCode(String deviceId,
                                           String encryptedQRCode,
                                           Integer platform,
                                           String platformVersion,
                                           String appVersion,
                                           String deviceName,
                                           String deviceModel) {
        ResponseObject responseObject = new ResponseObject();
        try {
            String sessionId = accountBiz.signUpWithQRCode(
                    deviceId, encryptedQRCode, platform, platformVersion, appVersion, deviceName, deviceModel);
            Map<String, String > data = new HashMap<String, String>();
            data.put("sessionId", sessionId);
            responseObject.setData(data);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.activationComplete);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("signUpWithQRCode", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("signUpWithQRCode", e);
            return responseObject;
        } catch (NoQRCodeException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.invalidQRCode);
            Helper.appLogger.error("signUpWithQRCode", e);
            return responseObject;
        }
    }

//    public ResponseObject getButtonList(String sessionId) {
//        ResponseObject responseObject = new ResponseObject();
//        try {
//            List<ButtonDto> buttonDtoList = accountBiz.getButtonList(sessionId);
//            responseObject.setData(Helper.getDictionaryFromList(buttonDtoList));
//            responseObject.setResponseCode(ResponseStatus.OK.getCode());
//            return responseObject;
//        } catch (DaoException e) {
//            e.printStackTrace();
//            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
//            responseObject.setMessage(ResponseMessages.operationFailed);
//            Helper.appLogger.error("getButtonList:"+e.getMessage());
//            return responseObject;
//        }
//    }
}
