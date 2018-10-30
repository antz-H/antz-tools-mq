package com.antz.cloud.rabbitmq2.utils;

import com.antz.cloud.rabbitmq.bean.Log;
import com.antz.cloud.rabbitmq2.utils.impl.DefaultProducerEventTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DefaultProducerEventTemplateTest {

    @Autowired
    DefaultProducerEventTemplate defaultProducerEventTemplate ;

    @Test
    public void send() throws Exception {
        String msg = "这是一条测试信息" ;
       // defaultProducerEventTemplate.send(LogConstants.QUEUE_NAME,LogConstants.EXCHANGE_NAME,msg);
        Log logmsg = new Log() ;
        logmsg.setName("你好");
        logmsg.setMessageid("2323");
        defaultProducerEventTemplate.send(LogConstants.QUEUE_NAME,"wewew",logmsg);
    }

    @Test
    public void sendAndConfirm() throws Exception {
        String msg = "这是一条测试信息" ;
        boolean flag =  defaultProducerEventTemplate.sendAndConfirm(LogConstants.QUEUE_NAME,LogConstants.EXCHANGE_NAME,msg);
        System.out.println("返回发送结果:"+flag);
        Log logmsg = new Log() ;
        logmsg.setName("你好");
        logmsg.setMessageid("2323");
        flag = defaultProducerEventTemplate.sendAndConfirm(LogConstants.QUEUE_NAME,LogConstants.EXCHANGE_NAME,logmsg);
        System.out.println("第二次返回发送结果:"+flag);
    }

    @Test
    public void send1() throws Exception {
    }

    @Test
    public void sendAndConfirm1() throws Exception {
    }

}
