package com.ddtech.spring2;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component("returnCallBackListener")
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.err.println("失败 message=="+new String(message.getBody())+"  replyCode=="+replyCode+"  replyText"+replyText+"  exchange"+exchange+"  routingKey"+routingKey);
    }
}
