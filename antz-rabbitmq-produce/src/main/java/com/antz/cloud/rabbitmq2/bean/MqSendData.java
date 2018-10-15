package com.antz.cloud.rabbitmq2.bean;

import java.io.Serializable;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:38
 **/
public class MqSendData  implements Serializable {
    private static final long serialVersionUID = 6680653147657618404L;
    private String tag;
    private Object data;
    private String remoteSynCompanyUuid;
    private String applicationName;
    private MqTransferUserInfo mqUserInfo;

    public String getApplicationName()
    {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getRemoteSynCompanyUuid() {
        return this.remoteSynCompanyUuid;
    }

    public void setRemoteSynCompanyUuid(String remoteSynCompanyUuid) {
        this.remoteSynCompanyUuid = remoteSynCompanyUuid;
    }

    public MqTransferUserInfo getMqUserInfo()
    {
        return this.mqUserInfo;
    }
    public void setMqUserInfo(MqTransferUserInfo mqUserInfo) {
        this.mqUserInfo = mqUserInfo;
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public Object getData() {
        return this.data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
