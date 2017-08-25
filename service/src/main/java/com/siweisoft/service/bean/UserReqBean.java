package com.siweisoft.service.bean;

//by summer on 2017-07-12.

import com.android.lib.network.bean.req.BaseReqBean;

public class UserReqBean extends BaseReqBean {

    private String phone;

    private String name;

    private String pwd;

    private Integer usertype;

    private String belong;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}
