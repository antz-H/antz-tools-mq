package com.antz.cloud.rabbitmq.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @program: swhysc-service-plugin
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-14 15:25
 **/
@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.rabbitmq.host")
public class RabbitMqConfig {

    @Autowired
    RabbitMqConfigProperties rabbitMqConfigProperties;


    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            if(ack){
                log.info("交换机exchange消息确认:成功,消息id：{}",correlationData.getId());
            }else{
                log.error("交换机exchange消息确认:失败,消息id："+correlationData.getId()+",异常：{}",cause);
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback(){
        /**
         * exchange与queue未绑定异步回调
         */
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            log.error("异常回调,消息:{} 发送失败, 应答码:{} 原因:{} 交换机:{} 路由键:{}", message, replyCode, replyText, exchange, routingKey);
        }
    };


    @Primary
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitMqConfigProperties.getHost() + ":" + rabbitMqConfigProperties.getPort());
        connectionFactory.setUsername(rabbitMqConfigProperties.getUsername());
        connectionFactory.setPassword(rabbitMqConfigProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMqConfigProperties.getVirtualHost());
        connectionFactory.setConnectionTimeout(rabbitMqConfigProperties.getConnectionTimeout());
        //如果要进行消息回调,则这里必须要设置为true
        connectionFactory.setPublisherConfirms(rabbitMqConfigProperties.isPublisherConfirms());
        connectionFactory.setPublisherReturns(rabbitMqConfigProperties.isPublisherConfirms());
        return connectionFactory;
    }
    @Primary
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        //相应交换机接收后异步回调
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        return rabbitTemplate;
    }
}
