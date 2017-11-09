package com.siweisoft.service.netdb.video;


import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.videodetail.VideoDetailBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-24.
 */
public class VideoBean extends BaseBean {

    private int id;

    /**
     * 录制文件目录
     */
    private String file;
    /**文件创建时间*/
    private String created;
    /**发起人userid*/
    private int fromid;
    /**接收人userid*/
    private int toid;
    /**发起人用户手机号*/
    private String fromphone;
    /**接收人用户手机号*/
    private String tophone;
    /**
     * 发起人视频分配id
     */
    private String fromchatid;
    /**
     * 接收人视频分配id
     */
    private String tochatid;

    private String otherid;

    private String othername;

    private long timenum;

    private UserBean fromUser;

    private UserBean toUser;

    private int uploaded;

    private boolean isfrom = false;

    private int callstate;

    public static final int CALL_STATE_SUCCESS = 1;

    public static final int CALL_STATE_FAIL = 0;

    public static final int CALL_STATE_REJECT = 2;

    private ArrayList<VideoDetailBean> videodetail;

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

    @Bindable
    public String getFromchatid() {
        return fromchatid;
    }

    public void setFromchatid(String fromchatid) {
        this.fromchatid = fromchatid;
    }

    @Bindable
    public String getTochatid() {
        return tochatid;
    }

    public void setTochatid(String tochatid) {
        this.tochatid = tochatid;
    }

    @Bindable
    public String getOtherid() {
        if (Value.getUserInfo().getChatid().equals(fromchatid)) {
            otherid = tochatid;
        } else {
            otherid = fromchatid;
        }
        return otherid;
    }

    public void setOtherid(String otherid) {
        this.otherid = otherid;
    }

    public String getOthername() {
        if (Value.getUserInfo().getPhone().equals(fromphone)) {
            othername = tophone;
        } else {
            othername = fromphone;
        }
        return othername;
    }

    public UserBean getOtherUser() {
        UserBean userBean;
        if (Value.getUserInfo().getPhone().equals(fromphone)) {
            userBean = getToUser();
        } else {
            userBean = getFromUser();
        }
        return userBean;
    }


    public long getTimenum() {
        return timenum;
    }

    public void setTimenum(long timenum) {
        this.timenum = timenum;
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
    public int getUploaded() {
        return uploaded;
    }

    public void setUploaded(int uploaded) {
        this.uploaded = uploaded;
    }

    public boolean isIsfrom() {
        return isfrom;
    }

    public void setIsfrom(boolean isfrom) {
        this.isfrom = isfrom;
    }

    public int getCallstate() {
        return callstate;
    }

    public void setCallstate(int callstate) {
        this.callstate = callstate;
    }

    public VideoBean getSame() {
        VideoBean videoBean = new VideoBean();
        videoBean.setCreated(getCreated());
        videoBean.setFile(getFile());
        videoBean.setFromchatid(getFromchatid());
        videoBean.setFromid(getFromid());
        videoBean.setFromphone(getFromphone());
        videoBean.setFromUser(getFromUser());
        videoBean.setId(getId());
        videoBean.setIsfrom(isIsfrom());
        videoBean.setTimenum(getTimenum());
        videoBean.setTochatid(getTochatid());
        videoBean.setToid(getToid());
        videoBean.setTophone(getTophone());
        videoBean.setToUser(getToUser());
        videoBean.setUploaded(getUploaded());
        videoBean.setVideodetail(getVideodetail());
        return videoBean;
    }

    public ArrayList<VideoDetailBean> getVideodetail() {
        return videodetail;
    }

    public void setVideodetail(ArrayList<VideoDetailBean> videodetail) {
        this.videodetail = videodetail;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "id=" + id +
                ", file='" + file + '\'' +
                ", created='" + created + '\'' +
                ", fromid=" + fromid +
                ", toid=" + toid +
                ", fromphone='" + fromphone + '\'' +
                ", tophone='" + tophone + '\'' +
                ", fromchatid='" + fromchatid + '\'' +
                ", tochatid='" + tochatid + '\'' +
                ", otherid='" + otherid + '\'' +
                ", othername='" + othername + '\'' +
                ", timenum=" + timenum +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                ", uploaded=" + uploaded +
                ", isfrom=" + isfrom +
                ", videodetail=" + videodetail +
                '}';
    }
}
