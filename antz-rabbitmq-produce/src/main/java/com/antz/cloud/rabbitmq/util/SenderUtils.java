package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.constants.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @program: swhysc-service-plugin
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-14 15:39
 **/
@Slf4j
@Component
public class SenderUtils<T> {

    @Autowired
    RabbitTemplate rabbitTemplate ;


    /**
     * 发送信息到日志队列
     *
     * @param msg
     */
    public void sendLogMessage(String[] msg) {
        send(msg, RabbitConstants.CONTROL_EXCHANGE, RabbitConstants.LOG_ROUTING_KEY);
    }


    /**
     * 发送消息
     * @param msg        消息
     * @param exchange   交换机
     * @param routingKey 路由键
     */
    public void send(String[] msg, String exchange, String routingKey) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        printLog(exchange,routingKey,correlationId,msg);
        Object response =  rabbitTemplate.convertSendAndReceive(exchange, routingKey, msg, correlationId);
        if( response != null ){
            log.info("消费者消息确认:{}",response);
        }
    }

    /**
     *  message类型为字符串
     * @param value  消息
     * @param exchange 交换机
     * @param routingKey  路由键
     */
    public void send(String value , String exchange ,String routingKey){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        printLog(exchange,routingKey,correlationId,value);
        rabbitTemplate.convertSendAndReceive(exchange,routingKey,value,correlationId);
    }

    /**
     * message 类型为泛型，但必须实现Serializable接口
     * @param value     Message ，该对象需要进行序列化
     * @param exchange  交换机
     * @param routingKey 路由键
     * @param messageId 可以为null，则提供默认的uuid
     */
    public void send(T value , String exchange ,String routingKey ,String messageId){
        CorrelationData correlationId = null ;
        if( StringUtils.isBlank(messageId) ){
            correlationId = new CorrelationData(UUID.randomUUID().toString());
        }else{
            correlationId = new CorrelationData(messageId);
        }
        printLog(exchange,routingKey,correlationId,value);
        rabbitTemplate.convertSendAndReceive(exchange,routingKey,value,correlationId);
    }

    /**
     * message 类型为泛型，但必须实现Serializable接口 ，支持消息确认
     * @param value     Message ，该对象需要进行序列化
     * @param exchange  交换机
     * @param routingKey 路由键
     * @param messageId 可以为null，则提供默认的uuid
     * @param confirmCallback  消息确认回调
     */
    public void send(T value , String exchange ,String routingKey ,String messageId,ConfirmCallback confirmCallback){
        if( confirmCallback != null ){
            rabbitTemplate.setConfirmCallback(confirmCallback);
        }
        CorrelationData correlationId = null ;
        if( StringUtils.isBlank(messageId) ){
            correlationId = new CorrelationData(UUID.randomUUID().toString());
        }else{
            correlationId = new CorrelationData(messageId);
        }
        printLog(exchange,routingKey,correlationId,value);
        rabbitTemplate.convertSendAndReceive(exchange,routingKey,value,correlationId);
    }

    /**
     * 自行封装Message类型
     * @param exchange  交换机
     * @param routingKey 路由键
     * @param message 自行封装Message消息体
     */
    public  void  send(String exchange , String routingKey ,Message message ){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        printLog(exchange,routingKey,correlationId,message);
        rabbitTemplate.send(exchange,routingKey,message,correlationId);
    }

    private <T> void printLog(String exchange,String routingKey,CorrelationData correlationData,T message){
        log.info("发送消息:交换机:{},路由键:{},消息标识:{},消息:{}",exchange,routingKey,correlationData.getId(),message != null ? message.toString() : "" );
    }
}
