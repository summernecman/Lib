package com.siweisoft.service.bean;

//by summer on 2017-07-12.

import com.android.lib.network.bean.req.BaseReqBean;

public class UserReqBean extends BaseReqBean {
    private String phone;

    private String username;

    private String pwd;

    private Integer iscustomer;

    private String belong;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getIscustomer() {
        return iscustomer;
    }

    public void setIscustomer(Integer iscustomer) {
        this.iscustomer = iscustomer;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }
}
