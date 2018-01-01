## hdfs-sample
#### Dependencies
* jetty-server-8.1.8
* hadoop 2.8.1

#### deployment
* After creating jar file run following linux command :
  - `zip -d you-jar-file.jar 'META-INF/*.SF' 'META-INF/*.RSA' 'META-INF/*SF'`
#### usage
* add header `fileType` to your request that contains the file with `POST` method. you will get `fileName` in response.
* to getting uploaded files just call `localhost:9595?fileName=`
* until now the code supports pdf, audio files, mp4, images
 
## javaEE-hibernate-postgresql-jdbc

## java-servlet
simple java servlet for beginners
#### test uri's
* /static-test: returns static-page.html by only web.xml config
* /test : return test.jsp file using `ServletTest` class  that utilies `RequestDispatcher` class. This page contains 2 section `header` and `footer`. The file contains alson `El` tags and a simple conditional `JSTL` tag.
* /forward-test : by `?forward` query parameter if `true` forward to static-test else include include-page.html

## kafka-test
* kafka_2.12 dependency
* Contains three Main classes,`SimpleProducer`, `SimpleConsumer` and `ConsumerGroup` 

## jms-test
* ./standalone.sh -c standalone-full.xml -b 0.0.0.0
* ./jboss-cli.sh 
* connect
* jms-queue add --queue-address=TestQueue --entries=queue/test
* jms-topic add --topic-address=TestTopic --entries=topic/test
## activemq-test
* download latest version of activeMQ from http://activemq.apache.org/download.html
* extract it and run from bin sub-dir by command `./activemq start` and stop it using `./activemq stop` 
* change config by editting conf/activemq.xml
* use maven dependency `activemq-all 5.15.2`
