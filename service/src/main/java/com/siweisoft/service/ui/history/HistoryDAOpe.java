package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class HistoryDAOpe extends BaseDAOpe {


    VideoI videoI;

    UserI userI;

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
        videoI.getByContacts(Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                onFinishListener.onFinish(o);
            }
        });
    }

    public void getUnUploadVideoNum(final OnFinishListener onFinishListener) {
        videoI.getUnUploadVideoNum(Value.getUserInfo(), onFinishListener);
    }

    public void getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> data, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getArrayUsersInfoByPhone(data, onFinishListener);
    }
}
