package com.antz.cloud.rabbitmq2.consumer.global;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-30 22:05
 **/
public class DefaultEventController {

    @Autowired
    private SimpleMessageListenerContainer msgListenerContainer;



}
