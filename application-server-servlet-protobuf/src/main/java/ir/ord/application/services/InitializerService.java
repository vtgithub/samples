package ir.ord.application.services;

import ir.ord.application.accessories.Helper;
import ir.ord.application.biz_layer.biz.CacheInitializerBiz;
import ir.ord.application.biz_layer.biz.SessionBiz;
import ir.ord.application.biz_layer.biz.WarehouseInitializerBiz;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by vahid on 7/10/17.
 */
@WebListener
public class InitializerService implements ServletContextListener {

    @EJB
    public CacheInitializerBiz cacheInitializerBiz;
    @EJB
    public WarehouseInitializerBiz warehouseInitializerBiz;
    @EJB
    SessionBiz sessionBiz;


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {

            System.out.println("______ init cache start ____");
            cacheInitializerBiz.initCache();
            System.out.println("______ init cache end ____");

            System.out.println("______ init RPC for management start ______");
            cacheInitializerBiz.runCacheService();
            System.out.println("______ init RPC for management end ______");

            System.out.println("______ init RPC for warehouse start ______");
            warehouseInitializerBiz.runWarehouseService();
            System.out.println("______ init RPC for warehouse end ______");

//            System.out.println("______ init session cache (redis) start ______");
//            sessionBiz.initCache();
//            System.out.println("______ init session cache (redis) end ______");
//            Helper.CacheServer.start();

        }catch (Exception e){
            Helper.appLogger.error("cache initialization Exception",e);
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
