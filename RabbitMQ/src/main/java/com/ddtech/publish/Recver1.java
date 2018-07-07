package com.ddtech.publish;


import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Recver1 {
    private final static String EXCHANGE_NAME = "testexchange";//定义交换机的名字

    public static void main(String[] args) throws Exception{

        Connection connection = ConnextionUtil.getConnection();

       final Channel channel = connection.createChannel();

        channel.queueDeclare("testpubqueue1", false, false, false,null);
        //绑定队列到交换机
        channel.queueBind("testpubqueue1", EXCHANGE_NAME, "");

        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者11111111:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("testpubqueue1", false, consumer);
    }
}
