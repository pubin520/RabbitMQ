package com.ddtech.route;




import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class Sender {
    private final static String EXCHANGE_NAME = "testeoute";

    public static void main(String[] args) throws  Exception{
        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");//定义路由格式的交换机
        channel.basicPublish(EXCHANGE_NAME, "key1", null, "路由消息aaaaaa".getBytes());
        channel.close();
        connection.close();
    }
}
