package test.spring.group.kafkaproducerconsumer.simple;//package test.spring.group.kafkaproducerconsumer.test.spring.group.kafkaproducerconsumer.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/kafka", produces = "application/json", consumes = "application/json")
public class ProducerConsumerTest {

    @Autowired
    Sender sender;
    @Autowired
    Receiver receiver;

    @RequestMapping(method = RequestMethod.POST, value = "/produce")
    public ResponseEntity produce(){
        sender.send("helloworld.t", "_______________________hi__________________________");
        return ResponseEntity.status(200).build();
    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/consume")
//    public ResponseEntity consume() throws InterruptedException {
////        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        return ResponseEntity.status(200).body(receiver.receive());
//    }
}
