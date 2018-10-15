package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.exchange.DirectDelaySupportExchange;
import org.springframework.amqp.core.*;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:37
 **/
public class MqQueueUtil {

    public static Queue createQueue(String queueName)
    {
        return new Queue(queueName, true);
    }

    public static Binding createBindingQueueToDefaultExchange(Queue queue)
    {
        return BindingBuilder.bind(queue).to(getApollDirectChange()).with(queue.getName());
    }

    public static Binding createDirectBindingQueue(DirectExchange exchange, Queue queue)
    {
        if ("apollo.exchange.direct".equals(exchange.getName())) {
            throw new RuntimeException("exchange 必须是 RabbitMqExchange 定义的变量,可以通过MqQueueUtil 获取exchange");
        }
        return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
    }

    public static Binding createTopicBindingQueue(TopicExchange exchange, Queue queue)
    {
        if ("apollo.exchange.direct".equals(exchange.getName())) {
            throw new RuntimeException("exchange 必须是 RabbitMqExchange 定义的变量,可以通过MqQueueUtil 获取exchange");
        }
        return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
    }

    public static DirectExchange getApollDirectChange() {
        return new DirectDelaySupportExchange("apollo.exchange.direct");
    }

}
