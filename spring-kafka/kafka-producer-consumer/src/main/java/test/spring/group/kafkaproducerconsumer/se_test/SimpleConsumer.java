package test.spring.group.kafkaproducerconsumer.se_test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
//import org.apache.kafka.common.serialization.StringDeserializer

/**
 * Created by vahid on 12/4/17.
 */
public class SimpleConsumer {
    public static void main(String[] args) throws Exception {
//        if(args.length == 0){
//            System.out.println("Enter topic name");
//            return;
//        }
        //Kafka consumer configuration settings
        String topicName = "test-topic";
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
//        props.put("auto.offset.reset", "smallest");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        //it can be ByteArrayDeserializer, IntegerDeserializer, longDeserializer
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));

        //print the topic name
        System.out.println("Subscribed to topic " + topicName);
        int i = 0;

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)

                // print the offset,key and value for the consumer records.
                System.out.printf("time=%s, offset = %d, key = %s, value = %s\n",
                        new Date(record.timestamp()).toString(), record.offset(), record.key(), record.value());
        }
    }
}
