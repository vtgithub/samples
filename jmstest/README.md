## jms-test
* ./standalone.sh -c standalone-full.xml -b 0.0.0.0
* ./jboss-cli.sh 
* connect
* jms-queue add --queue-address=TestQueue --entries=queue/test
* jms-topic add --topic-address=TestTopic --entries=topic/test
