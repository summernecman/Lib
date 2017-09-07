package com.siweisoft.service.netdb.collection;

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class CollectionBean extends BaseBean {

    private int id;

    private int videoid;

    private int userid;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    @Bindable
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}