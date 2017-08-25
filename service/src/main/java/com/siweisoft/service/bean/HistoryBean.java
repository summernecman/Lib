package com.siweisoft.service.bean;

//by summer on 17-08-23.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class HistoryBean extends BaseBean {

    private String name;

    private String date;

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
}
