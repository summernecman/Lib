package com.siweisoft.service.ui.setting.collect;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.collection.CollectionI;
import com.siweisoft.service.netdb.collection.CollectionOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;

public class CollectDAOpe extends BaseDAOpe {

    CollectionI collectionI;

    ArrayList<VideoBean> videos = new ArrayList<>();

    private int pagesize = 5;

    private int pagestart = 0;

    public CollectDAOpe(Context context) {
        super(context);
    }

    public void getCollection(UserBean userBean, OnFinishListener onFinishListener) {
        if (collectionI == null) {
            collectionI = new CollectionOpe(context);
        }
        collectionI.getCollectionVideosByUserIdWithLimit(userBean, onFinishListener);
    }

    public ArrayList<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<VideoBean> videos) {
        this.videos = videos;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagestart() {
        return pagestart;
    }

    public void setPagestart(int pagestart) {
        this.pagestart = pagestart;
    }
}
