package com.ddtech.persist;

import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;


public class Sender {
    private static String EXCHANGE_NAME = "testpersist";

    public static void main(String[] args) throws  Exception{
        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明持久化的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        //声明持久化消息
        channel.basicPublish(EXCHANGE_NAME, "abc", MessageProperties.PERSISTENT_TEXT_PLAIN, "持久化的消息".getBytes());
        channel.close();
        connection.close();

    }

}
