package com.antz.cloud.rabbitmq.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-17 16:53
 **/
@Data
@Builder
public class Order implements Serializable{


    private static final long serialVersionUID = -2355333128422843217L;
    private String id ;

    private String name ;

    private String messageid ;


}
