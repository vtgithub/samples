package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.GlobalFacedBean;
import ir.ord.application.dto.protoes.OrderProto;
import ir.ord.application.dto.protoes.ParameterProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 10/2/17.
 */
@Path("/rest/global")
public class GlobalService {

    @EJB
    private GlobalFacedBean globalFacedBean;

    @Path("/rescheduleReason")
    @GET
    public Response getRescheduleReasonList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = globalFacedBean.getRescheduleReasonList();
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ParameterProto.ComboElementList.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/global/rescheduleReason",e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/cancelReason")
    @GET
    public Response getCancelReasonList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = globalFacedBean.getCancelReasonList();
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ParameterProto.ComboElementList.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/global/cancelReason", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/orderFeedback")
    @GET
    public Response getOrderFeedback(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = globalFacedBean.getOrderFeedBack();
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.OrderComboElemets.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/global/orderFeedback",e);
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
