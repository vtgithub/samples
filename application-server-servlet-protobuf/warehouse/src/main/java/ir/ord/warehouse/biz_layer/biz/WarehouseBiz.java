package ir.ord.warehouse.biz_layer.biz;

import ir.ord.warehouse.biz_layer.rpc.OrderStruct;
import ir.ord.warehouse.biz_layer.rpc.RemoteDaoException;
import ir.ord.warehouse.dal.dao.RemoteDao;
import ir.ord.warehouse.dto.CustomOrderDto;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vahid on 9/16/17.
 */
@ApplicationScoped
public class WarehouseBiz {

    @Inject
    RemoteDao remoteDao;
    @Inject
    OrderConverter orderConverter;

    public List<CustomOrderDto> getWarehousePendingOrderList() throws RemoteDaoException {
        List<OrderStruct> orderList = remoteDao.getWarehousePendingOrderList();
        List<CustomOrderDto> customOrderDtoList = orderConverter.getDtoList(orderList);
        return customOrderDtoList;
    }

    public void processOrder(String orderId) throws RemoteDaoException {
        remoteDao.processOrder(orderId);
    }
}
