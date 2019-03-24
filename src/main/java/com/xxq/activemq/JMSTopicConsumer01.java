package com.xxq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author xiaoqiang
 * @Title: JMSTopicConsumer01
 * @ProjectName activemq-receiver
 * @Description: TODO
 * @date 2019-03-24 23:48
 */
public class JMSTopicConsumer01 {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.215.128:61616");
        Connection connection = null;
        try {

            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Destination destination = session.createTopic("myTopic");
            //创建发送者
            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage textMessage = (TextMessage) consumer.receive();
            System.out.println(textMessage.getText());

            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
