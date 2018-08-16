package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.TimePeriodFacedBean;
import ir.ord.application.dto.protoes.TimePeriodProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 6/28/17.
 */
@Path("/rest/timePeriod")
public class TimePeriodService {

    @EJB
    private TimePeriodFacedBean timePeriodFacedBean;

    @Path("/")
    @GET
    public Response getTimePeriodList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = timePeriodFacedBean.getTimePeriodList();
//            managementLogger.info("/management/timePeriod >> output: "+ Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            TimePeriodProto.TimePeriodList.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/timePeriod", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


//    @Path("/")
//    @POST
//    public Response getCustomTimePeriodList(@Context HttpServletRequest servletRequest, byte[] input){
//        ResponseObject responseObject = new ResponseObject();
//        try{
//            TimePeriodProto.CustomTimePeriodRequest.Builder ctprb =
//                    (TimePeriodProto.CustomTimePeriodRequest.Builder) ComHelper.getBuilderFromInputParameter(
//                    servletRequest.getHeader("Content-Type"),
//                    TimePeriodProto.CustomTimePeriodRequest.newBuilder(),
//                    input
//            );
//            responseObject = timePeriodFacedBean.getCustomTimePeriodList(ctprb.getBaseTime(), ctprb.getFutureDays());
//            return Response.status(ResponseStatus.OK.getCode()).entity(
//                    ComHelper.produceOutput(
//                            servletRequest.getHeader("Accept"),
//                            responseObject,
//                            TimePeriodProto.TimePeriodList.newBuilder()
//                    )
//            ).build();
//        }catch (Exception e){
//            e.printStackTrace();
//            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
//            responseObject.setMessage(ResponseMessages.unknownFailure);
//            Helper.appLogger.info("/timePeriod (Custom)>> output: "+ Helper.getJsonStr(responseObject), e);
//            return Response.status(ResponseStatus.OK.getCode()).entity(
//                    ComHelper.produceOutput(
//                            servletRequest.getHeader("Accept"),
//                            responseObject,
//                            null
//                    )
//            ).build();
//        }
//    }
}
