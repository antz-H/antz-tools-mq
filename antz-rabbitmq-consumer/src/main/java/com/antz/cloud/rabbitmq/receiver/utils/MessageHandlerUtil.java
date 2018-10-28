package com.antz.cloud.rabbitmq.receiver.utils;

import com.alibaba.fastjson.JSONObject;
import com.antz.cloud.rabbitmq.bean.EventMessage;
import com.antz.cloud.rabbitmq.util.CodecFactory;
import com.antz.cloud.rabbitmq.util.DefaultCodecFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-25 23:41
 **/
@Slf4j
public class MessageHandlerUtil {

    //默认序列化工厂
    private static CodecFactory defalutCodecFactorys = new DefaultFastJsonCodeFactory();

    public static <T> T handlerMessage(byte[] messageBytes , CodecFactory codecFactory , Class<T> returnType) throws IOException {
        if( codecFactory != null ){
            defalutCodecFactorys = codecFactory ;
        }
        EventMessage eventMessage = (EventMessage) ObjectAndByte.ByteToObject(messageBytes) ;
        byte[] bytes = eventMessage.getEventData() ;
        try{
            Object o = defalutCodecFactorys.deSerialize(bytes) ;
            if(o instanceof JSONObject){
                return ((JSONObject)o).toJavaObject(returnType);
            }
            return (T)defalutCodecFactorys.deSerialize(bytes);
        }catch (Exception e){
            log.error("RabbitMQ接收消息处理异常，当前工具类仅支持DefaultFastJsonCodeFactory和DefaultCodecFactory序列化工厂类",e);
        }
        return null;
    }

    public static <T> T handlerMessage(Object message , CodecFactory codecFactory , Class<T> returnType) throws IOException {
        Message mqMessage = (Message)message ;
       return  handlerMessage(mqMessage.getBody(),codecFactory,returnType);
    }
}
