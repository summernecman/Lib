package com.siweisoft.service.ui.chat.videochat;

//by summer on 2017-07-04.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoBean;

public class VideoChatDAOpe extends BaseDAOpe {


    VideoBean videoBean;

    public VideoChatDAOpe(Context context) {
        super(context);
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }
}
