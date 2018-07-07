package com.ddtech.topic;


import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Recver2 {
    private final static String EXCHANGE_NAME = "testtopic";//定义交换机的名字

    public static void main(String[] args) throws Exception{
        Connection connection = ConnextionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("testtopicqueue2", false, false, false,null);
        //绑定队列到交换机
        //参数3 标记,绑定到交换机的时候会指定一个标记,只有和它一样的标记的消息才会被当前消费者收到
        channel.queueBind("testtopicqueue2", EXCHANGE_NAME, "key.#");
        //如果要接收多个标记,只需要再执行一次即可
        channel.queueBind("testtopicqueue2", EXCHANGE_NAME, "abc.#");
        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者22222:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("testtopicqueue2", false, consumer);
    }
}
