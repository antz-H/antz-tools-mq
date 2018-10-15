package com.antz.cloud;

import com.antz.cloud.rabbitmq.util.CodecFactory;
import com.antz.cloud.rabbitmq.util.DefaultCodecFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: antz-rabbitmq-consumer
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-15 09:48
 **/
@ComponentScan
@SpringBootApplication
public class RabbitMqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqConsumerApplication.class,args);
    }
}
