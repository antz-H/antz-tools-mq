package com.antz.cloud.rabbitmq.util;



import java.io.IOException;

/**
 * @program: swhysc-service-plugin
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-22 13:11
 **/
public class DefaultFastJsonCodeFactory implements CodecFactory{
    @Override
    public byte[] serialize(Object obj) throws IOException {
        String str = JsonUtils.toJSONString(obj);
        return str.getBytes();
    }

    @Override
    public Object deSerialize(byte[] in) throws IOException {
        return JsonUtils.textToJson(new String(in)) ;
    }
}
