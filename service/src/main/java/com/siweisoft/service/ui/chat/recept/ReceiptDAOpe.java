package com.siweisoft.service.ui.chat.recept;

//by summer on 17-09-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoOpe;

public class ReceiptDAOpe extends BaseDAOpe {

    private int dwUserId;

    private String userStr;

    private VideoBean videoBean;

    private VideoOpe videoI;


    public ReceiptDAOpe(Context context) {
        super(context);
    }

    public int getDwUserId() {
        return dwUserId;
    }

    public void setDwUserId(int dwUserId) {
        this.dwUserId = dwUserId;
    }

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }


    public void updateCallState(VideoBean v, int state) {
        if (videoI == null) {
            videoI = new VideoOpe(context.getApplicationContext());
        }
        v.setCallstate(state);
        videoI.updateCallState(v, null);
    }
}
