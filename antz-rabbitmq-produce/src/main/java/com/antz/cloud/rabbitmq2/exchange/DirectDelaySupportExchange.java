package com.antz.cloud.rabbitmq2.exchange;

import org.springframework.amqp.core.DirectExchange;

import java.util.Map;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:39
 **/
public class DirectDelaySupportExchange extends DirectExchange {
    public DirectDelaySupportExchange(String name)
    {
        super(name);
    }
    public DirectDelaySupportExchange(String name, boolean durable, boolean autoDelete) {
        super(name, durable, autoDelete);
    }
    public DirectDelaySupportExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }
}
