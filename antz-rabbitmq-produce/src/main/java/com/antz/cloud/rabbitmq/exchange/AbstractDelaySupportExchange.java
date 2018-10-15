package com.antz.cloud.rabbitmq.exchange;

import org.springframework.amqp.core.AbstractExchange;

import java.util.Map;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:39
 **/
public abstract class AbstractDelaySupportExchange  extends AbstractExchange
{
    public AbstractDelaySupportExchange(String name)
    {
        this(name, true, false);
    }

    public AbstractDelaySupportExchange(String name, boolean durable, boolean autoDelete) {
        this(name, durable, autoDelete, null);
    }

    public AbstractDelaySupportExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
        getArguments().put("x-delayed-type", getDelayedType());
    }

    public abstract String getDelayedType();

    public final String getType() {
        return "x-delayed-message";
    }
}
