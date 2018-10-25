package com.antz.cloud.rabbitmq.receiver.utils;

import com.alibaba.fastjson.JSONObject;
import com.antz.cloud.rabbitmq.bean.EventMessage;
import com.antz.cloud.rabbitmq.bean.Log;
import com.antz.cloud.rabbitmq.bean.Order;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.springframework.amqp.core.Message;
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
@Component
@RabbitListener(queues = "log.queue")
public class LogReceived {

   /* @RabbitHandler
    public void process(Order order , Channel channel ,@Headers Map<String,Object> headers) throws IOException {
        channel.basicAck((long)(headers.get(AmqpHeaders.DELIVERY_TAG)),false);
        log.info("消息队列，{}，接收到消息，{}","log.queue",order.toString());
    }*/

   /* @RabbitHandler
    public void process(String message , Channel channel ,@Headers Map<String,Object> headers) throws IOException {
        channel.basicAck((long)(headers.get(AmqpHeaders.DELIVERY_TAG)),false);
        log.info("消息队列process，{}，接收到消息，{}","log.queue",message.toString());
    }
*/
    @RabbitHandler
    public void process_comsumer1(byte[] message , Channel channel ,@Headers Map<String,Object> headers) throws IOException, InterruptedException {
        channel.basicAck((long)(headers.get(AmqpHeaders.DELIVERY_TAG)),false);
        Log logs = MessageHandlerUtil.handlerMessage(message,new DefaultFastJsonCodeFactory(),Log.class) ;
        log.info("消费者process_comsumer1——消息队列，{}，接收到消息，{}","log.queue",logs.toString());
    }
   /* @RabbitHandler
    public void process_Object(Object message , Channel channel ,@Headers Map<String,Object> headers) throws IOException, InterruptedException {
        channel.basicAck((long)(headers.get(AmqpHeaders.DELIVERY_TAG)),false);
        Log logs = MessageHandlerUtil.handlerMessage(message,new DefaultFastJsonCodeFactory(),Log.class) ;
        log.info("消费者1——消息队列，{}，接收到消息，{}","log.queue",logs.toString());
    }*/

}
