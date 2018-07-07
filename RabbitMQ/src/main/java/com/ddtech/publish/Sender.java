package com.ddtech.publish;

import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class Sender {
    private final static String EXCHANGE_NAME = "testexchange";//定义交换机的名字

    public static void main(String[] args) throws Exception {
        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//定义一个交换机,类型是fanout,也就是发布订阅模式
        //发布订阅模式的,因为消息是先发到交换机中,而交换机是没有保存功能的,所以如果没有消费者,消息会丢失
        channel.basicPublish(EXCHANGE_NAME, "", null, "aaaaaa111111发布订阅模式的消息".getBytes());
        channel.close();
        connection.close();
    }
}
