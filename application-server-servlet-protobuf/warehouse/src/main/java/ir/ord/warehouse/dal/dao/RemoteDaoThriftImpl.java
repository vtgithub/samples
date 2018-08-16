package ir.ord.warehouse.dal.dao;

import ir.ord.warehouse.biz_layer.rpc.OrderStruct;
import ir.ord.warehouse.biz_layer.rpc.RemoteDaoException;
import ir.ord.warehouse.biz_layer.rpc.WarehouseService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by vahid on 9/16/17.
 */
@Transactional
@ApplicationScoped
public class RemoteDaoThriftImpl implements RemoteDao{

    TTransport transport = new TSocket("localhost", 7000);

    public List<OrderStruct> getWarehousePendingOrderList() throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            WarehouseService.Client client = new WarehouseService.Client(protocol);
            List<OrderStruct> warehousePendingOrderList = client.getWarehousePendingOrderList();
            return warehousePendingOrderList;
        } catch (Exception e){

            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }
    }

    public void processOrder(String orderId) throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            WarehouseService.Client client = new WarehouseService.Client(protocol);
            client.changeOrderStatusToWarehouseProccessing(orderId);
        }catch (Exception e){

            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }
    }
}
