package ir.ord.application.services;

import ir.ord.application.ResponseStatus;

import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;

import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.ButtonRequestProccessorBiz;
import ir.ord.application.biz_layer.faced.ButtonFacedBean;
import ir.ord.application.dto.protoes.ButtonProto;
import org.apache.log4j.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

/**
 * Created by vahid on 4/17/17.
 */
@Path("/rest/button")
public class ButtonService {

    @EJB
    private ButtonRequestProccessorBiz buttonRequestProccessorBiz;
    @EJB
    private ButtonFacedBean buttonFacedBean;

    @Consumes("*/*")
    @POST
    @Path("/")
    public void proccessButtonRequest( @Context HttpServletRequest servletRequest,
                                       @Suspended final AsyncResponse asyncResponse,
                                       final byte[] body){
        final String deviceToken = servletRequest.getHeader("deviceToken"),
                deviceVersion = servletRequest.getHeader("deviceVersion"),
                appId = servletRequest.getHeader("appId"),
                reqKey = servletRequest.getHeader("reqKey"),
                ip = servletRequest.getHeader("ip");
        final StringBuilder sb = new StringBuilder();
        sb.append(", deviceToken: ");
        sb.append(deviceToken);
        sb.append(", deviceVersion: ");
        sb.append(deviceVersion);
        sb.append(", appId:");
        sb.append(appId);
        sb.append(", contentLength: ");
        sb.append(servletRequest.getContentLength());
        sb.append(", ip: ");
        sb.append(ip);
        sb.append(", body: ");
        sb.append(new String(body));

        asyncResponse.setTimeout(Helper.getResponseTimeOut(), TimeUnit.SECONDS);

        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
            public void handleTimeout(AsyncResponse asyncResponse) {
                Helper.buttonLogger.error("TimeOut: "+sb.toString());
                asyncResponse.resume(Response.status(ResponseStatus.TIME_OUT.getCode()).build());
            }
        });

        new Thread() {
            @Override
            public void run() {
                try{

                    int responseCode = buttonFacedBean.proccessButtonRequest(
                            deviceToken, deviceVersion, appId, reqKey, ip, body
                    );
                    String returnedVal = "{\"reqKey\":\""+reqKey+"\" , \"responseCode\":\""+responseCode+"\" }";
                    Helper.buttonLogger.info(" input: "+ sb.toString() + ", output: "+returnedVal);
                    asyncResponse.resume(Response.status(ResponseStatus.OK.getCode()).entity(returnedVal).build());
                }catch (Exception e){
                    Helper.buttonLogger.error(" reqKey: " + reqKey, e);
                    asyncResponse.resume(Response.status(ResponseStatus.INTERNAL_SERVER_ERROR.getCode()).build());
                }

            }
        }.start();
    }

    @Consumes("*/*")
    @POST
    @Path("/cancel")
    public void processButtonCancelRequest(final byte[] body,
                                   @Context HttpServletRequest servletRequest,
                                   @Suspended final AsyncResponse asyncResponse){
        final String deviceId = servletRequest.getHeader("deviceToken"),
                deviceVersion = servletRequest.getHeader("deviceVersion"),
                appId = servletRequest.getHeader("appId"),
                reqKey = servletRequest.getHeader("reqKey");
        final StringBuilder sb = new StringBuilder();
        sb.append(", deviceId: ");
        sb.append(deviceId);
        sb.append(", deviceVersion: ");
        sb.append(deviceVersion);
        sb.append(", appId:");
        sb.append(appId);
        sb.append(", contentLength: ");
        sb.append(servletRequest.getContentLength());
        sb.append(", body:");
        sb.append(new String(body));

        asyncResponse.setTimeout(Helper.getResponseTimeOut(), TimeUnit.SECONDS);

        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
            public void handleTimeout(AsyncResponse asyncResponse) {
                Helper.buttonLogger.error("/cancel >> TimeOut: "+sb.toString());
                asyncResponse.resume(Response.status(ResponseStatus.TIME_OUT.getCode()).build());
            }
        });

        new Thread() {
            @Override
            public void run() {
                try{
                    int responseCode = buttonRequestProccessorBiz.proccessButtonCancelRequest(
                            deviceId, deviceVersion, appId, reqKey, body
                    );
                    String returnedVal = "{\"reqKey\":\""+reqKey+"\" , \"responseCode\":\""+responseCode+"\" }";
                    Helper.buttonLogger.info("/cancel >> input: "+ sb.toString()+", output: "+returnedVal);
                    asyncResponse.resume(Response.status(ResponseStatus.OK.getCode()).header("Content-Type","application/json").
                            entity(returnedVal).build());
                }catch (Exception e){
                    Helper.buttonLogger.error("/cancel >> reqKey: "+reqKey,e);
                    asyncResponse.resume(Response.status(ResponseStatus.INTERNAL_SERVER_ERROR.getCode()).build());
                }

            }
        }.start();
    }


    @Path("/setup/activate")
    @POST
    @Consumes("*/*")
    @Asynchronous
    public void activateButton(final byte[] activationCode,
                               @Context final HttpServletRequest servletRequest,
                               @Suspended final AsyncResponse asyncResponse){


        final String deviceToken = servletRequest.getHeader("deviceToken"),
                deviceVersion = servletRequest.getHeader("deviceVersion"),
                appId = servletRequest.getHeader("appId"),
                reqKey = servletRequest.getHeader("reqKey");
        final String  activationCodeStr = new String(activationCode);
        final StringBuilder sb = new StringBuilder();
        sb.append(", deviceToken: ");sb.append(deviceToken);
        sb.append(", deviceVersion: ");sb.append(deviceVersion);
        sb.append(", reqKey:");sb.append(reqKey);
        sb.append(", appId:");sb.append(appId);
        sb.append(", activationCode:");sb.append(activationCodeStr);

        asyncResponse.setTimeout(Helper.getResponseTimeOut(), TimeUnit.SECONDS);
        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
            public void handleTimeout(AsyncResponse asyncResponse) {
                Helper.buttonLogger.error("/setup/activate >> timeOut: "+ sb.toString());
                asyncResponse.resume(Response.status(ResponseStatus.TIME_OUT.getCode()).build());
            }
        });

        new Thread(){
            @Override
            public void run(){
                try {
                    ResponseObject responseObject = buttonFacedBean.activateButton(
                            deviceToken, activationCodeStr
                    );
                    sb.append(Helper.getJsonStr(responseObject));
                    Helper.buttonLogger.info("/setup/activate >> input: "+sb.toString());
                    if(responseObject.getData() == null || responseObject.getData().equals(""))
                        asyncResponse.resume(Response.status(responseObject.getResponseCode()).build());
                    Object data = responseObject.getData();
                    asyncResponse.resume(
                            Response.status(Response.Status.OK).entity(Helper.getJsonStr(data)).build()
                    );
                }catch (Exception e){

                    Helper.buttonLogger.error("/setup/activate >> input:"+sb.toString(),e);
                    asyncResponse.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
                }
            }
        }.start();

    }


    @Path("/functionality")
    @POST
    public Response getFunctionalityMap(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            ButtonProto.ButtonFunctionalityRequest.Builder buttonFunctionalityRequestBuilder =
                    (ButtonProto.ButtonFunctionalityRequest.Builder) ComHelper.getBuilderFromInputParameter(
                    servletRequest.getHeader("Content-Type"),
                    ButtonProto.ButtonFunctionalityRequest.newBuilder(),
                    input
            );
            responseObject = buttonFacedBean.getFunctionalityMap(buttonFunctionalityRequestBuilder.getDeviceToken());
//            appLogger.info(
//                    "/button/functionality >> input: deviceToken="+parameter.get("deviceToken")
//                    + " \n output: "+Helper.getJsonStr(responseObject)
//            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ButtonProto.ButtonFunctionality.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            Helper.appLogger.error(
                    "/button/functionality" ,e);
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/buy")
    @POST
    public Response buyButton(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try {
            ButtonProto.ButtonBuyRequest.Builder buttonBuyRequestBuilder =
                    (ButtonProto.ButtonBuyRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            ButtonProto.ButtonBuyRequest.newBuilder(),
                            input
                    );
            responseObject = buttonFacedBean.buyButton(
                    servletRequest.getHeader("app-version"),
                    servletRequest.getHeader("app-version-code"),
                    servletRequest.getHeader("device-platform"),
                    servletRequest.getHeader("device-sdk"),
                    servletRequest.getHeader("device-name"),
                    servletRequest.getHeader("page"),
                    servletRequest.getHeader("lat"),
                    servletRequest.getHeader("lng"),
                    buttonBuyRequestBuilder.getAddressId(),
                    servletRequest.getHeader("sessionId"),
                    buttonBuyRequestBuilder.getPackageId(),
                    buttonBuyRequestBuilder.getButtonFunctionalityMapMap()
            );
//            appLogger.info(
//                    "/button/buy >> input: {sessionId="+servletRequest.getHeader("sessionId")
//                            + " , info="+Helper.getJsonStr(buyButtonDto)
//                            + "} \n output: "+Helper.getJsonStr(responseObject)
//            );
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
            Helper.appLogger.error(
                    "/button/buy",e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/setup/activationCode") // sends activation code and insert packageId for button Entity
    @POST
    public Response getActivationCode(@Context HttpServletRequest servletRequest, byte[] input){

        ResponseObject responseObject = new ResponseObject();
        try {
            ButtonProto.ButtonSetupActivationCodeRequest.Builder buttonSetupActivationCodeRequestBuilder =
                    (ButtonProto.ButtonSetupActivationCodeRequest.Builder) ComHelper.getBuilderFromInputParameter(
                            servletRequest.getHeader("Content-Type"),
                            ButtonProto.ButtonSetupActivationCodeRequest.newBuilder(),
                            input
                    );
            responseObject = buttonFacedBean.getActivationCode(
                    buttonSetupActivationCodeRequestBuilder.getName(),
                    servletRequest.getHeader("sessionId"),
                    buttonSetupActivationCodeRequestBuilder.getDeviceToken(),
                    servletRequest.getRemoteAddr(),
                    buttonSetupActivationCodeRequestBuilder.getFunctionalityMapMap()
            );
//            appLogger.info(
//                    "/button/setup/activationCode >> input: sessionId="+servletRequest.getHeader("sessionId")
//                            + ", deviceToken="+getActivationCodeDTO.getDeviceToken()
//                            + " \n output: "+Helper.getJsonStr(responseObject)
//            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ButtonProto.ButtonSetupActivationCode.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            Helper.appLogger.error(
                    "/button/setup/activationCode" ,e);
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }



    @Path("/") // sends activation code and insert packageId for button Entity
    @GET
    public Response getButtonList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = buttonFacedBean.getButtonList(servletRequest.getHeader("sessionId"));
//            appLogger.info(
//                    "/button>> input: sessionId="+servletRequest.getHeader("sessionId")
//                            + " \n output: "+Helper.getJsonStr(responseObject)
//            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ButtonProto.OrdButtonList.newBuilder()
                    )
            ).build();
        }catch (Exception e) {

            Helper.appLogger.error(
                    "/button", e);
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
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
