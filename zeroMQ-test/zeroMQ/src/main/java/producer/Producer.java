package producer;

import dto.TestDto;
import org.apache.commons.lang.SerializationUtils;
import org.zeromq.ZMQ;

/**
 * Created by vahid on 1/1/18.
 */
public class Producer {
    private ZMQ.Socket ZMQSocket ;

    public Producer() {
        this.ZMQSocket = ZMQ.context(20).socket(ZMQ.PUSH);
        ZMQSocket.connect("tcp://localhost:5555");

    }

    public static void main(String[] args) {
        try {
            Producer producer = new Producer();
            TestDto testDto = new TestDto();
            testDto.setId((long) 1200000);
            testDto.setValue("testValue ....");
            producer.ZMQSocket.send(SerializationUtils.serialize(testDto), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
