package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.bean.EventMessage;
import com.rabbitmq.client.Delivery;
import com.rabbitmq.client.impl.AMQImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-08 13:50
 **/
//@Component
@Slf4j
public class DefaultProducerEventTemplate implements ProducerEventTemplate {

    @Autowired
    private CodecFactory defaultCodecFactory ;

    @Autowired
    private RabbitTemplate rabbitTemplate ;

    /**
     * 普通
     *
     * @param queueName    队列名
     * @param exchangeName 交换机
     * @param eventContent 发送对象
     * @throws Exception
     */
    @Override
    public void send(String queueName, String exchangeName, Object eventContent) throws Exception {
        this.send(queueName,exchangeName,null,null,eventContent,defaultCodecFactory,0,queueName,0,0);
    }

    /**
     * 带路由的普通消费
     *
     * @param queueName    队列名
     * @param exchangeName 交换机
     * @param routing      路由
     * @param eventContent 发送对象
     * @throws Exception
     */
    @Override
    public void send(String queueName, String exchangeName, String routing, Object eventContent) throws Exception {
        this.send(queueName,exchangeName,null,null,eventContent,defaultCodecFactory,0,routing,0,0);
    }

    /**
     * 普通消息，对消息进行过期设置
     *
     * @param queueName    队列名
     * @param exchangeName 交换机
     * @param eventContent 发送对象
     * @param expiration   过期时间
     * @param priority     优先级
     * @throws Exception
     */
    @Override
    public void send(String queueName, String exchangeName, Object eventContent, int expiration, int priority) throws Exception {
        this.send(queueName,exchangeName,null,null,eventContent,defaultCodecFactory,0,queueName,expiration,priority);
    }

    /**
     * 路由消息，对消息进行过期设置
     *
     * @param queueName    队列名
     * @param exchangeName 交换机
     * @param routing      路由
     * @param eventContent 发送对象
     * @param expiration   过期时间
     * @param priority     优先级
     * @throws Exception
     */
    @Override
    public void send(String queueName, String exchangeName, String routing, Object eventContent, int expiration, int priority) throws Exception {
        this.send(queueName,exchangeName,null,null,eventContent,defaultCodecFactory,0,routing,expiration,priority);
    }

    /**
     * 延迟消费
     *
     * @param queueName         队列名
     * @param exchangeName      交换机
     * @param consumerQueueName 消费者队列
     * @param consumerExchange  消费交换机
     * @param routing           路由 生产者
     * @param eventContent      发送对象
     * @throws Exception
     */
    @Override
    public void send(String queueName, String exchangeName, String consumerQueueName, String consumerExchange, String routing, Object eventContent) throws Exception {
        this.send(queueName,exchangeName,consumerQueueName,consumerExchange,eventContent,defaultCodecFactory,0,routing,0,0);
    }

    /**
     * 延迟消费
     *
     * @param queueName         队列名
     * @param exchangeName      交换机
     * @param consumerQueueName 消费者队列
     * @param consumerExchange  消费交换机
     * @param routing           路由 生产者
     * @param eventContent      发送对象
     * @param expiration        过期时间
     * @param priority          优先级
     * @throws Exception
     */
    @Override
    public void send(String queueName, String exchangeName, String consumerQueueName,
                     String consumerExchange, String routing, Object eventContent,
                     int expiration, int priority) throws Exception {
        this.send(queueName,exchangeName,consumerQueueName,consumerExchange,eventContent,defaultCodecFactory,0,routing,expiration,priority);
    }

    /**
     *
     * @param queueName         队列名
     * @param exchangeName      交换机
     * @param consumerQueueName 消费者队列
     * @param consumerExchange  消费交换机
     * @param eventContent      发送对象
     * @param codecFactory      序列化工厂类
     * @param type              0: 普通 1:以RPC方式调用，可等待消息响应
     * @param routingKey        路由 生产者
     * @param expiration        过期时间
     * @param priority          优先级
     * @return
     */
    private Object send(String queueName, String exchangeName, String consumerQueueName,
                        String consumerExchange, Object eventContent, CodecFactory codecFactory,
                        int type, String routingKey, int expiration, int priority) throws Exception{

        if (StringUtils.isEmpty(queueName) || StringUtils.isEmpty(exchangeName) || StringUtils.isEmpty(routingKey)) {
            //todo
            throw new Exception("queueName exchangeName routingKey can not be empty.");
        }
        byte[] eventContentBytes = null;
        if (codecFactory == null) {
            if (eventContent == null) {
                log.warn("Find eventContent is null,are you sure...");
            } else {
                throw new Exception(
                        "codecFactory must not be null ,unless eventContent is null");
            }
        } else {
            try {
                eventContentBytes = codecFactory.serialize(eventContent);
            } catch (IOException e) {
                throw new Exception(e);
            }
        }
        Object obj=null;

        EventMessage eventMessage = EventMessage.builder().queueName(queueName)
                                    .exchangeName(exchangeName)
                                    .consumerQueueName(consumerQueueName)
                                    .consumerExchange(consumerExchange)
                                    .routingKey(routingKey)
                                    .eventData(eventContentBytes)
                                    .type(type).build();
        MessageProperties messageProperties = new MessageProperties();
        //过期时间
        if( expiration > 0 ){
            messageProperties.setExpiration(String.valueOf(expiration));
        }
        //消息优先级
        if( priority > 0 ){
            messageProperties.setPriority(Integer.valueOf(priority));
        }
        Message message = new Message(ObjectAndByte.ObjectToByte(eventMessage),messageProperties);
        try{
            CorrelationData  correlationId = new CorrelationData(UUID.randomUUID().toString());
            if( type == 0){
                rabbitTemplate.send(exchangeName,routingKey,message,correlationId);
            }else if( type == 1){
                //消费者有消息响应,不推荐使用，可能会影响队列性能
                obj = rabbitTemplate.convertSendAndReceive(exchangeName,eventMessage,correlationId);
            }
        }catch (AmqpException e){
            log.error("send event fail. Event Message : [" + eventContent + "]", e);
            throw new Exception("send event fail", e);
        }
        return obj ;
    }


}
