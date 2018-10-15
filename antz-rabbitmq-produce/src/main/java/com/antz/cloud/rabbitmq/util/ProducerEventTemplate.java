package com.antz.cloud.rabbitmq.util;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @create: 2018-10-08 13:46
 **/
public interface ProducerEventTemplate {

    /**
     * 普通
     * @param queueName 队列名
     * @param exchangeName 交换机
     * @param eventContent 发送对象
     * @throws Exception
     */
    void send(String queueName,String exchangeName,Object eventContent) throws Exception;

    /**
     * 带路由的普通消费
     * @param queueName   队列名
     * @param exchangeName 交换机
     * @param routing   路由
     * @param eventContent 发送对象
     * @throws Exception
     */
    void send(String queueName,String exchangeName,String routing,Object eventContent) throws Exception;

    /**
     * 普通消息，对消息进行过期设置
     * @param queueName 队列名
     * @param exchangeName 交换机
     * @param eventContent 发送对象
     * @param expiration  过期时间
     * @param priority 优先级
     * @throws Exception
     */
    void send(String queueName, String exchangeName, Object eventContent,int expiration,int priority) throws Exception;
    /**
     * 路由消息，对消息进行过期设置
     * @param queueName  队列名
     * @param exchangeName 交换机
     * @param routing 路由
     * @param eventContent  发送对象
     * @param expiration 过期时间
     * @param priority  优先级
     * @throws Exception
     */
    void send(String queueName, String exchangeName, String routing,Object eventContent,int expiration,int priority) throws Exception;

    /**
     * 延迟消费
     * @param queueName  队列名
     * @param exchangeName 交换机
     * @param consumerQueueName 消费者队列
     * @param consumerExchange 消费交换机
     * @param routing 路由 生产者
     * @param eventContent  发送对象
     * @throws Exception
     */
    void send(String queueName,String exchangeName,String consumerQueueName,String consumerExchange,String routing,Object eventContent) throws Exception;
    /**
     * 延迟消费
     * @param queueName  队列名
     * @param exchangeName 交换机
     * @param consumerQueueName 消费者队列
     * @param consumerExchange 消费交换机
     * @param routing 路由 生产者
     * @param eventContent  发送对象
     * @param expiration  过期时间
     * @param priority 优先级
     * @throws Exception
     */
    void send(String queueName,String exchangeName,String consumerQueueName,String consumerExchange,String routing,Object eventContent,int expiration,int priority) throws Exception;

}
