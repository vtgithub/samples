package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.PaymentFacedBean;
import ir.ord.application.dal.entities.SuccessPaymentObject;
import ir.ord.application.dal.entities.VerifyResponseObject;
import ir.ord.application.dto.protoes.ParameterProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by vahid on 5/8/17.
 */
@Path("/rest/payment")
public class PaymentService {

    @EJB
    private PaymentFacedBean paymentFacedBean;


    @Path("/initialize")
    @POST
    public Response paymentInitialize(@Context HttpServletRequest servletRequest,
                                      byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try {
            ParameterProto.PaymentInitializeRequest.Builder paymentInitializeRequestBuilder =
                    (ParameterProto.PaymentInitializeRequest.Builder) ComHelper.getBuilderFromInputParameter(
                    servletRequest.getHeader("Content-Type"),
                    ParameterProto.PaymentInitializeRequest.newBuilder(),
                    input
            );
            responseObject = paymentFacedBean.paymentInitialize(
                    servletRequest.getHeader("sessionId"),
                    paymentInitializeRequestBuilder.getAmount(),
                    paymentInitializeRequestBuilder.getSpecificOrderId()

            );

                return Response.status(ResponseStatus.OK.getCode()).entity(
                        ComHelper.produceOutput(
                                servletRequest.getHeader("Accept"),
                                responseObject,
                                ParameterProto.PaymentInitialize.newBuilder()
                        )
                ).build();

        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/payment/initialize", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/fromUser")
    @POST
    @Produces("text/html")
    @Consumes("application/x-www-form-urlencoded")
    public void proccessRedirectedResponse(@Context HttpServletRequest servletRequest,
                                           @Context HttpServletResponse servletResponse,
                                             @FormParam("token") String token,
                                             @FormParam("OrderId") Long orderId,
                                             @FormParam("ResCode") Integer resCode) throws ServletException, IOException {
        servletRequest.setAttribute("cp", "/application-war");
        if(Helper.isSameRequest(token+orderId+resCode))
            return;
        Helper.putRequest(token+orderId+resCode);

        try{
            SuccessPaymentObject successPaymentObject = new SuccessPaymentObject(token, orderId, resCode);
            ResponseObject responseObject = paymentFacedBean.proccessRedirectedResponse(successPaymentObject);
            Helper.appLogger.info("{orderId:"+orderId+", resCode:"+resCode+"}");
            if (responseObject.getResponseCode()!=ResponseStatus.OK.getCode()){
                servletRequest.setAttribute("message", ResponseMessages.failedTransaction);
                servletRequest.setAttribute("bodyMessage", ResponseMessages.failedMainBodyMessage);
                servletRequest.setAttribute("orderId", orderId.toString());
                servletRequest.getRequestDispatcher("/pages/payment-failed.jsp").forward(servletRequest, servletResponse);
            }else {
                VerifyResponseObject verifyResponseObject = (VerifyResponseObject) responseObject.getData();
                servletRequest.setAttribute("orderId", verifyResponseObject.getOrderId());
                servletRequest.setAttribute("amount", verifyResponseObject.getAmount().longValue());
                servletRequest.setAttribute("message", responseObject.getMessage());
                servletRequest.getRequestDispatcher("/pages/payment-success.jsp").forward(servletRequest, servletResponse);
            }
        }catch (Exception e){

            Helper.appLogger.error("proccessRedirectedResponse:", e);
            servletRequest.setAttribute("message", ResponseMessages.failedTransaction);
            servletRequest.setAttribute("bodyMessage", ResponseMessages.failedMainBodyMessage);
            servletRequest.setAttribute("orderId", orderId.toString());
            servletRequest.getRequestDispatcher("/pages/payment-failed.jsp").forward(servletRequest, servletResponse);
        }

//        return Helper.getReturnToAppHtml(responseObject);

    }

    @Path("/fromUser")
    @GET
    public void proccessRedirectedResponse(@Context HttpServletRequest servletRequest,
                                           @Context HttpServletResponse servletResponse) throws ServletException, IOException {
        servletRequest.setAttribute("message", ResponseMessages.failedSecondaryBodyMessage);
        servletRequest.setAttribute("cp", "/application-war");
        servletRequest.getRequestDispatcher("/pages/payment-failed.jsp").forward(servletRequest, servletResponse);
    }

    @Path("/test")
    @GET
//    @Produces("text/html")
    public void test(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) throws ServletException, IOException {
        servletRequest.setAttribute("cp", "/application-war");
        servletRequest.setAttribute("orderId", 888888888);
        servletRequest.setAttribute("message", "متاسفانه پرداخت شماانجام نشد");
//        servletRequest.setAttribute("message", "پرداخت با موفقیت انجام شد");
//        VerifyResponseObject verifyResponseObject = new VerifyResponseObject();
//        verifyResponseObject.setAmount((double) 2000);
//        verifyResponseObject.setOrderId("129421");
//
//        servletRequest.setAttribute("verifyResponseObject", verifyResponseObject);
        servletRequest.getRequestDispatcher("/pages/payment-failed.jsp").forward(servletRequest, servletResponse);
//        return Response.ok().build();

    }

}
