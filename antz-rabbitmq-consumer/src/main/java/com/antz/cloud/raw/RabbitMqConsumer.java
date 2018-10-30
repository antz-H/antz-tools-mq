package com.antz.cloud.raw;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-19 23:24
 **/
public class RabbitMqConsumer {

    public static final String QUEUE_NAME = "queue" ;
    public static final String EXCHANGE_NAME = "exchange" ;
    public static final String ROUTING_KEY = "route.key" ;
    public static final String BINDING_KEY = "bind.key" ;
    public static final String IP_ADDRESS = "118.25.35.116" ;
    public static final int PORT = 5672 ;

    public static void main(String[] args) {
      ConnectionFactory connectionFactory  = new ConnectionFactory();
        Address[] addresses = new Address[]{new Address(IP_ADDRESS,PORT)};
        try {
            Connection connection =  connectionFactory.newConnection(addresses) ;
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            Channel channel = connection.createChannel();
            channel.basicQos(64); //设置客户端最多接收未被ack 的消息的个数
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println( "recv message: " + new String(body)) ;

                          int i = new Random().nextInt(10);
                            System.out.println("计算出的随机数:"+ i+"=====DeliveryTag="+envelope.getDeliveryTag());
                         /* if("hello world5".equals(new String(body))){
                              System.out.println("拒绝");
                              channel.basicNack(envelope.getDeliveryTag(),true,true);
                          }else{*/
                              channel.basicAck(envelope.getDeliveryTag(),false);
                         // }

                }
            };
            channel.basicConsume(QUEUE_NAME,consumer);
            TimeUnit.SECONDS.sleep(5);
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
