package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.DeviceFacedBean;
import ir.ord.application.dto.protoes.ParameterProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 5/11/17.
 */
@Path("/rest/device")
public class DeviceService {

    @EJB
    private DeviceFacedBean deviceFacedBean;

    @Path("/notif")
    @POST
    public Response deviceNotifSend(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            ParameterProto.DeviceNotifRequest.Builder deviceNotifRequestBuilder =
                    (ParameterProto.DeviceNotifRequest.Builder) ComHelper.getBuilderFromInputParameter(
                    servletRequest.getHeader("Content-Type"),
                    ParameterProto.DeviceNotifRequest.newBuilder(),
                    input
            );
            responseObject = deviceFacedBean.sendDeviceNotif(
                    servletRequest.getHeader("sessionId"), deviceNotifRequestBuilder.getPushToken()
            );
//            Helper.appLogger.info("/device/notif >> input: "+ Helper.getJsonStr(parameter)+
//                    "\noutput: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                            )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/device/notif", e);
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
