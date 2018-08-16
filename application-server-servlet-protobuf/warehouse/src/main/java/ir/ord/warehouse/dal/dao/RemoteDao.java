package ir.ord.warehouse.dal.dao;

import ir.ord.warehouse.biz_layer.rpc.OrderStruct;
import ir.ord.warehouse.biz_layer.rpc.RemoteDaoException;

import java.util.List;

/**
 * Created by vahid on 9/16/17.
 */

public interface RemoteDao {

    List<OrderStruct> getWarehousePendingOrderList() throws RemoteDaoException;
    void processOrder(String orderId) throws RemoteDaoException;
}
