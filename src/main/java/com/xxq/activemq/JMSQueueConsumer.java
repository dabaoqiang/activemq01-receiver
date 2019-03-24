package com.xxq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author xiaoqiang
 * @Title: JMSQueueConsumer
 * @ProjectName activemq-receiver
 * @Description: TODO
 * @date 2019-03-24 19:53
 */
public class JMSQueueConsumer {
    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.215.128:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination myQueue = session.createQueue("myqueue");
            MessageConsumer consumer = session.createConsumer(myQueue);
            TextMessage textMessage = (TextMessage) consumer.receive();
            System.out.println(textMessage.getText());
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.getStackTrace();
                }
            }
        }


    }
}
