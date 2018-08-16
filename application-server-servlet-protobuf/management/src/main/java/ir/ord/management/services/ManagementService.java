package ir.ord.management.services;

import ir.ord.management.accessories.ComHelper;
import ir.ord.management.accessories.ResponseMessages;
import ir.ord.management.accessories.ResponseStatus;
import ir.ord.management.biz_layer.biz.ManagementFacedBean;
import ir.ord.management.dto.protoes.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 5/6/17.
 */
@Path("/")
public class ManagementService {

    @Inject
    private ManagementFacedBean managementFacedBean;

//    @Path("/category")
//    @GET
//    public Response getCategoryList(@Context HttpServletRequest servletRequest){
//        ResponseObject responseObject = new ResponseObject();
//        try{
//            responseObject = managementFacedBean.getCategoryList();
//
//            return Response.status(ResponseStatus.OK.getCode()).entity(
//                    ComHelper.produceOutput(
//                            servletRequest.getHeader("Accept"),
//                            responseObject,
//                            CategoryProto.CategoryList.newBuilder()
//                    )
//            ).build();
//        }catch (Exception e){
//
//            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
//            responseObject.setMessage(ResponseMessages.unknownFailure);
//            managementLogger.error("/management/category >> output: "+Helper.getJsonStr(responseObject), e);
//            return Response.status(ResponseStatus.OK.getCode()).entity(responseObject).build();
//        }
//    }


    @Path("/category")
    @POST
    public Response addCategory(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try{
            CategoryProto.CategoryRequest.Builder categoryBuilder =
                    (CategoryProto.CategoryRequest.Builder) ComHelper.pureConsumeInput(
                    servletRequest.getHeader("Content-Type"),
                    CategoryProto.CategoryRequest.newBuilder(),
                    input
            );
            responseBuilder = managementFacedBean.addCategory(categoryBuilder);

            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.pureProduceOutput(
                            servletRequest.getHeader("Accept"),
                            responseBuilder,
                            null
                    )
            ).build();
        }catch (Exception e){
            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.unknownFailure);
            ComHelper.managementLogger.error("/category >>\noutput: "+ComHelper.getJsonStrFromBuilder(responseBuilder), e);
            return Response.status(ResponseStatus.OK.getCode()).entity(ComHelper.getJsonStrFromBuilder(responseBuilder)).build();
        }
    }

//
//    @Path("/package")
//    @GET
//    public Response getPackageList(@Context HttpServletRequest servletRequest){
//        ResponseObject responseObject = new ResponseObject();
//        try{
//
//            responseObject = managementFacedBean.getPackageList();
////            managementLogger.info("/management/package >> output: "+ Helper.getJsonStr(responseObject));
//            return Response.status(ResponseStatus.OK.getCode()).entity(
//                    ComHelper.produceOutput(
//                            servletRequest.getHeader("Accept"),
//                            responseObject,
//                            PackageProto.PackageList.newBuilder()
//                     )
//            ).build();
//        }catch (Exception e){
//
//            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
//            responseObject.setMessage(ResponseMessages.unknownFailure);
//            managementLogger.error("/management/package >> output: "+Helper.getJsonStr(responseObject), e);
//            return Response.status(ResponseStatus.OK.getCode()).entity(responseObject).build();
//        }
//    }

    @Path("/package")
    @POST
    public Response addPackage(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try{
            PackageProto.PackageRequest.Builder packageBuilder=
                    (PackageProto.PackageRequest.Builder) ComHelper.pureConsumeInput(
                    servletRequest.getHeader("Content-Type"),
                    PackageProto.PackageRequest.newBuilder(),
                    input
            );
            responseBuilder = managementFacedBean.addPackage(packageBuilder);

            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.pureProduceOutput(
                            servletRequest.getHeader("Accept"),
                            responseBuilder,
                            null
                    )
            ).build();
        }catch (Exception e){

            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.unknownFailure);
            ComHelper.managementLogger.error("/package >> output: "+ ComHelper.getJsonStrFromBuilder(responseBuilder), e);
            return Response.status(ResponseStatus.OK.getCode()).entity(ComHelper.getJsonStrFromBuilder(responseBuilder)).build();
        }
    }

