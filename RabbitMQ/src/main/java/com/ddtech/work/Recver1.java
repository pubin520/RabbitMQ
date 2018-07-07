package com.ddtech.work;



import com.ddtech.uitl.ConnextionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Recver1 {
    private final static String QUEUE = "testwork";//队列的名字


    public static void main(String[] args) throws  Exception {

        Connection connection = ConnextionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE,false,false,false,null);
        // 同一时刻服务器只会发一条消息给消费者,只有当前消费者将消息处理完成后才会获取到下一条消息
        //注释掉后可以获取多条消息,但是会一条一条处理
        channel.basicQos(1);//告诉服务器,在我们没有确认当前消息完成之前,不要给我发新的消息
        DefaultConsumer consumer =new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //当我们收到消息的时候调用
                System.out.println("消费者1 收到的内容是:"+new String(body));
                //确认
                try {
                    Thread.sleep(10);//模拟耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);//参数2,false 为确认收到消息, true 为拒接收到消息
            }
        };
            //注册消费者, 参数2 手动确认,代表我们收到消息后需要手动告诉服务器,我收到消息了
        channel.basicConsume(QUEUE,false,consumer);
    }
}
