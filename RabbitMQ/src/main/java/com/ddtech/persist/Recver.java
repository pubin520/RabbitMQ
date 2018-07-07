package com.ddtech.persist;




import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Recver {
    private static String EXCHANGE_NAME = "testpersist";
    private static String QUEUE_NAME = "testpersistqueue";
    public static void main(String[] args) throws  Exception{
        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        //声明持久化的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"abc");
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("收到消息:"+new String(body));
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
