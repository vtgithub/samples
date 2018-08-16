package ir.ord.management.biz_layer.biz;

import ir.ord.management.accessories.ComHelper;
import ir.ord.management.accessories.ResponseMessages;
import ir.ord.management.accessories.ResponseStatus;
import ir.ord.management.biz_layer.CustomValidationException;
import ir.ord.management.biz_layer.biz.ManagementBiz;
import ir.ord.management.dal.rpc.RemoteDaoException;
import ir.ord.management.dto.protoes.*;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by vahid on 5/6/17.
 */
@Stateless
public class ManagementFacedBean {

    @Inject
    private ManagementBiz managementBiz;


    public ResponseProto.Response.Builder addCategory(CategoryProto.CategoryRequest.Builder categoryrequestBuilder) {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try {
            managementBiz.addCategory(categoryrequestBuilder);
            responseBuilder.setResponseCode(ResponseStatus.OK.getCode());
            responseBuilder.setMessage(ResponseMessages.addCategoryComplete);
            return responseBuilder;
        } catch (CustomValidationException e) {
            responseBuilder.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseBuilder.setMessage(e.getMessage());
            ComHelper.managementLogger.error("addCategory", e);
            return responseBuilder;
        } catch (RemoteDaoException e) {
            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.DataServiceUnavailable);
            ComHelper.managementLogger.error("addCategory", e);
            return responseBuilder;
        }
    }



    public ResponseProto.Response.Builder addPackage(PackageProto.PackageRequest.Builder packageRequestBuilder) {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try {
            managementBiz.addPackage(packageRequestBuilder);
            responseBuilder.setResponseCode(ResponseStatus.OK.getCode());
            responseBuilder.setMessage(ResponseMessages.addPackageComplete);
            return responseBuilder;
        } catch (CustomValidationException e) {
            responseBuilder.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseBuilder.setMessage(e.getMessage());
            ComHelper.managementLogger.error("addPackage", e);
            return responseBuilder;
        } catch (RemoteDaoException e) {
            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.DataServiceUnavailable);
            ComHelper.managementLogger.error("addPackage", e);
            return responseBuilder;
        }
    }

    public ResponseProto.Response.Builder addTimePeriod(TimePeriodProto.TimePeriodRequest.Builder timePeriodRequestBuilder) {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try{
            managementBiz.addTimePeriod(timePeriodRequestBuilder);
            responseBuilder.setResponseCode(ResponseStatus.OK.getCode());
            responseBuilder.setMessage(ResponseMessages.addTimePeriodComplete);
            return responseBuilder;
        }catch (CustomValidationException e) {
            responseBuilder.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseBuilder.setMessage(e.getMessage());
            ComHelper.managementLogger.error("addTimePeriod", e);
            return responseBuilder;
        } catch (RemoteDaoException e) {
            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.DataServiceUnavailable);
            ComHelper.managementLogger.error("addTimePeriod", e);
            return responseBuilder;
        }
    }

    public ResponseProto.Response.Builder fillTimePeriodList(TimePeriodProto.TimePeriodFillRequest.Builder builder) {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();

        try {
            managementBiz.fillTimePeriodList(builder);
            responseBuilder.setResponseCode(ResponseStatus.OK.getCode());
            responseBuilder.setMessage(ResponseMessages.allTimePeriodsFilledSuccessfully);
            return responseBuilder;
        } catch (RemoteDaoException e) {
            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.DataServiceUnavailable);
            ComHelper.managementLogger.error("fillTimePeriodList", e);
            return responseBuilder;
        }

    }

    public ResponseProto.Response.Builder addGift(GiftProto.GiftRequest.Builder giftRequestBuilder) {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try {
            managementBiz.addGift(giftRequestBuilder);
            responseBuilder.setResponseCode(ResponseStatus.OK.getCode());
            responseBuilder.setMessage(ResponseMessages.addGiftInfoComplete);
            return responseBuilder;
        } catch (CustomValidationException e) {
            responseBuilder.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseBuilder.setMessage(e.getMessage());
            ComHelper.managementLogger.error("addGift", e);
            return responseBuilder;
        } catch (RemoteDaoException e) {
            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.DataServiceUnavailable);
            ComHelper.managementLogger.error("addGift", e);
            return responseBuilder;
        }

    }

    public ResponseProto.Response.Builder getAllGift() {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try {
            GiftProto.GiftList.Builder giftListBuilder = managementBiz.getAllGift();
            responseBuilder.setResponseCode(ResponseStatus.OK.getCode());
            responseBuilder.setData(giftListBuilder.build().toByteString());
            return responseBuilder;
        } catch (RemoteDaoException e) {
            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.DataServiceUnavailable);
            ComHelper.managementLogger.error("getAllGift", e);
            return responseBuilder;
        }
    }
}
