package com.ddtech.topic;



import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class Sender {
    private final static String EXCHANGE_NAME = "testtopic";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange,声明为 topic 也就是通配符类型
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //发送 abc.1.2数据,凡是能匹配到这个关键词的都会收到
        channel.basicPublish(EXCHANGE_NAME, "key.1", null, "topic 模式消息111".getBytes());
        channel.close();
        connection.close();
    }
}
