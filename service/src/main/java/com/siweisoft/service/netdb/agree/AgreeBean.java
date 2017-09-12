package com.siweisoft.service.netdb.agree;

//by summer on 17-09-12.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class AgreeBean extends BaseBean {

    private int id;

    private int commentid;

    private int agreeid;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    @Bindable
    public int getAgreeid() {
        return agreeid;
    }

    public void setAgreeid(int agreeid) {
        this.agreeid = agreeid;
    }
}
