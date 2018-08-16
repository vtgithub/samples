package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.ComHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.faced.ProductFacedBean;
import ir.ord.application.dto.protoes.ProductProto;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 12/23/17.
 */
@Path("/rest/product")
public class ProductService {
    @EJB
    private ProductFacedBean productFacedBean;

    @Path("/{catId}")
    @GET
    public Response getProductList(@Context HttpServletRequest servletRequest,
                                   @PathParam("catId") String catId){
        ResponseObject responseObject = new ResponseObject();
        try {
//            ProductProto.ProductRequest.Builder productRequestBuilder =
//                    (ProductProto.ProductRequest.Builder) ComHelper.getBuilderFromInputParameter(
//                    servletRequest.getHeader("Content-Type"),
//                    ProductProto.ProductRequest.newBuilder(),
//                    input);
            responseObject = productFacedBean.getProductListByCatId(catId);
            return Response.status(ResponseStatus.OK.getCode()).entity(
                    ComHelper.produceOutput(
                            servletRequest.getHeader("Accept"),
                            responseObject,
                            ProductProto.ProductList.newBuilder()
                    )
            ).build();
        }catch (Exception e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_UNHANDLED_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.unknownFailure);
            Helper.appLogger.error("/product", e);
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
