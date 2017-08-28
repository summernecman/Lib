package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class HistoryDAOpe extends BaseDAOpe {


    VideoI videoI;

    public HistoryDAOpe(Context context) {
        super(context);
        videoI = new VideoOpe(context);
    }

    public ArrayList<VideoBean> getData() {
        ArrayList<VideoBean> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VideoBean videoBean = new VideoBean();
            videoBean.setFromphone("张三");
            videoBean.setTophone("3次");
            videoBean.setCreated("2014:4:23");
            data.add(videoBean);
        }
        return data;
    }

    public void getVideos(final OnFinishListener onFinishListener) {
        videoI.getVideosByContacts(Value.userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                onFinishListener.onFinish(o);
            }
        });
    }
}
