package com.antz.cloud.rabbitmq.demo.log;

import com.antz.cloud.rabbitmq.exchange.TopicDelaySupportExchange;
import com.antz.cloud.rabbitmq.util.CodecFactory;
import com.antz.cloud.rabbitmq.util.DefaultCodecFactory;
import com.antz.cloud.rabbitmq.util.DefaultFastJsonCodeFactory;
import com.antz.cloud.rabbitmq.util.MqQueueUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 17:30
 **/
@Component
public class LogMqUtils {

    @Autowired
    AmqpAdmin amqpAdmin ;

    @Autowired
    RabbitTemplate rabbitTemplate ;

    @Bean("logQueue")
    public Queue createQueue(){
         return  MqQueueUtil.createQueue(LogConstants.QUEUE_NAME) ;
    }

    @Bean("logExchange")
    public TopicExchange createExchange(){
        return new TopicDelaySupportExchange(LogConstants.EXCHANGE_NAME,true,false);
    }

    @Bean("logBinding")
    public Binding bindingTopicExchange(@Qualifier("logQueue") Queue logQueue , @Qualifier("logExchange") TopicExchange  logExchange){
        return MqQueueUtil.createTopicBindingQueue(logExchange,logQueue);
    }
    @Bean
    public String  createTopicExchange(@Qualifier("logQueue") Queue logQueue ,
                                     @Qualifier("logExchange") TopicExchange  logExchange,
                                     @Qualifier("logBinding") Binding logBinding ){
        amqpAdmin.declareQueue(logQueue);
        amqpAdmin.declareExchange(logExchange);
        amqpAdmin.declareBinding(logBinding);
        return null ;
    }

    @Bean
    public CodecFactory defineDefaultCodecFactory(){
        CodecFactory codecFactory = new DefaultFastJsonCodeFactory();
        return codecFactory ;
    }

}
