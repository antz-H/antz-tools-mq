package com.antz.cloud.rabbitmq2.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: swhysc-service-plugin
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-14 15:08
 **/
@Data
//@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqConfigProperties {

    private String host;
    private String port;
    private String username;
    private String password;
    private String virtualHost;
    private int connectionTimeout;
    private boolean publisherConfirms;
    private boolean publisherReturns;


}
