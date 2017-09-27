package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;

public class VideoChatDAOpe extends BaseDAOpe {

    private VideoBean videoBean;

    private double start;

    private double end;

    VideoI videoI;

    private boolean accept = false;


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

    public void updateVideo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
        if (videoI == null) {
            videoI = new VideoOpe(context.getApplicationContext());
        }
        videoI.getMaxVideoId(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoI.addVideo(videoBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        onFinishListener.onFinish(o);
                    }
                });
            }
        });
    }

    public void insert_and_getid_fromvieo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
        getVideoBean().setFile("");
        getVideoBean().setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        getVideoBean().setTimenum(getMinute());
        if (videoI == null) {
            videoI = new VideoOpe(context.getApplicationContext());
        }
        videoI.insert_and_getid_fromvieo(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                onFinishListener.onFinish(o);
            }
        });
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
