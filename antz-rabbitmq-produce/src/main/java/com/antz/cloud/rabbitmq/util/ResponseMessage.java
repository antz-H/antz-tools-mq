package com.antz.cloud.rabbitmq.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * MQ返回消息体封装类
 * @program: swhysc-service-plugin
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-14 15:58
 **/
@Data
@ApiModel
public class ResponseMessage<T> implements Serializable{

    private static final long serialVersionUID = -1084897004898275273L;

    @ApiModelProperty(value = "是否成功")
    private boolean success;
    @ApiModelProperty(value = "成功、错误返回提示信息")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private T data;
    @ApiModelProperty(value = "新增、修改主鍵返回id")
    private String id;

}
