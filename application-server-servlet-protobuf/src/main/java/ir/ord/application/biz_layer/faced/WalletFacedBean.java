package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.NotAllowedUserException;
import ir.ord.application.biz_layer.biz.UsedGiftException;
import ir.ord.application.biz_layer.biz.WalletBiz;
import ir.ord.application.biz_layer.biz.CustomValidationException;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.CreditDto;
import ir.ord.application.dto.GiftDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vahid on 5/2/17.
 */
@Stateless
public class WalletFacedBean {

    @EJB
    private WalletBiz walletBiz;

    public ResponseObject chargeWallet(String sessionId, Double amount) {
        ResponseObject responseObject = new ResponseObject();
        try {
            walletBiz.chargeWallet(sessionId, amount);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.walletChargeComplete);
            return responseObject;
        }catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("chargeWallet:",e );
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("chargeWallet:",e );
            return responseObject;
        }

    }

    public ResponseObject walletReport(String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<CreditDto> creditDtoList = walletBiz.walletReport(sessionId);
            responseObject.setData(Helper.getDictionaryFromList(creditDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        }catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("walletReport:",e );
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("walletReport:",e );
            return responseObject;
        }
    }

    public ResponseObject giftChargeWallet(String sessionId, String code) {
        ResponseObject responseObject = new ResponseObject();
        try {
            walletBiz.giftChargeWallet(sessionId, code);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.walletChargeComplete);
            return responseObject;

        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("giftChargeWallet:",e );
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("giftChargeWallet:",e );
            return responseObject;
        }
    }

    public ResponseObject getGiftList(String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try{
            List<GiftDto> giftDtoList = walletBiz.getUsedGiftList(sessionId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(Helper.getDictionaryFromList(giftDtoList));
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getGiftList:",e );
            return responseObject;
        }
    }
}
