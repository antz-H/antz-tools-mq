package com.antz.cloud.rabbitmq.util;


import com.antz.cloud.RabbitMqProductApplication;
import com.antz.cloud.rabbitmq.bean.Order;
import com.antz.cloud.rabbitmq.constants.RabbitConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitMqProductApplication.class)
public class SenderUtilsTest {

    @Autowired
    SenderUtils senderUtils ;

    @Test
    public void sendLogMessageTest(){
        String[] strings = new String[]{"hello","你好"};
        String[] strings1 = new String[]{"hello","不好"};

        while (true){
            senderUtils.sendLogMessage(strings) ;
            senderUtils.send(strings1, RabbitConstants.CONTROL_EXCHANGE, RabbitConstants.LOG_ROUTING_KEY);
        }
    }

    @Test
    public void sendLogMessageTestOnce(){
        while (true) {
            String[] strings = new String[]{"hello", "你好"};
            String[] strings1 = new String[]{"hello", "不好"};
            //senderUtils.sendLogMessage(strings) ;
            senderUtils.send(strings1, RabbitConstants.CONTROL_EXCHANGE, RabbitConstants.LOG_ROUTING_KEY);
        }
    }

    @Test
    public void sendLogMessageTestOnce1(){
        String[] strings = new String[]{"hello",String.valueOf(System.nanoTime())};
        String[] strings1 = new String[]{"hello",String.valueOf(System.nanoTime())};
       // senderUtils.sendLogMessage(strings) ;
        senderUtils.send(strings1, RabbitConstants.CONTROL_EXCHANGE,"once");
    }

    @Test
    public void sendOrderMessage(){
        while (true){
            Order order = Order.builder().id(UUID.randomUUID().toString())
                    .name("订单信息")
                    .messageid(String.valueOf(System.nanoTime())+UUID.randomUUID().toString()).build();
            senderUtils.send(order,RabbitConstants.CONTROL_ORDER_EXCHANGE,RabbitConstants.ORDER_ROUTING_KEY,order.getMessageid());
        }
    }


}
