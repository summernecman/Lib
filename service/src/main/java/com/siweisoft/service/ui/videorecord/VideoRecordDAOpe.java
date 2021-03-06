package com.siweisoft.service.ui.videorecord;

//by summer on 17-08-23.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class VideoRecordDAOpe extends BaseDAOpe {


    VideoI videoI = new VideoOpe(context);

    ArrayList<VideoBean> videos;


    public VideoRecordDAOpe(Context context) {
        super(context);
    }

    public void getHistory(OnFinishListener onFinishListener) {
        videoI.getHistoryVideos(Value.userBean, onFinishListener);
    }

    public ArrayList<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<VideoBean> videos) {
        this.videos = videos;
    }
}
