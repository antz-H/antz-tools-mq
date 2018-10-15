package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.bean.Order;
import com.antz.cloud.rabbitmq.demo.log.LogConstants;
import com.antz.cloud.rabbitmq.demo.log.LogMqUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 17:41
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogMqUtilsTest {

    @Autowired
    RabbitTemplate rabbitTemplate ;


    @Test
    public void logTopicTest() {
       // while (true) {
            Order order = Order.builder().id(UUID.randomUUID().toString())
                    .name("订单信息")
                    .messageid(String.valueOf(System.nanoTime()) + UUID.randomUUID().toString()).build();
            rabbitTemplate.convertAndSend(LogConstants.EXCHANGE_NAME, LogConstants.QUEUE_NAME, order, new CorrelationData(order.getMessageid()));
       // }
    }
}
