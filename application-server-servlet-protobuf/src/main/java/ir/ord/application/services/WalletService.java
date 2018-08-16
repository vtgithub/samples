package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.WalletFacedBean;
import ir.ord.application.dto.protoes.CreditProto;
import ir.ord.application.dto.protoes.GiftProto;
import ir.ord.application.dto.protoes.ParameterProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 5/2/17.
 */
@Path("/rest/wallet")
public class WalletService {

    @EJB
    private WalletFacedBean walletFacedBean;

    @Path("/charge")
    @POST
    public Response chargeWallet(@Context HttpServletRequest servletRequest,
                                 byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            ParameterProto.WalletChargeRequest.Builder walletChargeBuilder =
                    (ParameterProto.WalletChargeRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            ParameterProto.WalletChargeRequest.newBuilder(),
                            input
                    );
            responseObject = walletFacedBean.chargeWallet(
                    servletRequest.getHeader("sessionId"),
                    walletChargeBuilder.getAmount()
            );
//            Helper.appLogger.info("/wallet/charge >> input: amount="+wall+
//                    "\noutput:"+Helper.getJsonStr(responseObject));
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
            Helper.appLogger.error("/wallet/charge", e);
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
    public Response walletReport(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = walletFacedBean.walletReport(servletRequest.getHeader("sessionId"));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            CreditProto.CreditList.newBuilder()
                            )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/wallet", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/giftCharge")
    @POST
    public Response giftChargeWallet(@Context HttpServletRequest servletRequest,
                                     byte[] input){
        ResponseObject responseObject = new ResponseObject();
        
        try{
            ParameterProto.WalletGiftChargeRequest.Builder walletGiftChargeBuilder =
                    (ParameterProto.WalletGiftChargeRequest.Builder) ComHelper.getBuilderFromInputParameter(
                    servletRequest.getHeader("Content-Type"),
                    ParameterProto.WalletGiftChargeRequest.newBuilder(),
                    input
            );
            responseObject = walletFacedBean.giftChargeWallet(
                    servletRequest.getHeader("sessionId"), walletGiftChargeBuilder.getCode());
//            Helper.appLogger.info("/wallet/giftCharge >> input: code="+code+"\noutput:"+Helper.getJsonStr(responseObject));
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
            Helper.appLogger.error("/wallet/giftCharge", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/gift")
    @GET
    public Response getGiftList(@Context HttpServletRequest servletRequest,
                                     byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = walletFacedBean.getGiftList(servletRequest.getHeader("sessionId"));
//            Helper.appLogger.info("/wallet/giftCharge >> input: code="+code+"\noutput:"+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            GiftProto.GiftList.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/wallet/gift", e);
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
