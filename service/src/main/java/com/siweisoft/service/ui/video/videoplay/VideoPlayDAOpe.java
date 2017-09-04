package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.collection.CollectionI;
import com.siweisoft.service.netdb.collection.CollectionOpe;
import com.siweisoft.service.netdb.comment.CommentI;
import com.siweisoft.service.netdb.comment.CommentOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

public class VideoPlayDAOpe extends BaseDAOpe {

    VideoBean videoBean;

    UserInfoDAOpe userInfoDAOpe;

    CommentI commentI;

    CollectionI collectionI;

    public VideoPlayDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }

    public UserInfoDAOpe getUserInfoDAOpe() {
        return userInfoDAOpe;
    }

    public void getComment(VideoBean videoBean, OnFinishListener onFinishListener) {
        if (commentI == null) {
            commentI = new CommentOpe(context);
        }
        commentI.getVideoComment(videoBean, onFinishListener);
    }

    public void collect(VideoBean videoBean, OnFinishListener onFinishListener) {
        if (collectionI == null) {
            collectionI = new CollectionOpe(context);
        }
        collectionI.collect(videoBean, onFinishListener);
    }


}
