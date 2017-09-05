package com.siweisoft.service.ui.setting.collect;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.collection.CollectionI;
import com.siweisoft.service.netdb.collection.CollectionOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class CollectDAOpe extends BaseDAOpe {

    CollectionI collectionI;

    ArrayList<VideoBean> videos;

    public CollectDAOpe(Context context) {
        super(context);
    }

    public void getCollection(UserBean userBean, OnFinishListener onFinishListener) {
        if (collectionI == null) {
            collectionI = new CollectionOpe(context);
        }
        collectionI.getCollectionVideosByUserId(userBean, onFinishListener);
    }

    public ArrayList<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<VideoBean> videos) {
        this.videos = videos;
    }
}
