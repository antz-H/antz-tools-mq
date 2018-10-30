package com.antz.cloud.rabbitmq2.producer.global;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-28 23:07
 **/
public interface ProducerTemplate {

     boolean send(String queueName, String exchangeName,String routingKey, Object eventContent,boolean isConfirm) ;
}
