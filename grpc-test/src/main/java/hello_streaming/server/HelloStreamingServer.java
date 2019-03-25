package hello_streaming.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelloStreamingServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(4040).addService(new StreamingGreeterService()).build().start();
        System.out.println("__________Server started____________port:"+4040);
//        server.awaitTermination(2, TimeUnit.HOURS);
        server.awaitTermination();
    }
}
