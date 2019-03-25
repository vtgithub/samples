package hello_streaming.server;

import io.grpc.examples.manualflowcontrol.HelloReply;
import io.grpc.examples.manualflowcontrol.HelloRequest;
import io.grpc.examples.manualflowcontrol.StreamingGreeterGrpc;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicBoolean;

public class StreamingGreeterService extends StreamingGreeterGrpc.StreamingGreeterImplBase {

    public StreamObserver<HelloRequest> sayHelloStreaming(final StreamObserver<HelloReply> responseObserver) {

        final ServerCallStreamObserver<HelloReply> replyStreamObserver =
                (ServerCallStreamObserver<HelloReply>) responseObserver;
        replyStreamObserver.disableAutoInboundFlowControl();

//        final AtomicBoolean wasReady = new AtomicBoolean(false);

        replyStreamObserver.setOnReadyHandler(()->{
            if (replyStreamObserver.isReady()){
                System.out.println("__________server is ready____________");
                replyStreamObserver.request(1);
            }
        });

        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest helloRequest) {
                try {
                    System.out.println("___________request received__________"+ helloRequest.getName());
                    Thread.sleep(100);
                    responseObserver.onNext(HelloReply.newBuilder().setMessage("hello" + helloRequest.getName()).build());
                    if (replyStreamObserver.isReady()){
                        replyStreamObserver.request(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    responseObserver.onError(e);
                }

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                System.out.println("___________completed___________");
            }
        };
    }

}
