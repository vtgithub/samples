package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.CustomValidationException;
import ir.ord.application.biz_layer.biz.SessionBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.SessionDeactivateDto;
import ir.ord.application.dto.SessionInfoDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vahid on 9/10/17.
 */
@Stateless
public class SessionFacedBean {

    @EJB
    SessionBiz sessionBiz;

    public ResponseObject getActiveSessions(String sessionId) {
        ResponseObject responseObject = new ResponseObject();

        try {
            List<SessionInfoDto> sessionInfoDtoList = sessionBiz.getActiveSessions(sessionId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(Helper.getDictionaryFromList(sessionInfoDtoList));
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getActiveSessions:", e);
            return responseObject;
        }
    }


    public ResponseObject signOutAccount(String sessionId) {
        ResponseObject responseObject = new ResponseObject();

        try {
            sessionBiz.signOutAccount(sessionId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
//            responseObject.setMessage(ResponseMessages.alreadySignedOut);
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("signOutAccount:",e);
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("signOutAccount:",e);
            return responseObject;
        }
    }

    public ResponseObject signOutAccountList(List<SessionDeactivateDto> secondarySessionList) {
        ResponseObject responseObject = new ResponseObject();

        try {
            sessionBiz.signOutAccountList(secondarySessionList);
//            responseObject.setMessage(ResponseMessages.successfulGroupSignOut);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("signOutAccountList:",e);
            return responseObject;

        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("signOutAccountList:",e);
            return responseObject;
        }

    }
}
