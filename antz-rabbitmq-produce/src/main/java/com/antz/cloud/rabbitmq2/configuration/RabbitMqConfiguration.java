package com.antz.cloud.rabbitmq2.configuration;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-28 21:44
 **/
@Configuration
public class RabbitMqConfiguration {

    @Autowired
    private RabbitMqConfigProperties1 config ;

    @Bean("RabbitMqConnection")
    public Connection connectionInit(){

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());
        factory.setVirtualHost(config.getVirtualHost());
        factory.setConnectionTimeout(config.getConnectionTimeout());
        //初始化线程数
        int threads = 5 ;
        if(config.getThreads() > 5){
            threads = config.getThreads() ;
        }
        Address[] addresses = new Address[config.getHostandports().size()];
        int i = 0 ;
        for(String address : config.getHostandports()){
            String[] addressAndPort = address.split(":") ;
            addresses[i] = new Address(addressAndPort[0],Integer.valueOf(addressAndPort[1]));
        }
        //初始化定长的连接池
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        try {
           return  factory.newConnection(executorService,addresses) ;
        } catch (IOException e) {
            throw new RuntimeException("rabbitmq 初始连接异常");
        } catch (TimeoutException e) {
            throw new RuntimeException("rabbitmq 初始连接超时异常");
        }
    }
}
