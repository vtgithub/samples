package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.CustomValidationException;
import ir.ord.application.biz_layer.biz.Giftbiz;
import ir.ord.application.biz_layer.biz.NoAccountFoundException;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.GiftDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vahid on 5/3/17.
 */
@Stateless
public class GiftFacedBean {
    
    @EJB
    private Giftbiz giftbiz;

    public ResponseObject addGift(GiftDto giftDto) {
        ResponseObject responseObject = new ResponseObject();
        try {
            String chargeCode = giftbiz.addGift(giftDto);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            Map<String, String> data = new HashMap<String, String>();
            data.put("chargeCode", chargeCode);
            responseObject.setData(data);
            responseObject.setMessage(ResponseMessages.addGiftInfoComplete);
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("addGift:",e );
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("addGift:",e );
            return responseObject;
        }
    }
}
