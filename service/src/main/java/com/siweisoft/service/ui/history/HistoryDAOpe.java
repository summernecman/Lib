package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;

public class HistoryDAOpe extends BaseDAOpe {


    public HistoryDAOpe(Context context) {
        super(context);
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
}
