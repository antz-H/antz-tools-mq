package com.antz.cloud.rabbitmq.pull.configuration;

import com.antz.cloud.rabbitmq.bean.EventMessage;
import com.antz.cloud.rabbitmq.receiver.utils.ObjectAndByte;
import com.antz.cloud.rabbitmq.util.CodecFactory;
import com.antz.cloud.rabbitmq.util.DefaultCodecFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.ChannelCallback;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-08 23:32
 **/
@Slf4j
public class DefaultChannelCallBack<T> implements ChannelCallback<T> {
    private String queueName ;
    //是否自动消息确认
    private boolean autoAck ;

    private CodecFactory codecFactory = new DefaultCodecFactory();

    public DefaultChannelCallBack(String queueName, boolean autoAck ) {
        this.queueName = queueName;
        this.autoAck = autoAck;
    }

    @Override
    public T doInRabbit(Channel channel) throws Exception {
        GetResponse response ;
        response = channel.basicGet(queueName,autoAck);
        if(response == null ){
            return null ;
        }
        EventMessage eventMessage =  (EventMessage)ObjectAndByte.ByteToObject(response.getBody()) ;
        Object msg =  codecFactory.deSerialize(eventMessage.getEventData()) ;
        log.info("当前线程号：{}，pull拉取消息:{}",Thread.currentThread().getName(),msg.toString());
        Thread.sleep(2000l);
        channel.basicAck(response.getEnvelope().getDeliveryTag(),false);
        return null;
    }
}
