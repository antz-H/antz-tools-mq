package com.antz.cloud.raw;

import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-19 23:09
 **/
public class RabbitMqProduct {
    public static final String QUEUE_NAME = "queue" ;
    public static final String EXCHANGE_NAME = "exchange3" ;
    public static final String ROUTING_KEY = "route.key" ;
    public static final String BINDING_KEY = "bind.key" ;
    public static final String IP_ADDRESS = "118.25.35.116" ;
    public static final int PORT = 5672 ;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        try {
            Connection connection = connectionFactory.newConnection() ;
            Channel channel = connection.createChannel() ;
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);
            //channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            //channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
           /* channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("handleAck---"+deliveryTag);
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("handleNack---"+deliveryTag);
                }
            });*/
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("消息确认---"+replyText);
                }
            });
            channel.addReturnListener((returnMessage)->{
                System.out.println("消息确认");
            });
         /* channel.addShutdownListener((shutdown)->{
              System.out.println("ShutdownSignalException异常错误："+shutdown.getReason().protocolMethodName());
          });*/
            String message = "Nihao" ;
           // channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,true,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }






}
