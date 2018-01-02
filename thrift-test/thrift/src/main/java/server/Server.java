package server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import thrift_gen.TestService;

/**
 * Created by vahid on 1/2/18.
 */
public class Server {
    public static void main(String[] args) {
        TestService.Iface iface = new TestServiceHandler();
        TestService.Processor<TestService.Iface> testServiceHandlerProcessor = new TestService.Processor<TestService.Iface>(iface);
        try{
            TServerTransport serverTransport = new TServerSocket(3233);
            final TSimpleServer server = new TSimpleServer(
                    new TServer.Args(serverTransport).processor(testServiceHandlerProcessor));
            new Thread(){
                public void run(){
                    server.serve();
                }
            }.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
