package messaging.activemq;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subscriber {

    private static final String NO_GREETING = "no greeting";

    private String clientId;
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;

    public void create(String clientId, String topicName) throws JMSException {
        this.clientId = clientId;

        // create a Connection Factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_BROKER_URL);

        // create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        // create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create the Topic from which messages will be received
        Topic topic = session.createTopic(topicName);

        // create a MessageConsumer for receiving messages
        messageConsumer = session.createConsumer(topic);

        // start the connection in order to receive messages
        connection.start();
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }

    public String getGreeting(long timeout) throws JMSException {

        String greeting = NO_GREETING;

        MessageListener messageListener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received message='"
                                + textMessage.getText() + "'");
                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };
        messageConsumer.setMessageListener(messageListener);
        // read a message from the topic destination
//        Message message = messageConsumer.receive(timeout);

        // check if a message was received
//        if (message != null) {
//            // cast the message to the correct type
//            TextMessage textMessage = (TextMessage) message;
//
//            // retrieve the message content
//            String text = textMessage.getText();
//            System.out.println(clientId + ": received message with text= " + text);
//
//            // create greeting
//            greeting = "Hello " + text + "!";
//        } else {
//            System.out.println(": no message received");
//        }

//        System.out.println("greeting="+ greeting);
        return greeting;
    }

    public static void main(String[] args) throws JMSException {
        Subscriber subscriber = new Subscriber();
        subscriber.create("id2","topic1");
        subscriber.getGreeting(5000);
//        subscriber.closeConnection();
    }
}