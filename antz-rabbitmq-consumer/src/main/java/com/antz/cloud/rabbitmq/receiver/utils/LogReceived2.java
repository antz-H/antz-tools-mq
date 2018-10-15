package com.antz.cloud.rabbitmq.receiver.utils;

import com.antz.cloud.rabbitmq.bean.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 17:58
 **/
@Slf4j
//@Component
//@RabbitListener(queues = "log.queue")
public class LogReceived2 {
    @RabbitHandler
    public void process_comsumer2(byte[] message , Channel channel ,@Headers Map<String,Object> headers) throws IOException {
        channel.basicAck((long)(headers.get(AmqpHeaders.DELIVERY_TAG)),false);
        log.info("消费者2——消息队列，{}，接收到消息，{}","log.queue",ObjectAndByte.ByteToObject(message).toString());
    }


}
