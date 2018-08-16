package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ClientLocation;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.dto.protoes.OrderProto;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 11/28/17.
 */
@Path("/rest/location")
public class LocationService {



    @Path("/iranLocation")
    @GET
    public Response getIranLocation(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            String ip = (servletRequest.getHeader("X-FORWARDED-FOR") != null) ? servletRequest.getHeader("X-FORWARDED-FOR") : servletRequest.getRemoteAddr();
            ClientLocation clientLocation = Helper.getClientLocation(ip);
            if (clientLocation == null || clientLocation.getCountryCode() == null || clientLocation.getCountryName() == null){
                Helper.appLogger.error("/iranLocation");
                return Response.status(ResponseStatus.BAD_REQUEST.getCode()).build();
            }
            return Response.status(ResponseStatus.OK.getCode()).build();
        }catch (Exception e){
            Helper.appLogger.error("/iranLocation", e);
            return Response.status(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode()).build();

        }
    }

}
