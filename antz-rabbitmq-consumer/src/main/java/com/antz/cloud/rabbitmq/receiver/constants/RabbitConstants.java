package com.antz.cloud.rabbitmq.receiver.constants;

/**
 * @program: swhysc-service-plugin
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-14 15:42
 **/
public class RabbitConstants {

    /**
     *  默认日志消息队列
     */
     public static final String LOG_MESSAGE_QUEUE = "logmessage";

    /**
     * 默认日志队列路由键（*表示一个词,#表示零个或多个词）
     */
    public static final String LOG_ROUTING_KEY = "log.key";

    /**
     * 默认交换机
     */
    public static final String CONTROL_EXCHANGE = "control.exchange";
    /**
     * 订单队列
     */
    public static final String ORDER_MESSAGE_QUEUE = "order";

    /**
     * 订单交换机
     */
    public static final String CONTROL_ORDER_EXCHANGE = "order.exchange" ;

    /**
     * Routing_Key
     */
    public static final String ORDER_ROUTING_KEY = "order.key";
}
