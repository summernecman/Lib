package com.siweisoft.service.netdb.video;


import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

/**
 * Created by SWSD on 17-08-24.
 */
public class VideoBean extends BaseBean {

    private int id;

    private String file;

    private String created;

    private int fromid;

    private int toid;

    private String fromphone;

    private String tophone;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Bindable
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Bindable
    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    @Bindable
    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    @Bindable
    public String getFromphone() {
        return fromphone;
    }

    public void setFromphone(String fromphone) {
        this.fromphone = fromphone;
    }

    @Bindable
    public String getTophone() {
        return tophone;
    }

    public void setTophone(String tophone) {
        this.tophone = tophone;
    }
}
