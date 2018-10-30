package com.antz.cloud.rabbitmq2.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-28 21:45
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "demeter.rabbitmq")
public class RabbitMqConfigProperties1 {

    private List<String> hostandports;
    private String username;
    private String password;
    private String virtualHost;
    private int connectionTimeout;
    private int threads ;

    private boolean publisherConfirms;
    private boolean publisherReturns;

}
