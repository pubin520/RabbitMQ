package com.ddtech.uitl;



import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 用于创建连接的工具类
 */
public class ConnextionUtil {
     public static Connection getConnection () throws  Exception{
         ConnectionFactory connectionFactory = new ConnectionFactory();
         connectionFactory.setHost("192.168.157.133");//设置 server 的地址
         connectionFactory.setPort(5672);
         connectionFactory.setUsername("luke");
         connectionFactory.setPassword("luke");
         connectionFactory.setVirtualHost("/test");
         return connectionFactory.newConnection();//创建一个新的连接
     }
}
