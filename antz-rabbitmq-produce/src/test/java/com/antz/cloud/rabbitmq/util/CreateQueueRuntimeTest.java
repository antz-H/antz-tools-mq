package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.exchange.TopicDelaySupportExchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 16:47
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateQueueRuntimeTest {

    @Autowired
    AmqpTemplate amqpTemplate ;
    @Autowired
    AmqpAdmin amqpAdmin ;
    @Autowired
    RabbitTemplate rabbitTemplate ;
    @Test
    public void createQueueTest(){
        /*Queue queue = MqQueueUtil.createQueue("huang");
        DirectExchange directExchange = new DirectDelaySupportExchange("huang.exchange",true,false);
        Binding binding = MqQueueUtil.createDirectBindingQueue(directExchange,queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);*/

        Queue queue = MqQueueUtil.createQueue("huang.queue");
        TopicExchange topicExchange = new TopicDelaySupportExchange("huang.topic.exchange",true,false);
        Binding binding = MqQueueUtil.createTopicBindingQueue(topicExchange,queue) ;
        amqpAdmin.declareExchange(topicExchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }

}
