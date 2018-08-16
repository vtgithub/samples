package ir.ord.application.services;

import ir.ord.application.ResponseStatus;

import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;

import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.AccountFacedBean;
import ir.ord.application.dto.AccountInfoDto;
import ir.ord.application.dto.protoes.AccountInfoProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by vahid on 4/22/17.
 */
@Path("/rest/account")
public class AccountService {

    @EJB
    private AccountFacedBean accountFacedBean;


    @Path("/info")
    @GET
    public Response getAccountInfo(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = accountFacedBean.getAccountInfo(servletRequest.getHeader("sessionId"));
//            appLogger.info("/account/ifo(GET) >> output: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AccountInfoProto.AccountInfo.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/account/info(GET)", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/info")
    @POST
    public Response finalSignUpAccount(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            AccountInfoDto accountInfoDto = (AccountInfoDto) ComHelper.consumeInput(
                    servletRequest.getHeader("Content-Type"),
                    AccountInfoProto.AccountInfoRequest.newBuilder(),
                    AccountInfoDto.class,
                    input
            );

            responseObject = accountFacedBean.registerAccountInfo(
                    accountInfoDto, servletRequest.getHeader("sessionId")
            );
//            appLogger.info("/account/info(POST) >> input: "+Helper.getJsonStr(accountInfoDto)+
//                    "\noutput: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AccountInfoProto.AccountInfo.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/account/final(POST)", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/signIn")
    @POST
    public Response signInAccount(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            AccountInfoProto.AccountSignInRequest.Builder accountSignInRequestBuilder =
                    (AccountInfoProto.AccountSignInRequest.Builder) ComHelper.getBuilderFromInputParameter(
                    servletRequest.getHeader("Content-Type"),
                    AccountInfoProto.AccountSignInRequest.newBuilder(),
                    input
            );
            responseObject =  accountFacedBean.signIn(
                    accountSignInRequestBuilder.getPhoneNumber(), accountSignInRequestBuilder.getDeviceId());
//            appLogger.info("/account/signIn >> input: "+Helper.getJsonStr(info)+
//                    "\noutput: "+Helper.getJsonStr(responseObject));
            if (responseObject.getResponseCode() == ResponseStatus.INVALID_ACCOUNT.getCode()) {
                URI toSignUp = new URI("/account/signUp");
                return Response.temporaryRedirect(toSignUp).entity(input).
                        header("Content-Type",servletRequest.getHeader("Content-Type")).
                        header("Accept", servletRequest.getHeader("Accept")).build();
            }
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AccountInfoProto.AccountSignIn.newBuilder()
                    )

            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/account/signIn ", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }

    }

    @Path("/activation")
    @POST
    public Response accountActivation(@Context HttpServletRequest servletRequest, byte[] input){

        ResponseObject responseObject = new ResponseObject();
        try{
            String ip = (servletRequest.getHeader("X-FORWARDED-FOR") != null) ? servletRequest.getHeader("X-FORWARDED-FOR") : servletRequest.getRemoteAddr();

            AccountInfoProto.AccountActivationRequest.Builder accountActivationRequest =
                    (AccountInfoProto.AccountActivationRequest.Builder) ComHelper.getBuilderFromInputParameter(
                    servletRequest.getHeader("Content-Type"),
                    AccountInfoProto.AccountActivationRequest.newBuilder(),
                    input
            );

            responseObject = accountFacedBean.activate(
                    accountActivationRequest.getActivationCode(),
                    accountActivationRequest.getDeviceId(),
                    accountActivationRequest.getSessionInfo().getPlatform(),
                    accountActivationRequest.getSessionInfo().getPlatformVersion(),
                    accountActivationRequest.getSessionInfo().getAppVersion(),
                    accountActivationRequest.getSessionInfo().getDeviceName(),
                    accountActivationRequest.getSessionInfo().getDeviceModel(),
                    ip
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AccountInfoProto.AccountActivation.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/account/activation", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }




    //------------------ change phone number

    @Path("/phone")
    @POST
    public Response changePhoneNumber(@Context HttpServletRequest servletRequest, byte[] input){

        ResponseObject responseObject = new ResponseObject();
        try{
            AccountInfoProto.AccountPhoneRequest.Builder accountPhoneRequestBuilder =
                    (AccountInfoProto.AccountPhoneRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            AccountInfoProto.AccountPhoneRequest.newBuilder(),
                            input
                    );
            responseObject = accountFacedBean.changePhoneNumber(
                    servletRequest.getHeader("sessionId"), accountPhoneRequestBuilder.getPhoneNumber());
//            appLogger.info(
//                    "/account/phone >>" +
//                    "input: {sessionId:"+servletRequest.getHeader("sessionId")+", phoneNumber:"+parameter.get("phoneNumber")+"}"+
//                     "\noutput: "+Helper.getJsonStr(responseObject)
//            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AccountInfoProto.AccountPhone.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error(
                 "/account/phone", e
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/phone/activate")
    @POST
    public Response changePhoneActivation(@Context HttpServletRequest servletRequest,byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            AccountInfoProto.AccountPhoneActivateRequest.Builder accountPhoneActivateRequestBuilder =
                    (AccountInfoProto.AccountPhoneActivateRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            AccountInfoProto.AccountPhoneRequest.newBuilder(),
                            input
                    );
            responseObject = accountFacedBean.changePhoneActivation(
                    servletRequest.getHeader("sessionId"),
                    accountPhoneActivateRequestBuilder.getActivationCode()
            );
//            appLogger.info("/account/phone/activate >> " +
//                    "input: {sessionId: "+servletRequest.getHeader("sessionId")+", activationCode: "+parameter.get("activationCode")+"}"+
//                    "\noutput: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Content-Type"),
                            responseObject,
                            null
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/account/phone/activate", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    //---------------- QR code

    @Path("/qr")
    @GET
    public Response getQRCode(@Context HttpServletRequest servletRequest){

        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = accountFacedBean.getQRCode(servletRequest.getHeader("sessionId"));
//            appLogger.info(
//                    "/account/qr >>" +
//                    "input: {sessionId:"+servletRequest.getHeader("sessionId")+
//                     "}\noutput: "+Helper.getJsonStr(responseObject)
//            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AccountInfoProto.AccountQr.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error(
                    "/account/qr", e
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/signIn/qr")
    @POST
    public Response signUpWithQRCode(@Context HttpServletRequest servletRequest, byte[] input){

        ResponseObject responseObject = new ResponseObject();
        try{
            AccountInfoProto.AccountSignInQrRequest.Builder accountSignInQrRequestBuilder =
                    (AccountInfoProto.AccountSignInQrRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            AccountInfoProto.AccountSignInQrRequest.newBuilder(),
                            input
                    );
            responseObject = accountFacedBean.signUpWithQRCode(
                    accountSignInQrRequestBuilder.getDeviceId(),
                    accountSignInQrRequestBuilder.getQrCode(),
                    accountSignInQrRequestBuilder.getSessionInfo().getPlatform(),
                    accountSignInQrRequestBuilder.getSessionInfo().getPlatformVersion(),
                    accountSignInQrRequestBuilder.getSessionInfo().getAppVersion(),
                    accountSignInQrRequestBuilder.getSessionInfo().getDeviceName(),
                    accountSignInQrRequestBuilder.getSessionInfo().getDeviceModel()
            );
//            appLogger.info(
//                    "/account/signUp/qr >>" +
//                            "input: {deviceId:"+parameter.get("deviceId")+
//                            "}\noutput: "+Helper.getJsonStr(responseObject)
//            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AccountInfoProto.AccountSignInQr.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error(
                    "/account/signUp/qr", e
            );
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
