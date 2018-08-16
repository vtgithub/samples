package ir.ord.warehouse.services;


import ir.ord.warehouse.biz_layer.biz.WarehouseBiz;
import ir.ord.warehouse.biz_layer.rpc.RemoteDaoException;
import ir.ord.warehouse.dto.CustomOrderDto;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
@Path("/")
public class WarehouseRestService {

    static Logger warehouseLogger = Logger.getLogger("warehouseLogger");
    static {
        warehouseLogger.setLevel(Level.INFO);
    }
    @Inject
    private WarehouseBiz warehouseBiz;


    @Path("/getAllWarehousePending")
    @GET
    public Response getAllWarehousePending(){
        try{
            List<CustomOrderDto> orderList = warehouseBiz.getWarehousePendingOrderList();
            warehouseLogger.info("/getAllWarehousePending");
            return Response.status(Response.Status.OK).entity(orderList).build();
        }catch (RemoteDaoException e){

            warehouseLogger.error("/getAllWarehousePending", e);
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }catch (Exception e){

            warehouseLogger.error("/getAllWarehousePending", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    
    @Path("/processOrder")
    @PUT
    public Response processOrder(String orderId){
        try{
            warehouseBiz.processOrder(orderId);
            warehouseLogger.info("/processOrder");
            return Response.status(Response.Status.OK).build();
        }catch (RemoteDaoException e){

            warehouseLogger.error("/processOrder", e);
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }catch (Exception e){

            warehouseLogger.error("/processOrder", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}
