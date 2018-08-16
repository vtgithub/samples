package ir.ord.application.services;

import ir.ord.application.Convertor.GiftConvertor;
import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.GiftFacedBean;
import ir.ord.application.dto.GiftDto;
import ir.ord.application.dto.protoes.GiftProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 5/3/17.
 */
@Path("/rest/gift")
public class GiftService {

    @EJB
    private GiftFacedBean giftFacedBean;
    @Inject
    private GiftConvertor giftConvertor;

    @Path("/")
    @POST
    public Response addGift(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            GiftProto.GiftRequest.Builder giftRequestBuilder =
                    (GiftProto.GiftRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            GiftProto.GiftRequest.newBuilder(),
                            input
                    );

            GiftDto giftDto = giftConvertor.getDtoFromBuilder(giftRequestBuilder);
            responseObject = giftFacedBean.addGift(giftDto);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            GiftProto.GiftCharge.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/gift",e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


}
