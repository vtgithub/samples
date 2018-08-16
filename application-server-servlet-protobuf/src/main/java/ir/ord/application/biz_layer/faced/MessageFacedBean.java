package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.MessageBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.MessageDto;
import ir.ord.application.services.ResponseObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vahid on 11/26/17.
 */
@Stateless
public class MessageFacedBean {
    @EJB
    private MessageBiz messageBiz;

    public ResponseObject getHomePageMessageList() {
        ResponseObject responseObject = new ResponseObject();
        try{
            List<MessageDto> messageDtoList = messageBiz.getHomePageMessageList();
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(messageDtoList);
            return responseObject;

        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getHomePageMessageList", e);
            return responseObject;
        }
    }
}
