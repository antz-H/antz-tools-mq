package com.antz.cloud.rabbitmq2.producer.utils;

import com.antz.cloud.rabbitmq2.producer.RabbitMqProducer;
import com.antz.cloud.rabbitmq2.producer.global.ProducerEventTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-28 22:14
 **/
@Slf4j
@Component
public class DefaultProducerEventTemplate implements ProducerEventTemplate {

    @Autowired
    RabbitMqProducer rabbitMqProducer ;

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
        rabbitMqProducer.send(queueName,exchangeName,queueName,eventContent,false);
    }

    /**
     * 普通
     *
     * @param queueName    队列名
     * @param exchangeName 交换机
     * @param eventContent 发送对象
     * @return 消息是否确认接收 true:消息已确认 ，false:消息未确认
     * @throws Exception
     */
    @Override
    public boolean sendAndConfirm(String queueName, String exchangeName, Object eventContent) throws Exception {
        return rabbitMqProducer.send(queueName,exchangeName,queueName,eventContent,true);
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
        rabbitMqProducer.send(queueName,exchangeName,routing,eventContent,false);
    }

    /**
     * 带路由的普通消费
     *
     * @param queueName    队列名
     * @param exchangeName 交换机
     * @param routing      路由
     * @param eventContent 发送对象
     * @return 消息是否确认接收 true:消息已确认 ，false:消息未确认
     * @throws Exception
     */
    @Override
    public boolean sendAndConfirm(String queueName, String exchangeName, String routing, Object eventContent) throws Exception {
        return  rabbitMqProducer.send(queueName,exchangeName,routing,eventContent,true);
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
    @Deprecated
    public void send(String queueName, String exchangeName, Object eventContent, int expiration, int priority) throws Exception {
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
    @Deprecated
    public void send(String queueName, String exchangeName, String routing, Object eventContent, int expiration, int priority) throws Exception {
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
    @Deprecated
    public void send(String queueName, String exchangeName, String consumerQueueName, String consumerExchange, String routing, Object eventContent) throws Exception {
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
    @Deprecated
    public void send(String queueName, String exchangeName, String consumerQueueName, String consumerExchange, String routing, Object eventContent, int expiration, int priority) throws Exception {
    }


}
