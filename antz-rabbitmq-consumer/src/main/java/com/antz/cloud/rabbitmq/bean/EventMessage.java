package com.antz.cloud.rabbitmq.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-08 15:35
 **/
@Data
@Builder
public class EventMessage implements Serializable {

    private static final long serialVersionUID = -3470412652119830149L;

    private String queueName;

    private String exchangeName;

    private String routingKey;

    private String consumerQueueName;

    private String consumerExchange;

    private byte[] eventData;

    private int type;
}
