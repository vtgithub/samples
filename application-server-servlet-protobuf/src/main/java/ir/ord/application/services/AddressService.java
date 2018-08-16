package ir.ord.application.services;

import ir.ord.application.ResponseStatus;

import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;

import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.AddressFacedBean;
import ir.ord.application.dto.AddressDto;
import ir.ord.application.dto.TimePeriodDto;
import ir.ord.application.dto.protoes.*;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/1/17.
 */
@Path("/rest/account/address")
public class AddressService {

    @EJB
    private AddressFacedBean addressFacedBean;

    @Path("/")
    @GET
    public Response getAllAddressOfAccount(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = addressFacedBean.getAllAddressOfAccount(servletRequest.getHeader("sessionId"));
            byte[] response = ComHelper.produceOutput(
                    servletRequest.getHeader("Accept"),
                    responseObject,
                    AddressProto.AccountAddressList.newBuilder()
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(response).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/address", e);
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
    @POST
    public Response addressRegistration(@Context HttpServletRequest servletRequest, byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try {
            AddressDto addressDto = (AddressDto) ComHelper.consumeInput(
                    servletRequest.getHeader("Content-Type"),
                    AddressProto.AccountAddressResquest.newBuilder(),
                    AddressDto.class,
                    input
            );
            responseObject = addressFacedBean.registerAddress(
                    addressDto, servletRequest.getHeader("sessionId")
            );
//            appLogger.info("/address/register >> input: "+Helper.getJsonStr(addressDto)+
//                    "\noutput: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            AddressProto.AccountAddress.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/address/register " , e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/{addressID}")
    @GET
    public Response getAddress(@Context HttpServletRequest servletRequest,
                               @PathParam("addressID") String addressId){
        ResponseObject responseObject = new ResponseObject();
    try{
        responseObject = addressFacedBean.getAccountAddressById(servletRequest.getHeader("sessionId"), addressId);

        AddressProto.AccountAddress.Builder accountAddressBuilder = AddressProto.AccountAddress.newBuilder();
        return Response.status(ResponseStatus.OK.getCode()).entity(
                ComHelper.produceOutput(
                        servletRequest.getHeader("Accept"),
                        responseObject,
                        accountAddressBuilder
                )
        ).build();
    }catch (Exception e){

        responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
        responseObject.setMessage(ResponseMessages.unknownFailure);
        Helper.appLogger.error("/account/address/{"+addressId+"}" , e);
        return Response.status(ResponseStatus.OK.getCode()).entity(
                ComHelper.produceOutput(
                        servletRequest.getHeader("Accept"),
                        responseObject,
                        null
                )
        ).build();
    }

    }

    @Path("/{addressID}")
    @PUT
    public Response addressChange(@Context HttpServletRequest httpServletRequest,
                                  @PathParam("addressID") String addressID,
                                  byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try {
            AddressDto addressDto = (AddressDto) ComHelper.consumeInput(
              httpServletRequest.getHeader("Content-Type"),
                    AddressProto.AccountAddressResquest.newBuilder(),
                    AddressDto.class,
                    input
            );
            addressDto.setId(addressID);
            responseObject = addressFacedBean.changeAddress(
                    addressDto, httpServletRequest.getHeader("sessionId")
            );
//            appLogger.info("/address/change >> input: "+Helper.getJsonStr(addressDto)+
//                    "\noutput: "+Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            httpServletRequest.getHeader("Accept"),
                            responseObject,
                            AddressProto.AccountAddress.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/address/change", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            httpServletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/{addressID}")
    @DELETE
    public Response deleteAddressById(@Context HttpServletRequest servletRequest,
                                      @PathParam("addressID") String addressID,
                                      byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = addressFacedBean.deleteAddressById(
                    servletRequest.getHeader("sessionId"),
                    addressID
            );
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
            Helper.appLogger.error("/account/address/{"+addressID+"}", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }


    @Path("/{addressID}/time")
    @PUT
    public Response changeTimePeriodList(@Context HttpServletRequest httpServletRequest,
                                         @PathParam("addressID") String addressID,
                                         byte[] input){
        ResponseObject responseObject = new ResponseObject();
        try{
            List<Object> objectList = ComHelper.groupConsumeInput(
                    httpServletRequest.getHeader("Content-Type"),
                    TimePeriodProto.TimePeriodRequest.newBuilder(),
                    TimePeriodDto.class,
                    input
            );
            List<TimePeriodDto> timePeriodDtoList = new ArrayList<TimePeriodDto>();
            for (Object obj : objectList) {
                timePeriodDtoList.add((TimePeriodDto) obj);
            }
            responseObject = addressFacedBean.changeTimePeriodList(
                    httpServletRequest.getHeader("sessionId"),
                    addressID,
                    timePeriodDtoList
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            httpServletRequest.getHeader("Accept"),
                            responseObject,
                            AddressProto.AccountAddress.newBuilder()
                    )
            ).build();

        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/account/address/{addressID}/time", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            httpServletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }

    }

    @Path("/{addressID}/buttons")
    @GET
    public Response getAllAddressButtons(@Context HttpServletRequest servletRequest,
                                  @PathParam("addressID") String addressID){
       ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = addressFacedBean.getAllAddressButtons(servletRequest.getHeader("sessionId"), addressID);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ButtonProto.OrdButtonList.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/account/address/addressId/buttons",e);
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
