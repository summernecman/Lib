package com.siweisoft.service.netdb.video;


import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.siweisoft.service.ui.Constant.Value;

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
        if (Value.userBean.getChatid() == fromchatid) {
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
        if (Value.userBean.getPhone() == fromphone) {
            othername = tophone;
        } else {
            othername = fromphone;
        }
        return othername;
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
                '}';
    }
}
