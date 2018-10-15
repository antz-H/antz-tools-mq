package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.bean.Order;
import com.antz.cloud.rabbitmq.constants.RabbitConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderFanoutTest {

    @Autowired
    SenderUtils senderUtils ;

    @Test
    public void fanoutTest(){
        Order order = Order.builder().id(UUID.randomUUID().toString())
                .name("订单信息")
                .messageid(String.valueOf(System.nanoTime())+UUID.randomUUID().toString()).build();
        senderUtils.send(order, RabbitConstants.CONTROL_FANOUT_EXCHANGE,RabbitConstants.FANOUT_ROUTING_KEY,order.getMessageid());

    }
}
