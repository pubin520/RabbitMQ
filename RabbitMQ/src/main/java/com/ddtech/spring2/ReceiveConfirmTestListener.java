package com.ddtech.spring2;




import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;


@Component("receiveConfirmTestListener")
public class ReceiveConfirmTestListener  implements ChannelAwareMessageListener {
    /**
     * 收到消息的时候执行的监听
     * @param message
     * @param channel
     * @throws Exception
     */
    public void onMessage(Message message, Channel channel) throws Exception {

       try {
           System.err.println("消费者收到了消息" + message);
           channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
       }catch (Exception e){
          e.printStackTrace();
           channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
       }
    }
}
