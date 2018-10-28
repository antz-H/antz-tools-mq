package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.bean.Log;
import com.antz.cloud.rabbitmq.demo.log.LogConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-08 12:19
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LogSenderTest {

    @Autowired
    SenderUtils senderUtils ;

    @Autowired
    DefaultProducerEventTemplate defaultProducerEventTemplate ;

    @Test
    public void logTest(){
        String value = "这是一条测试信息" ;
        senderUtils.send(value, LogConstants.EXCHANGE_NAME,LogConstants.QUEUE_NAME);
    }

    @Test
    public void logTest2() throws Exception {
        for(int i = 0 ;i <10 ; i++){
            Log log = new Log();
            String message = "这是一条测试日志"+i;
            log.setName(message);
            log.setMessageid(String.valueOf(i));
            defaultProducerEventTemplate.send( LogConstants.QUEUE_NAME,LogConstants.EXCHANGE_NAME,log);
        }

    }

    @Test
    public void logTest3() throws Exception {
        for(int i = 0 ;i <10 ; i++){
            Log log = new Log();
            String message = "这是一条测试日志"+i;
            defaultProducerEventTemplate.send( LogConstants.QUEUE_NAME,LogConstants.EXCHANGE_NAME,message);
        }

    }

}
