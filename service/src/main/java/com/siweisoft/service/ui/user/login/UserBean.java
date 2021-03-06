package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class UserBean extends BaseBean {

    private String chatid;

    private int id;

    private String phone;

    private String name;

    private String pwd;

    private Integer usertype;

    private String belong;

    private String headurl = "";

    private float rate;

    private String uuuid;


    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Bindable
    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    @Bindable
    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    @Bindable
    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    @Bindable
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getUuuid() {
        return uuuid;
    }

    public void setUuuid(String uuuid) {
        this.uuuid = uuuid;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "chatid='" + chatid + '\'' +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", usertype=" + usertype +
                ", belong='" + belong + '\'' +
                ", headurl='" + headurl + '\'' +
                ", rate=" + rate +
                ", uuuid='" + uuuid + '\'' +
                '}';
    }
}
