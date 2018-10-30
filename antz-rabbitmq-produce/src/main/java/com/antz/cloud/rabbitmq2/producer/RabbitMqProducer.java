package com.antz.cloud.rabbitmq2.producer;

import com.antz.cloud.rabbitmq.util.JsonUtils;
import com.antz.cloud.rabbitmq2.producer.global.ProducerTemplate;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-28 23:05
 **/
@Slf4j
@Configuration
public class RabbitMqProducer implements ProducerTemplate {

    @Autowired
    @Qualifier("RabbitMqConnection")
    private Connection connection ;

    /**
     *
     * @param queueName      消息队列
     * @param exchangeName   交换机
     * @param routingKey     路由键
     * @param eventContent   消息
     * @param isConfirm      是否消息确认
     * @return
     */
    public boolean send(String queueName, String exchangeName,String routingKey, Object eventContent,boolean isConfirm){
        Channel channel = getChannel();
        if( channel == null ){
            return false ;
        }
        byte[] body = null  ;
        if( eventContent instanceof String ){
            body = ((String) eventContent).getBytes() ;
        }else{
            body = JsonUtils.toJSONString(eventContent).getBytes() ;
        }
        try {
            //将信道置为publisher confirm 模式，开启消息异步确认机制
            if( isConfirm ){
                channel.confirmSelect() ;
            }
           /**
            *   rabbitmq机理
            *   1、默认情况下mandatory=false，表示交换机无法找到绑定的queue，则消息直接被丢弃 ，true 会回调ReturnListener接口
            *   2、默认情况下immediate=false,表示交换机将消息发送到队列时，发现无消费者，那该消息不会存到当前队列上；
            *    当与routingkey绑定的所有队列都没有消费者时，该消息会通过Basic.Return返回给生产者。此参数在rabbit3.0已废弃，所以并不影响
            */
            //增加mandatory=true
            channel.addReturnListener(new DefaultReturnListener());
            //channel.addReturnListener(new DefaultReturnCallBack()) ;
            channel.basicPublish(exchangeName,routingKey,true,MessageProperties.PERSISTENT_TEXT_PLAIN,body);
            //若进行消息确认，则可返回是否确认成功
            if( isConfirm ){
                return channel.waitForConfirms() ;
            }
        } catch (IOException e) {
            log.error("rabbitmq消息发送网络IO异常",e);
            return false ;
        } catch (InterruptedException e) {
            log.error("rabbitmq等待消息确认IO网络异常",e);
            return false ;
        }finally {
            if(channel != null ){
                try {
                    channel.close();
                } catch (IOException e) {
                    log.error("rabbitmq关闭连接网络IO异常",e);
                } catch (TimeoutException e) {
                    log.error("rabbitmq关闭连接超时异常",e);
                }
            }
        }
        return false ;
    }

    private Channel getChannel(){
        try {
            return  connection.createChannel() ;
        } catch (IOException e) {
            log.error("rabbitmq获取连接异常",e);
            return null ;
        }
    }

}
