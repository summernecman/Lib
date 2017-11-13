package com.siweisoft.service.ui.video.videocontainer;

//by summer on 2017-11-08.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.netdb.collection.CollectionBean;
import com.siweisoft.service.netdb.collection.CollectionI;
import com.siweisoft.service.netdb.collection.CollectionOpe;
import com.siweisoft.service.netdb.comment.CommentOpe;
import com.siweisoft.service.netdb.share.ShareBean;
import com.siweisoft.service.netdb.share.ShareI;
import com.siweisoft.service.netdb.share.ShareOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.videodetail.VideoDetailBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.util.ArrayList;

public class VideoContainerDAOpe extends BaseDAOpe {


    private int type = 0;

    VideoBean videoBean;

    UserInfoDAOpe userInfoDAOpe;

    CommentOpe commentI;

    CollectionI collectionI;

    UserBean userBean;

    ShareI shareI;

    VideoI videoi;

    CollectionBean collectionBean = new CollectionBean();

    public VideoContainerDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getVideosPager(VideoBean videoBean, int type) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < videoBean.getVideodetail().size(); i++) {
            VideoBean vto = videoBean.getSame();
            VideoPlayFrag playFrag = new VideoPlayFrag();
            playFrag.setArguments(new Bundle());
            playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, vto);
            playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA2, vto.getVideodetail().get(i));
            playFrag.getArguments().putInt(ValueConstant.DATA_TYPE, type);
            fragments.add(playFrag);
        }
        return fragments;
    }


    public void collect(CollectionBean collectionBean, OnFinishListener onFinishListener) {
        if (collectionI == null) {
            collectionI = new CollectionOpe(context);
        }

        if (Value.getUserInfo().getId() == videoBean.getFromUser().getId()) {
            collectionBean.setUserid(videoBean.getFromUser().getId());
        } else {
            collectionBean.setUserid(videoBean.getToUser().getId());
        }
        collectionI.collect(collectionBean, onFinishListener);
    }

    public void disCollect(CollectionBean collectionBean, OnFinishListener onFinishListener) {
        if (collectionI == null) {
            collectionI = new CollectionOpe(context);
        }
        if (Value.getUserInfo().getId() == videoBean.getFromUser().getId()) {
            collectionBean.setUserid(videoBean.getFromUser().getId());
        } else {
            collectionBean.setUserid(videoBean.getToUser().getId());
        }
        collectionI.disCollect(collectionBean, onFinishListener);
    }

    public String getVideoComment(VideoDetailBean videoDetailBean, VideoBean videoBean) {
        for (int i = 0; i < videoBean.getVideoCommentBeans().size(); i++) {
            if (videoDetailBean.getUserid() == videoBean.getVideoCommentBeans().get(i).getUserid()) {
                return videoBean.getVideoCommentBeans().get(i).getTxt();
            }
        }
        return "";
    }

    public void share(ShareBean shareBean, OnFinishListener onFinishListener) {
        if (shareI == null) {
            shareI = new ShareOpe(context);
        }
        shareI.share(shareBean, onFinishListener);
    }

    public void isCollectedByVideoIdAndUserId(VideoBean videoBean, OnFinishListener onFinishListener) {
        if (collectionI == null) {
            collectionI = new CollectionOpe(context);
        }
        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setUserid(Value.getUserInfo().getId());
        collectionBean.setVideoid(videoBean.getId());
        collectionI.isCollectedByVideoIdAndUserId(collectionBean, onFinishListener);
    }


    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
        getCollectionBean().setVideoid(getVideoBean().getId());
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CollectionBean getCollectionBean() {
        return collectionBean;
    }
}
