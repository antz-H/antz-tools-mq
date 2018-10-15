package com.antz.cloud.rabbitmq.util;

import java.io.IOException;

/**
 * @program: antz-cloud-mq
 * @description: 序列化和反序列化工厂
 * @author: huanghuang@rewin.com.cn
 * @create: 2018-10-08 13:54
 **/
public interface CodecFactory {
    byte[] serialize(Object obj) throws IOException;
    Object deSerialize(byte[] in) throws IOException;
}
