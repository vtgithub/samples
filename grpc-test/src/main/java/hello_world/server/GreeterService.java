package hello_world.server;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

public class GreeterService extends GreeterGrpc.GreeterImplBase {

    public void sayHello(HelloRequest request,
                         StreamObserver<HelloReply> responseObserver) {
        System.out.println("hello_world.client name_______________"+request.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("you name has been received. hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
