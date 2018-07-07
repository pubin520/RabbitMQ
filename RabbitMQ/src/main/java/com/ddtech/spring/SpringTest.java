package com.ddtech.spring;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTest {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        template.convertAndSend("spring 的消息");
         ((ClassPathXmlApplicationContext) context).destroy();
    }
}