//    @Path("/timePeriod")
//    @GET
//    public Response getTimePeriodList(@Context HttpServletRequest servletRequest){
//        ResponseObject responseObject = new ResponseObject();
//        try{
//            responseObject = managementFacedBean.getTimePeriodList();
////            managementLogger.info("/management/timePeriod >> output: "+ Helper.getJsonStr(responseObject));
//            return Response.status(ResponseStatus.OK.getCode()).entity(
//                    ComHelper.produceOutput(
//                            servletRequest.getHeader("Accept"),
//                            responseObject,
//                            TimePeriodProto.TimePeriodList.newBuilder()
//                    )
//            ).build();
//        }catch (Exception e){
//
//            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
//            responseObject.setMessage(ResponseMessages.unknownFailure);
//            managementLogger.info("/management/timePeriod >> output: "+ Helper.getJsonStr(responseObject), e);
//            return Response.status(ResponseStatus.OK.getCode()).entity(responseObject).build();
//        }
//    }

    @Path("/timePeriod")
    @POST
    public Response addTimePeriod(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try{
            TimePeriodProto.TimePeriodRequest.Builder timePeriodRequestBuilder =
                    (TimePeriodProto.TimePeriodRequest.Builder) ComHelper.pureConsumeInput(
                    servletRequest.getHeader("Content-Type"),
                    TimePeriodProto.TimePeriodRequest.newBuilder(),
                    input
            );
            responseBuilder = managementFacedBean.addTimePeriod(timePeriodRequestBuilder);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.pureProduceOutput(
                            servletRequest.getHeader("Accept"),
                            responseBuilder,
                            null
                    )
            ).build();
        }catch (Exception e){

            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.unknownFailure);
            ComHelper.managementLogger.error("/timePeriod >> output: "+ ComHelper.getJsonStrFromBuilder(responseBuilder),e);
            return Response.status(ResponseStatus.OK.getCode()).entity(ComHelper.getJsonStrFromBuilder(responseBuilder)).build();
        }
    }

    @Path("/timePeriodFill")
    @POST
    public Response fillTimePeriodList(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try{
            TimePeriodProto.TimePeriodFillRequest.Builder builder=
                    (TimePeriodProto.TimePeriodFillRequest.Builder) ComHelper.pureConsumeInput(
                    servletRequest.getHeader("Content-Type"),
                    TimePeriodProto.TimePeriodFillRequest.newBuilder(),
                    input
            );
            responseBuilder = managementFacedBean.fillTimePeriodList(builder);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.pureProduceOutput(
                            servletRequest.getHeader("Accept"),
                            responseBuilder,
                            null
                    )
            ).build();
        }catch (Exception e){

            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseBuilder.setMessage(ResponseMessages.unknownFailure);
            ComHelper.managementLogger.error("/timePeriodFill >> output: "+ ComHelper.getJsonStrFromBuilder(responseBuilder),e);
            return Response.status(ResponseStatus.OK.getCode()).entity(ComHelper.getJsonStrFromBuilder(responseBuilder)).build();
        }
    }

    @Path("/gift")
    @POST
    public Response addGift(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try{
            GiftProto.GiftRequest.Builder giftRequestBuilder =
                    (GiftProto.GiftRequest.Builder) ComHelper.pureConsumeInput(
                        servletRequest.getHeader("Content-Type"),
                        GiftProto.GiftRequest.newBuilder(),
                        input
            );
            responseBuilder = managementFacedBean.addGift(giftRequestBuilder);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.pureProduceOutput(
                            servletRequest.getHeader("Accept"),
                            responseBuilder,
                            null
                    )
            ).build();
        }catch (Exception e){

            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            ComHelper.managementLogger.error("/gift >> output: "+ ComHelper.getJsonStrFromBuilder(responseBuilder),e);
            return Response.status(ResponseStatus.OK.getCode()).entity(ComHelper.getJsonStrFromBuilder(responseBuilder)).build();
        }


    }

    @Path("/gift")
    @GET
    public Response getAllGift(@Context HttpServletRequest servletRequest){
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        try{
            responseBuilder = managementFacedBean.getAllGift();
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.pureProduceOutput(
                            servletRequest.getHeader("Accept"),
                            responseBuilder,
                            GiftProto.GiftList.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseBuilder.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            ComHelper.managementLogger.error("/gift(GET)", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(ComHelper.getJsonStrFromBuilder(responseBuilder)).build();
        }
    }


}
