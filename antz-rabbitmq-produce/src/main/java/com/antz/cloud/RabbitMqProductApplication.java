package com.antz.cloud;

import com.antz.cloud.rabbitmq.exchange.FanoutDelaySupportExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: antz-rabbitmq-server
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-15 09:33
 **/
@ComponentScan(basePackages = {"com.antz.cloud"})
@SpringBootApplication
public class RabbitMqProductApplication {

    RabbitTemplate rabbitTemplate ;
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProductApplication.class,args);
    }

    public void test(){
        FanoutDelaySupportExchange fanoutExchange = new FanoutDelaySupportExchange("a.exchange");
    }
}
