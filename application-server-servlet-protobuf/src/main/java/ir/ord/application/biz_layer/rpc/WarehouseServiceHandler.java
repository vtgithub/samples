package ir.ord.application.biz_layer.rpc;

import ir.ord.application.Convertor.OrderConvertor;
import ir.ord.application.accessories.Helper;
import ir.ord.application.biz_layer.biz.OrderBiz;
import ir.ord.application.dal.dao.OrderDao;
import ir.ord.application.dal.entities.OrderEntity;
import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.thrift.TException;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 9/16/17.
 */
@ApplicationScoped
public class WarehouseServiceHandler implements WarehouseService.Iface {

    @EJB
    OrderBiz orderBiz;

    public List<OrderStruct> getWarehousePendingOrderList() throws RemoteDaoException, TException {
        try{
            List<OrderStruct> orderStructList = orderBiz.getWarehousePendingOrderStructList();
            return ((orderStructList == null)?(new ArrayList<OrderStruct>()):orderStructList);
        }catch (Exception e){
            Helper.appLogger.error("getWarehousePendingOrderList",e);
            throw new RemoteDaoException(e.getMessage());
        }
    }

    public void changeOrderStatusToWarehouseProccessing(String orderId) throws RemoteDaoException, TException {
        try{
            boolean result = orderBiz.changeOrderStatusToWarehouseProcessing(orderId);
            if (result == false)
                throw new RemoteDaoException();
        }catch (Exception e){
            Helper.appLogger.error("changeOrderStatusToWarehouseProccessing",e);
            throw new RemoteDaoException(e.getMessage());
        }

    }
}
