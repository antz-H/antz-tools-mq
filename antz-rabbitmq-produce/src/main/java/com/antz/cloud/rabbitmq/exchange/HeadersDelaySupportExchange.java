package com.antz.cloud.rabbitmq.exchange;

import org.springframework.amqp.core.HeadersExchange;

import java.util.Map;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:40
 **/
public class HeadersDelaySupportExchange extends HeadersExchange {
    public HeadersDelaySupportExchange(String name)
    {
        super(name);
    }
    public HeadersDelaySupportExchange(String name, boolean durable, boolean autoDelete) {
        super(name, durable, autoDelete);
    }
    public HeadersDelaySupportExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }
}
