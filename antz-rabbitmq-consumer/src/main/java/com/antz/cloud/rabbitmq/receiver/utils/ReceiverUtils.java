package com.antz.cloud.rabbitmq.receiver.utils;

import com.antz.cloud.rabbitmq.bean.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: swhysc-service-plugin
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-14 15:52
 **/
@Slf4j
@Component
public class ReceiverUtils {
/*
    @RabbitListener(queues = RabbitConstants.LOG_MESSAGE_QUEUE)
    public void processLogMessage(String[] msg){
        //String message =  MessageFormat.format("接收到数据{}",msg[0]);
        log.info("接收来自{}队列的消息,{}",RabbitConstants.LOG_MESSAGE_QUEUE,msg[1]);
    }*/

/*    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.log.queue.name}",
                    durable = "${spring.rabbitmq.listener.log.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.log.exchange.name}",
                    durable ="${spring.rabbitmq.listener.log.exchange.durable}" ,
                    type = "${spring.rabbitmq.listener.log.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.log.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.log.key}")
            )
    public void processLogMessage2(String[] msg){
        //String message =  MessageFormat.format("接收到数据{}",msg[0]);
        log.info("============接收来自{}队列的消息,{}",msg[1]);
    }*/

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.log.queue.name}",
                    durable = "${spring.rabbitmq.listener.log.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.log.exchange.name}",
                    durable ="${spring.rabbitmq.listener.log.exchange.durable}" ,
                    type = "${spring.rabbitmq.listener.log.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.log.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.log.key}")
    )
    @RabbitHandler
    public void onMessage(@Payload String[] msg ,
                          @Headers Map<String,Object> headers,
                          Channel channel)throws Exception{
        log.info("onMessage接收队列消息，{}",msg[1]);
       /* if(true){
            throw new Exception();
        }*/
        Long deliverTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliverTag,false);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable ="${spring.rabbitmq.listener.order.exchange.durable}" ,
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}")
    )
    @RabbitHandler
    public void onMessageToManyConsumer(@Payload Order order,
                                        @Headers Map<String,Object> headers,
                                        Channel channel)throws Exception{
        log.info("onMessageToManyConsumer接收队列消息，{}",order.toString());
        Long deliverTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //channel.basicAck(deliverTag,false);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable ="${spring.rabbitmq.listener.order.exchange.durable}" ,
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}")
    )
    @RabbitHandler
    public void onMessageToManyConsumer1(@Payload Order order,
                                        @Headers Map<String,Object> headers,
                                        Channel channel)throws Exception{
        log.info("onMessageToManyConsumer1接收队列消息，{}",order.toString());
        Long deliverTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //channel.basicAck(deliverTag,false);
    }

    /**
     *  Direct Exchange 测试
     * @param order
     * @param headers
     * @param channel
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.directmessage.queue.name}",
                    durable = "${spring.rabbitmq.listener.directmessage.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.directmessage.exchange.name}",
                    durable ="${spring.rabbitmq.listener.directmessage.exchange.durable}" ,
                    type = "${spring.rabbitmq.listener.directmessage.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.directmessage.exchange.ignoreDeclarationExceptions}"))
    )
    @RabbitHandler
    public void onMessageDirectMessage(@Payload Order order,
                                         @Headers Map<String,Object> headers,
                                         Channel channel)throws Exception{
        log.info("Direct Exchange 测试接收数据，{}",order.toString());
    }


}
