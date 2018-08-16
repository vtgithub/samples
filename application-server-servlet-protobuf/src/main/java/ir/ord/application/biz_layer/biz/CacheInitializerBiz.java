package ir.ord.application.biz_layer.biz;

import ir.ord.application.accessories.Helper;
import ir.ord.application.biz_layer.rpc.CacheService;
import ir.ord.application.biz_layer.rpc.CacheServiceHandler;
import ir.ord.application.dal.dao.CacheDao;
import ir.ord.application.dal.dao.DaoException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by vahid on 7/13/17.
 */
@Stateless
public class CacheInitializerBiz {
    @Inject
    public CacheDao cacheDao;
    @Inject
    public CacheServiceHandler cacheServiceHandler;

    public void initCache() throws DaoException {
        cacheDao.initCache();
    }

    public void runCacheService(){
        CacheService.Processor<CacheServiceHandler> processor =
                new CacheService.Processor<CacheServiceHandler>(cacheServiceHandler);
        try {
            TServerTransport serverTransport = new TServerSocket(6000);
            final TSimpleServer server = new TSimpleServer(
                    new TServer.Args(serverTransport).processor(processor));
            new Thread(){
                public void run(){
                    server.serve();
                }
            }.start();
        } catch (Exception e) {
            Helper.appLogger.error("runCacheService", e);
        }
    }

}
