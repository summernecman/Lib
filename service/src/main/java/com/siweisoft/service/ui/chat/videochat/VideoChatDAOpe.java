package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;

public class VideoChatDAOpe extends BaseDAOpe {

    private VideoBean videoBean;


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

}
