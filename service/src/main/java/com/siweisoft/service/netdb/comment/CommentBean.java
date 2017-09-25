package com.siweisoft.service.netdb.comment;

//by summer on 17-08-29.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.service.netdb.user.UserBean;

public class CommentBean extends BaseBean {

    private int id;

    private float rate;

    private String tips;

    private String remark;

    private String created;

    private String videoname;

    private String fromuser;

    private String touser;

    private UserBean fromUser;

    private UserBean toUser;

    private int fromid;

    private int toid;

    private int agreeNum;

    private boolean agree;

    private int videoid;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Bindable
    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Bindable
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Bindable
    public String getCreated() {
        return created;
    }

    @Bindable
    public String getMMDD_HHMM() {
        return DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS, DateFormatUtil.MM_DD_HH_MM, created);
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Bindable
    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    @Bindable
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    @Bindable
    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    @Bindable
    public UserBean getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserBean fromUser) {
        this.fromUser = fromUser;
    }

    @Bindable
    public UserBean getToUser() {
        return toUser;
    }

    public void setToUser(UserBean toUser) {
        this.toUser = toUser;
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
    public int getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(int agreeNum) {
        this.agreeNum = agreeNum;
    }

    @Bindable
    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    @Bindable
    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }
}
