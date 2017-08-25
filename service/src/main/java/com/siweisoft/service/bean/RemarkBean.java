package com.siweisoft.service.bean;

//by summer on 17-08-25.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class RemarkBean extends BaseBean {

    private int userid;

    private String created;

    private String remark;

    private int agreenum;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Bindable
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Bindable
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Bindable
    public int getAgreenum() {
        return agreenum;
    }

    public void setAgreenum(int agreenum) {
        this.agreenum = agreenum;
    }
}
