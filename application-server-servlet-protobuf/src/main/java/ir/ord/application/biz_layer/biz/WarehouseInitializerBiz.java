package ir.ord.application.biz_layer.biz;

import ir.ord.application.accessories.Helper;
import ir.ord.application.biz_layer.rpc.CacheService;
import ir.ord.application.biz_layer.rpc.CacheServiceHandler;
import ir.ord.application.biz_layer.rpc.WarehouseService;
import ir.ord.application.biz_layer.rpc.WarehouseServiceHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by vahid on 9/17/17.
 */
@Stateless
public class WarehouseInitializerBiz {

    @Inject
    public WarehouseServiceHandler warehouseServiceHandler;

    public void runWarehouseService() {
        WarehouseService.Processor<WarehouseServiceHandler> warehouseServiceProcessor =
                new WarehouseService.Processor<WarehouseServiceHandler>(warehouseServiceHandler);
        try {
            TServerTransport serverTransport = new TServerSocket(7000);
            final TSimpleServer warehouseServer = new TSimpleServer(
                    new TServer.Args(serverTransport).processor(warehouseServiceProcessor));
            new Thread(){
                public void run(){
                    warehouseServer.serve();
                }
            }.start();
        } catch (Exception e) {
            Helper.appLogger.error("runWarehouseService", e);
        }
    }
}
