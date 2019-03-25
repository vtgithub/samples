package hello_streaming.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.manualflowcontrol.HelloReply;
import io.grpc.examples.manualflowcontrol.HelloRequest;
import io.grpc.examples.manualflowcontrol.StreamingGreeterGrpc;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HelloStreamingClient {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(1);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 4040).usePlaintext().build();
        StreamingGreeterGrpc.StreamingGreeterStub stub = StreamingGreeterGrpc.newStub(channel);
        stub.sayHelloStreaming(new ClientResponseObserver<HelloRequest, HelloReply>() {
            ClientCallStreamObserver<HelloRequest> requestStream;

            public void beforeStart(ClientCallStreamObserver<HelloRequest> clientCallStream) {
                this.requestStream =  clientCallStream;
                requestStream.disableAutoInboundFlowControl();
                Iterator<String> iterator = ClientUtil.names().iterator();
                requestStream.setOnReadyHandler(() -> {

                    while (requestStream.isReady()){
                        if (iterator.hasNext()){
                            String nextName = iterator.next();
                            System.out.println("______request______" + nextName);
                            requestStream.onNext(HelloRequest.newBuilder().setName(nextName).build());
                        }else {
                            requestStream.onCompleted();
                        }
                    }
                });
            }
            private int c = 0;
            public void onNext(HelloReply helloReply) {
                System.out.println("_____response____" + helloReply.getMessage());
                requestStream.request(1);
                c++;
                if (c ==  ClientUtil.names().size())
                    onCompleted();
            }

            public void onError(Throwable throwable) {
                System.out.println("____________ error ___________");
                throwable.printStackTrace();
                done.countDown();
            }

            public void onCompleted() {
                System.out.println("________________all done______________");
                done.countDown();
            }
        });
        done.await();
        channel.awaitTermination(1, TimeUnit.SECONDS);
        channel.shutdown();
        System.out.println("__________ shut down _________");
    }
}
