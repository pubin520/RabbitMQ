package com.ddtech.route;




import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Recver1 {
    private final static String EXCHANGE_NAME = "testeoute";//定义交换机的名字

    public static void main(String[] args) throws Exception{
        Connection connection = ConnextionUtil.getConnection();
        final  Channel channel = connection.createChannel();
        channel.queueDeclare("testroutequeue1", false, false, false,null);
        //绑定队列到交换机
        //参数3 标记,绑定到交换机的时候会指定一个标记,只有和它一样的标记的消息才会被当前消费者收到
        // 绑定队列到交换机,绑定自己的关键字 key 为key,注意在绑定到指定路由(交换机)的时候,该路由必须存在,也就是我们必须先由发送者创建一个路由才可以
        channel.queueBind("testroutequeue1", EXCHANGE_NAME, "key1");
        //如果要接收多个标记,只需要再执行一次即可
        channel.queueBind("testroutequeue1", EXCHANGE_NAME, "key2");
        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者11111111:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("testroutequeue1", false, consumer);
    }
}
