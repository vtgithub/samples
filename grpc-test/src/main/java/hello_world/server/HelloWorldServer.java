package hello_world.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelloWorldServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(4040).addService(new GreeterService()).build();
        server.start();
        server.awaitTermination(2, TimeUnit.HOURS);
    }
}
