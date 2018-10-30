package com.antz.cloud.rabbitmq2.exchange;

import org.springframework.amqp.core.FanoutExchange;

import java.util.Map;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:40
 **/
public class FanoutDelaySupportExchange  extends FanoutExchange {
    public FanoutDelaySupportExchange(String name)
    {
        super(name);
    }
    public FanoutDelaySupportExchange(String name, boolean durable, boolean autoDelete) {
        super(name, durable, autoDelete);
    }
    public FanoutDelaySupportExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }
}
