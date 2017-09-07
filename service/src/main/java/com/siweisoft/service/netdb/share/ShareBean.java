package com.siweisoft.service.netdb.share;

//by summer on 17-09-06.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class ShareBean extends BaseBean {

    private int id;

    private int videoid;

    private int sendid;

    private int receiptid;


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
    public int getSendid() {
        return sendid;
    }

    public void setSendid(int sendid) {
        this.sendid = sendid;
    }

    @Bindable
    public int getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(int receiptid) {
        this.receiptid = receiptid;
    }
}
