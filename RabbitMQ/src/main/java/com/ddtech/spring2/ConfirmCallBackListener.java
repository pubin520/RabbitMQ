package com.ddtech.spring2;




import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component("confirmCallBackListener")
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {


    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.err.println("确认回调 ack=="+ack+"   cause=="+cause);
    }
}
