package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.SessionFacedBean;
import ir.ord.application.dto.SessionDeactivateDto;
import ir.ord.application.dto.protoes.SessionInfoProto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 9/10/17.
 */
@Path("/rest/account")
public class SessionService {

    @EJB
    SessionFacedBean sessionFacedBean;

    @Path("/session")
    @GET
    public Response getActiveSessions(@Context HttpServletRequest servletRequest){

        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = sessionFacedBean.getActiveSessions(servletRequest.getHeader("sessionId"));

            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            SessionInfoProto.SessionInfoList.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error(
                    "/account/sessions", e
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


    @Path("/signOut")
    @POST
    public Response signOutAccount(@Context HttpServletRequest servletRequest, byte[] body){

        ResponseObject responseObject = new ResponseObject();
        try{

            if (body == null || body.length == 0)
                responseObject = sessionFacedBean.signOutAccount(servletRequest.getHeader("sessionId"));
            else {
                List<Object> objectList = ComHelper.groupConsumeInput(
                        servletRequest.getHeader("Content-Type"),
                        SessionInfoProto.SessionDeactivateRequest.newBuilder(),
                        SessionDeactivateDto.class,
                        body
                );
                List<SessionDeactivateDto> sessionList = new ArrayList<SessionDeactivateDto>();
                for (Object sessionDeactivateObject : objectList) {
                    sessionList.add((SessionDeactivateDto) sessionDeactivateObject);
                }
                responseObject = sessionFacedBean.signOutAccountList(sessionList);
            }
//            Helper.appLogger.info("/account/signOut >> output: "+Helper.getJsonStr(responseObject));
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
            Helper.appLogger.error("/account/signOut",e);
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
