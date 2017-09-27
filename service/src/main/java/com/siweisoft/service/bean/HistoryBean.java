package com.siweisoft.service.bean;

//by summer on 17-08-23.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.siweisoft.service.netdb.user.UserBean;

public class HistoryBean extends BaseBean {

    private String name;

    private String date;

    private UserBean userBean;

    private int id;

    private int num;



    public HistoryBean(String name, String date) {
        this.name = name;
        this.date = date;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Bindable
    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
