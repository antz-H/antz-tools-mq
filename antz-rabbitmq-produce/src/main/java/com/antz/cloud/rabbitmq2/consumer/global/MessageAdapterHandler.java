package com.antz.cloud.rabbitmq2.consumer.global;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @program: antz-cloud-mq
 * @description:   消息处理代理类
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-30 13:55
 **/
public class MessageAdapterHandler implements MessageListener{


    @Override
    public void onMessage(Message message) {

    }
}
