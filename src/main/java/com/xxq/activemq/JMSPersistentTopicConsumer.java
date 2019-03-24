package com.xxq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author xiaoqiang
 * @Title: JMSPersistentTopicConsumer
 * @ProjectName activemq-receiver
 * @Description: 持久化
 * @date 2019-03-25 00:07
 */
public class JMSPersistentTopicConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory= new ActiveMQConnectionFactory("tcp://192.168.215.128:61616");
        Connection connection=null;
        try {
            connection=connectionFactory.createConnection();
            connection.setClientID("xxq-001");

            connection.start();

            Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Topic destination=session.createTopic("myTopic");
            //创建发送者
            MessageConsumer consumer=session.createDurableSubscriber(destination,"xxq-001");

            TextMessage textMessage=(TextMessage) consumer.receive();
            System.out.println(textMessage.getText());

            session.commit(); //消息被确认

            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
