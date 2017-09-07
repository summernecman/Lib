package com.siweisoft.service.ui.setting.sharelist;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.share.ShareBean;
import com.siweisoft.service.netdb.share.ShareI;
import com.siweisoft.service.netdb.share.ShareOpe;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;

public class ShareListDAOpe extends BaseDAOpe {


    ShareI shareI;

    ArrayList<VideoBean> videos;

    public ShareListDAOpe(Context context) {
        super(context);
    }


    public void getSharesByReceipt(ShareBean shareBean, OnFinishListener onFinishListener) {
        if (shareI == null) {
            shareI = new ShareOpe(context);
        }
        shareI.getSharesByReceipt(shareBean, onFinishListener);
    }

    public ArrayList<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<VideoBean> videos) {
        this.videos = videos;
    }
}
