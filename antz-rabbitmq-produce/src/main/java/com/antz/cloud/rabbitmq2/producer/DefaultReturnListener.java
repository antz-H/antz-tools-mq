package com.antz.cloud.rabbitmq2.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-28 23:56
 **/
@Slf4j
public class DefaultReturnListener implements ReturnListener {

    @Autowired
    @Qualifier("RabbitMqConnection")
    private Connection connection ;

    @Override
    public void handleReturn(int replyCode, String replyText, String exchange,
                             String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
        Channel channel ;
        try {
            channel = connection.createChannel() ;
        } catch (IOException e) {
            log.error("rabbitmq获取连接异常",e);
            throw new RuntimeException("rabbitmq获取连接异常") ;
        }
       /* //声明交换机
        channel.exchangeDeclare(exchange, "fanout", durable);
        //声明队列
        channel.queueDeclare(queueName, durable, false, false, null);
        //队列绑定交换机
        channel.queueBind(queueName, exchange, "");*/
    }
}
