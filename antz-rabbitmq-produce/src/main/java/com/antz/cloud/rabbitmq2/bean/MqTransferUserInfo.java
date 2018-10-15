package com.antz.cloud.rabbitmq2.bean;

import java.io.Serializable;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-18 15:38
 **/
public class MqTransferUserInfo implements Serializable {
    private static final long serialVersionUID = 4129317254154030666L;

    private String userToken;
    private Long dbServerId;
    private String dbServerUrl;
    private String dbServerUserName;
    private String dbServerPassword;
    private String companyDbName;
    private String companyUuid;

    public String getCompanyUuid()
    {
        return this.companyUuid;
    }
    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid;
    }
    public String getUserToken() {
        return this.userToken;
    }
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    public Long getDbServerId() {
        return this.dbServerId;
    }
    public void setDbServerId(Long dbServerId) {
        this.dbServerId = dbServerId;
    }
    public String getDbServerUrl() {
        return this.dbServerUrl;
    }
    public void setDbServerUrl(String dbServerUrl) {
        this.dbServerUrl = dbServerUrl;
    }
    public String getDbServerUserName() {
        return this.dbServerUserName;
    }
    public void setDbServerUserName(String dbServerUserName) {
        this.dbServerUserName = dbServerUserName;
    }
    public String getDbServerPassword() {
        return this.dbServerPassword;
    }
    public void setDbServerPassword(String dbServerPassword) {
        this.dbServerPassword = dbServerPassword;
    }
    public String getCompanyDbName() {
        return this.companyDbName;
    }
    public void setCompanyDbName(String companyDbName) {
        this.companyDbName = companyDbName;
    }
}
