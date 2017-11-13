package com.siweisoft.service.ui.video.videorecord;

//by summer on 17-08-23.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.bean.ContactBean;
import com.siweisoft.service.bean.HistoryBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.video.seach.SeachBean;

import java.util.ArrayList;

public class VideoRecordDAOpe extends BaseDAOpe {


    VideoI videoI = new VideoOpe(context);

    ArrayList<VideoBean> videos = new ArrayList<>();

    private HistoryBean historyBean;

    private int pageindex = 0;

    private SeachBean seachBean = new SeachBean();


    public VideoRecordDAOpe(Context context) {
        super(context);
        seachBean.setTxt("");
        seachBean.setData(Value.getVideotipsList());
    }

    public void getHistory(OnFinishListener onFinishListener) {
        videoI.getHistoryVideos(Value.getUserInfo(), onFinishListener);
    }

    public ArrayList<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<VideoBean> videos) {
        this.videos = videos;
    }

    public HistoryBean getHistoryBean() {
        return historyBean;
    }

    public void setHistoryBean(HistoryBean historyBean) {
        this.historyBean = historyBean;
    }

    public void getVideosByBothUserIdWithLimit(ContactBean contactBean, OnFinishListener onFinishListener) {
        if (getSeachBean().isCan()) {
            contactBean.setType(getSeachBean().getType());
            contactBean.setTxt(getSeachBean().getTxt());
            videoI.getVideosByBothUserIdWithLimitAndSeach(contactBean, onFinishListener);
        } else {
            videoI.getVideosByBothUserIdWithLimit(contactBean, onFinishListener);
        }
    }


    public void getVideosByBothUserIdWithLimitAndSeach(ContactBean contactBean, OnFinishListener onFinishListener) {
        videoI.getVideosByBothUserIdWithLimitAndSeach(contactBean, onFinishListener);
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public SeachBean getSeachBean() {
        return seachBean;
    }

    public void setSeachBean(SeachBean seachBean) {
        this.seachBean = seachBean;
    }
}
