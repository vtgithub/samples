package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.ButtonBiz;
import ir.ord.application.biz_layer.biz.CustomValidationException;
import ir.ord.application.biz_layer.biz.NotEnoghMonyException;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.ButtonDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vahid on 5/1/17.
 */
@Stateless
public class ButtonFacedBean {


    @EJB
    private ButtonBiz buttonBiz;

    public ResponseObject buyButton(String appVersion,
                                    String appVersionCode,
                                    String devicePlatform,
                                    String deviceSdk,
                                    String deviceName,
                                    String page,
                                    String lat,
                                    String lng,
                                    String addressId,
                                    String sessionId,
                                    String packageId,
                                    Map<Integer, String> buttonFunctionalityMap) {
        ResponseObject responseObject = new ResponseObject();
        try {
            buttonBiz.buyButton(
                    addressId,
                    sessionId,
                    packageId,
                    appVersion,
                    appVersionCode,
                    devicePlatform,
                    deviceSdk,
                    deviceName,
                    page,
                    lat,
                    lng,
                    buttonFunctionalityMap
            );
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.buttonHasBeenBought);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("buyButton", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("buyButton", e);
            return responseObject;
        } catch (NotEnoghMonyException e) {
            responseObject.setResponseCode(ResponseStatus.PAYMENT_REQUIRED.getCode());
            responseObject.setMessage(ResponseMessages.notEnoughtMoney);
            Helper.appLogger.error("buyButton", e);
            return responseObject;
        }catch (Exception e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("buyButton", e);
            return responseObject;
        }

    }


    public ResponseObject getActivationCode(String name, String sessionId, String deviceTocken,
                                    String ipAddress, Map<Integer, String > functionalityMap) {

        ResponseObject responseObject = new ResponseObject();
        try {
            String activationCode = buttonBiz.getActivationCode(name, sessionId, deviceTocken, ipAddress, functionalityMap);
            Map<String, String> data = new HashMap<String, String>();
            data.put("activationCode", activationCode);
            responseObject.setData(data);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getActivationCode", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getActivationCode", e);
            return responseObject;
        }
    }

    public ResponseObject activateButton(String deviceToken, String activationCode) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Map<Integer, String> funcNumberFuncKeyMap = buttonBiz.activateButton(deviceToken, activationCode);
            responseObject.setData(funcNumberFuncKeyMap);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            Helper.buttonLogger.error("activateButton", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            Helper.buttonLogger.error("activateButton", e);
            return responseObject;
        }
    }

    public int proccessButtonRequest(
            String deviceToken,
            String deviceVersion,
            String appId,
            String reqKey,
            String ip,
            byte[] body) {
        try {
            buttonBiz.proccessButtonRequest(deviceToken, deviceVersion, appId, reqKey, ip, body);
            return ResponseStatus.OK.getCode();
        } catch (CustomValidationException e) {
            Helper.buttonLogger.error("proccessButtonRequest", e);
            return ResponseStatus.VALIDATION_ERROR.getCode();
        } catch (DaoException e) {
            Helper.buttonLogger.error("proccessButtonRequest", e);
            return ResponseStatus.DATABASE_EXCEPTION.getCode();
        } catch (NotEnoghMonyException e) {
            Helper.buttonLogger.error("proccessButtonRequest", e);
            return ResponseStatus.OK.getCode();
        }
    }


    public ResponseObject getFunctionalityMap(String deviceToken) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Map<Integer, String> functionalityMap = buttonBiz.getFunctionalityMap(deviceToken);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(Helper.getJsonStr(functionalityMap));
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getFunctionalityMap:",e );
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getFunctionalityMap:",e );
            return responseObject;
        }
    }


    public ResponseObject getButtonList(String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<ButtonDto>  buttonDtoList = buttonBiz.getButtonList(sessionId);
            responseObject.setData(Helper.getDictionaryFromList(buttonDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        }  catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getButtonList:",e );
            return responseObject;
        }

    }
}
