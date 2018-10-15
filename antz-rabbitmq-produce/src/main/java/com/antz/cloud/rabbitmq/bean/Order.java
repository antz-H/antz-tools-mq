package com.antz.cloud.rabbitmq.bean;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-17 16:53
 **/
@Data
@Builder
@EqualsAndHashCode
public class Order implements Serializable,  Cloneable ,Comparable{

    private static final long serialVersionUID = -2355333128422843217L;

    private String id ;

    private String name ;

    private String messageid ;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
