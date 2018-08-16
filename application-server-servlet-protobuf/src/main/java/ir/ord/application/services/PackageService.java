package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.PackageFacedBean;
import ir.ord.application.dto.protoes.PackageProto;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 5/7/17.
 */
@Path("/rest/package")
public class PackageService {

    @Inject
    private PackageFacedBean packageFacedBean;

    @Path("/")
    @GET
    public Response getPackageList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{

            responseObject = packageFacedBean.getPackageList();
//            managementLogger.info("/management/package >> output: "+ Helper.getJsonStr(responseObject));
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            PackageProto.PackageList.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/package", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/button")
    @GET
    public Response getButtonPackeList(@Context HttpServletRequest servletRequest){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = packageFacedBean.getButtonPackageList();

            byte[] responseBytes = ComHelper.produceOutput(
                    servletRequest.getHeader("Accept"),
                    responseObject,
                    PackageProto.PackageList.newBuilder()
            );
            return Response.status(ResponseStatus.OK.getCode()).entity(responseBytes).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/package", e);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            null
                    )
            ).build();
        }
    }

    @Path("/{catId}")
    @GET
    public Response getPackageListByCatId(@PathParam("catId") String catId, @Context HttpServletRequest servletRequest){

        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = packageFacedBean.getPackageListByCatId(catId);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            PackageProto.PackageList.newBuilder()
                    )
            ).build();
        }catch (Exception e){

            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/package", e);
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
