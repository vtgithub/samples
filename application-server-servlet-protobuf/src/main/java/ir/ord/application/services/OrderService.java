package ir.ord.application.services;

import ir.ord.application.Convertor.TimePeriodConvertor;
import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.OrderFacedBean;
import ir.ord.application.dto.OrderFeedbackDto;
import ir.ord.application.dto.protoes.OrderProto;
import ir.ord.application.dto.protoes.ParameterProto;
import ir.ord.application.dto.protoes.TimePeriodProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 5/3/17.
 */
@Path("/rest/order")
public class OrderService {

    @EJB
    private OrderFacedBean orderFacedBean;
    @Inject
    private TimePeriodConvertor timePeriodConvertor;

    @Path("/cleanAll")
    @DELETE
    public Response cleanAllOrders(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.cleanAllOrders();
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
            Helper.appLogger.error("/order", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }



    @Path("/{id}")
    @GET
    public Response getOrderById(@Context HttpServletRequest servletRequest, @PathParam("id") String orderId){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.getOrderById(orderId);
//            Helper.appLogger.info("/order >> input: "+ orderId + "\noutput: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.Order.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/order", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/{id}/{status}")
    @GET
    public Response changeOrderStatus(@Context HttpServletRequest servletRequest,
                                      @PathParam("id") String orderId,
                                      @PathParam("status") String status){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.changeOrderStatus(orderId, status.trim());
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.Order.newBuilder()
                    )
            ).build();

        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/order", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/")
    @GET
    public Response getOrderList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.getOrderList(
                    servletRequest.getHeader("sessionId")
            );

//            return Response.status(ResponseStatus.OK.getCode()).entity(responseBytes).build();
//
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.OrderList.newBuilder()
                    )
            ).build();

        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/order", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    

    @Path("/unresolved")
    @GET
    public Response getUnresolvedOrderList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.getUnresolvedOrderList(
                    servletRequest.getHeader("sessionId")
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.OrderList.newBuilder()
                    )
            ).build();
        }catch (Exception e){
                responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
                responseObject.setMessage(ResponseMessages.unknownFailure);
                Helper.appLogger.error("/order/unresolved");
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/count")
    @GET
    public Response getOrderCount(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.getOrderCount(servletRequest.getHeader("sessionId"));

            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ParameterProto.ItemCount.newBuilder()
                    )
            ).build();

        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/order/count", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/{orderId}/cancel")
    @POST
    public Response cancelOrderById(@Context HttpServletRequest servletRequest,
                                    @PathParam("orderId") String orderId, byte[] body){
        ResponseObject responseObject = new ResponseObject();
        try{
            OrderProto.OrderCancelRequest.Builder orderCancelRequestBuilder =
                    (OrderProto.OrderCancelRequest.Builder) ComHelper.getBuilderFromInputParameter(
                        servletRequest.getHeader("Content-Type"),
                        OrderProto.OrderCancelRequest.newBuilder(),
                        body
                );
            responseObject = orderFacedBean.cancelOrder(
                    servletRequest.getHeader("sessionId"),
                    orderId,
                    orderCancelRequestBuilder.getCancelReason().getId(),
                    orderCancelRequestBuilder.getCancelReason().getVal()
            );
//            Helper.appLogger.info("/order >> input: "+ orderId + "\noutput: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.Order.newBuilder()
                    )
            ).build();

        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/GET_ORDER", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }



    @Path("/{orderId}/changeTime")
    @GET
    public Response getChangeTimePeriods(@Context HttpServletRequest servletRequest,
                                    @PathParam("orderId") String orderId){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.getChangeTimePeriodList(
                    orderId
            );
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
            Helper.appLogger.error("/changeTime", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/{orderId}/changeTime")
    @POST
    public Response changeOrderTime(@Context HttpServletRequest servletRequest,
                                    @PathParam("orderId") String orderId,
                                    byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            OrderProto.OrderChangeTimeRequest.Builder orderChangeTimeRequest=
                    (OrderProto.OrderChangeTimeRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            OrderProto.OrderChangeTimeRequest.newBuilder(),
                            input
                    );
//            TimePeriodDto timePeriodDto = timePeriodConvertor.getDtoFromBuilder(
//                    timePeriodConvertor.getTimePeriodRequestFromTimePeriod(
//                        orderChangeTimeRequestBuilder.getTimePeriodDtoBuilder()
//                    )
//            );
            responseObject = orderFacedBean.changeOrderTime(
                    orderId,
                    orderChangeTimeRequest.getDeliveryTimePeriod().getBaseTime(),
                    orderChangeTimeRequest.getDeliveryTimePeriod().getFromTime(),
                    orderChangeTimeRequest.getDeliveryTimePeriod().getToTime(),
                    orderChangeTimeRequest.getRescheduleReason().getId(),
                    orderChangeTimeRequest.getRescheduleReason().getVal()
            );
//            Helper.appLogger.info("/order >> output: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.Order.newBuilder()
                    )
            ).build();

        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/changeTime", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/{orderId}/neededAmount")
    @GET
    public Response changeOrderTime(@Context HttpServletRequest servletRequest,
                                    @PathParam("orderId") String orderId) {
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = orderFacedBean.getNeededAmount(orderId, servletRequest.getHeader("sessionId"));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            OrderProto.OrderNeededAmount.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/neededAmount" , e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/{orderId}/feedback")
    @POST
    public Response submitOrderFeedBack(@Context HttpServletRequest servletRequest, @PathParam("orderId") String orderId, byte[] body){
        ResponseObject responseObject = new ResponseObject();
        try{
            OrderFeedbackDto orderFeedbackDto = (OrderFeedbackDto) ComHelper.consumeInput(
                    servletRequest.getHeader("Content-Type"),
                    OrderProto.OrderFeedback.newBuilder(),
                    OrderFeedbackDto.class,
                    body
            );
            responseObject = orderFacedBean.submitOrderFeedback(orderId, orderFeedbackDto);
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
            Helper.appLogger.error("/feedBack", e);
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
