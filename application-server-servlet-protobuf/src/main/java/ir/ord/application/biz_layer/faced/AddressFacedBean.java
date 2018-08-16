package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.AddressBiz;
import ir.ord.application.biz_layer.biz.CustomValidationException;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.AddressDto;
import ir.ord.application.dto.ButtonDto;
import ir.ord.application.dto.TimePeriodDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vahid on 5/1/17.
 */
@Stateless
public class AddressFacedBean {

    @EJB
    private AddressBiz addressBiz;

    public ResponseObject registerAddress(AddressDto addressDto, String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            AddressDto address= addressBiz.registerAddress(addressDto, sessionId);

            responseObject.setData(address);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.addressregistrationComplete);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("registerAddress", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("registerAddress", e);
            return responseObject;
        }
    }

    public ResponseObject changeAddress(AddressDto addressDto, String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            AddressDto address = addressBiz.changeAddress(addressDto, sessionId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.addressChangeComplete);
            responseObject.setData(address);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("changeAddress", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changeAddress", e);
            return responseObject;
        }
    }

    public ResponseObject getAllAddressOfAccount(String sessionId){
        ResponseObject responseObject = new ResponseObject();
        try {
            List<AddressDto> addressDtoList = addressBiz.getAllAddressOfAccount(sessionId);
            if (addressDtoList == null || addressDtoList.size()==0)
                responseObject.setMessage(ResponseMessages.noItemFound);
            responseObject.setData(Helper.getDictionaryFromList(addressDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getAllAddressOfAccount", e);
            return responseObject;
        }

    }

    public ResponseObject changeTimePeriodList(String sessionId, String addressID, List<TimePeriodDto> timePeriodDtoList) {
        ResponseObject responseObject = new ResponseObject();

        try {
            AddressDto addressDto = addressBiz.changeTimePeriodList(sessionId, addressID, timePeriodDtoList);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(addressDto);
            responseObject.setMessage(ResponseMessages.timePeriodListChangeComplete);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("changeTimePeriodList", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changeTimePeriodList", e);
            return responseObject;
        }
    }

    public ResponseObject getAccountAddressById(String sessionId, String addressId) {
        ResponseObject responseObject = new ResponseObject();

        try {
            AddressDto addressDto = addressBiz.getAccountAddressById(sessionId, addressId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(addressDto);
            if (addressDto == null)
                responseObject.setMessage(ResponseMessages.noItemFound);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error(" getAccountAddressById:", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error(" getAccountAddressById:", e);
            return responseObject;
        }
    }

    public ResponseObject deleteAddressById(String sessionId, String addressID) {
        ResponseObject responseObject = new ResponseObject();
        try {
            addressBiz.deleteAddressById(sessionId, addressID);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.addressRemovedSuccessfully);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error(" deleteAddressById:", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error(" deleteAddressById:", e);
            return responseObject;
        }
    }

    public ResponseObject getAllAddressButtons(String sessionId, String addressID) {
        ResponseObject responseObject =new ResponseObject();

        try {
            List<ButtonDto> buttonDtoList = addressBiz.getAllAddressButtons(sessionId, addressID);
            if (buttonDtoList == null || buttonDtoList.size() ==0){
                responseObject.setMessage(ResponseMessages.noButtonFoundForTheAddress);
                responseObject.setResponseCode(ResponseStatus.NO_CONTENT.getCode());
                Helper.appLogger.info("getAllAddressButtons: no address found");
            }else{
                responseObject.setData(Helper.getDictionaryFromList(buttonDtoList));
                responseObject.setResponseCode(ResponseStatus.OK.getCode());
            }

            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error(" getAllAddressButtons:", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error(" getAllAddressButtons:", e);
            return responseObject;
        }
    }
}
