package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;

public class VideoChatDAOpe extends BaseDAOpe {

    private VideoBean videoBean;

    private double start;

    private double end;


    public VideoChatDAOpe(Context context) {
        super(context);
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }


    public boolean isLocalSendVideo(UserBean local, UserBean remove) {
        switch (local.getUsertype()) {
            case UserBean.SERVER:
                return false;
            case UserBean.CUSTOME:
                return true;
            case UserBean.ENGINEER:
                if (remove.getUsertype() == UserBean.SERVER) {
                    return true;
                } else {
                    return false;
                }
        }
        return true;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public int getMinute() {
        LogUtil.E(end + "---" + start);
        return (int) ((end - start) / 1000);
    }
}
