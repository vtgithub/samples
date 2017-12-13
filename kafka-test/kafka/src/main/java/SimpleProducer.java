import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Created by vahid on 12/4/17.
 */
public class SimpleProducer{

    public static void main(String[] args) throws Exception{

        //Assign topicName to string variable
        String topicName = "testTopic";

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

                //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for(int i = 0; i < 10; i++) {
            final int finalI = i;
            producer.send(
                    new ProducerRecord<String, String>(
                            topicName,
                            0,
                            Integer.toString(i),
                            Integer.toString(i)
                    ), new Callback() {
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            System.out.println(finalI + "'s ack has been received");
                        }
                    }
            );

//            producer.send(
//                    new ProducerRecord<String, String>(
//                            topicName,
//                            1,
//                            Integer.toString(i),
//                            Integer.toString(i)
//                    ), new Callback() {
//                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                            System.out.println(finalI + "'s ack has been received");
//                        }
//                    }
//            );
        }
        System.out.println("Message sent successfully");
        producer.close();
    }

}
