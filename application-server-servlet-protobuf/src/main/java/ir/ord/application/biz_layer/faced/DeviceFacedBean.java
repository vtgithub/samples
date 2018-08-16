package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.CustomValidationException;
import ir.ord.application.biz_layer.biz.DeviceBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by vahid on 5/11/17.
 */
@Stateless
public class DeviceFacedBean {

    @EJB
    private DeviceBiz deviceBiz;

    public ResponseObject sendDeviceNotif(String sessionId, String pushToken) {
        ResponseObject responseObject = new ResponseObject();

        try {
            deviceBiz.sendDeviceNotif(sessionId, pushToken);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("sendDeviceNotif:",e );
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("sendDeviceNotif:",e );
            return responseObject;
        }
    }
}
