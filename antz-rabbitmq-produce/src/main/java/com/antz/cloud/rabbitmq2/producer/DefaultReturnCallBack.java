package com.antz.cloud.rabbitmq2.producer;

import com.rabbitmq.client.Return;
import com.rabbitmq.client.ReturnCallback;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-10-29 00:15
 **/
public class DefaultReturnCallBack implements ReturnCallback {
    @Override
    public void handle(Return returnMessage) {
        returnMessage.getRoutingKey() ;
    }
}
