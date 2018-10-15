package com.antz.cloud.rabbitmq.pull.configuration;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DefaultChannelCallBackTest {

    @Autowired
    RabbitTemplate rabbitTemplate ;

    @Autowired
    RabbitAdmin rabbitAdmin ;

    @Test
    public void doInRabbitTest() throws Exception {
        String queueName = "log.queue" ;
        rabbitTemplate.execute(new DefaultChannelCallBack(queueName,false));
        Properties p = rabbitAdmin.getQueueProperties(queueName);
        log.info("消息的个数：{}",p.get(RabbitAdmin.QUEUE_MESSAGE_COUNT));
        //todo
        //多线程下同时消费拉取，是否会出现消息重复消费问题，需要test
    }

    @Test
    public void doInRabbitMulThreadTest() throws InterruptedException {
        String queueName = "log.queue" ;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for( int i = 0 ; i < 10 ;i ++){
            fixedThreadPool.execute(()-> {
                rabbitTemplate.execute(new DefaultChannelCallBack(queueName,false));
                System.out.println("执行");
            });
        }

        Thread.sleep(3000l);
      /*  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();*/
    }

    @Test
    public void doReceiveMessage(){

        String queueName = "log.queue" ;
        Message message = rabbitTemplate.receive(queueName);
        if(message != null ){
            throw  new RuntimeException();
        }

    }





}
