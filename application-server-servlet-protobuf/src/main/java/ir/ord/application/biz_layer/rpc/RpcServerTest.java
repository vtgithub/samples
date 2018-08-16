package ir.ord.application.biz_layer.rpc;

import ir.ord.application.accessories.Helper;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by vahid on 7/23/17.
 */
public class RpcServerTest {


    public static void main(String[] args) {
        System.out.println("______ init RPC for management start ______");
        Helper.CacheServer.start();
        System.out.println("______ init RPC for management end ______");
}


//        public static void StartsimpleServer(CacheService.Processor<CacheServiceHandler> processor) {
//            try {
//                TServerTransport serverTransport = new TServerSocket(6000);
//                TServer server = new TSimpleServer(
//                        new TServer.Args(serverTransport).processor(processor));
//
//                // Use this for a multithreaded server
//                // TServer server = new TThreadPoolServer(new
//                // TThreadPoolServer.Args(serverTransport).processor(processor));
//
//                System.out.println("Starting the simple server...");
//                server.serve();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        public static void main(String[] args) {
//            StartsimpleServer(new CacheService.Processor<CacheServiceHandler>(new CacheServiceHandler()));
//        }


}
