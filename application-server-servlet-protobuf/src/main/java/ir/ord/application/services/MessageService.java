package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.MessageFacedBean;
import ir.ord.application.dto.protoes.AddressProto;
import ir.ord.application.dto.protoes.MessageProto;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 11/26/17.
 */
@Path("/rest/message")
public class MessageService {
    @EJB
    private MessageFacedBean messageFacedBean;



    @Path("/home")
    @GET
    public Response getAllAddressOfAccount(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = messageFacedBean.getHomePageMessageList();
            byte[] response = ComHelper.produceOutput(
                    servletRequest.getHeader("Accept"),
                    responseObject,
                    MessageProto.MessageList.newBuilder()
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(response).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/message/home", e);
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
