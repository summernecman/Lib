package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.content.Context;
import android.os.Environment;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.NullUtil;
import com.siweisoft.service.netdb.collection.CollectionBean;
import com.siweisoft.service.netdb.collection.CollectionI;
import com.siweisoft.service.netdb.collection.CollectionOpe;
import com.siweisoft.service.netdb.comment.CommentI;
import com.siweisoft.service.netdb.comment.CommentOpe;
import com.siweisoft.service.netdb.share.ShareBean;
import com.siweisoft.service.netdb.share.ShareI;
import com.siweisoft.service.netdb.share.ShareOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.io.File;

public class VideoPlayDAOpe extends BaseDAOpe {

    VideoBean videoBean;

    UserInfoDAOpe userInfoDAOpe;

    CommentI commentI;

    CollectionI collectionI;

    UserBean userBean;

    ShareI shareI;

    VideoI videoi;

    private int type = 0;

    CollectionBean collectionBean = new CollectionBean();



    public VideoPlayDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
        userBean = new UserBean();
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
        videoBean.setToUser(Value.getUserInfo());
        commentI.getVideoCommentByVideoIdAndFrom(videoBean, onFinishListener);
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


    public void uploadVideo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
        final String f = videoBean.getFile();
        if (NullUtil.isStrEmpty(videoBean.getFile())) {
            return;
        }
        final String[] ss = videoBean.getFile().split("/");
        File file = new File(Environment.getExternalStorageDirectory() + "/videorecord", ss[ss.length - 1]);
        videoBean.setFile(file.getPath());
        if (videoi == null) {
            videoi = new VideoOpe(context);
        }
        videoi.updateVideo(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(f);
                videoi.setVideoUploaded(videoBean, onFinishListener);
            }
        });
    }


    public void share(ShareBean shareBean, OnFinishListener onFinishListener) {
        if (shareI == null) {
            shareI = new ShareOpe(context);
        }
        shareI.share(shareBean, onFinishListener);
    }



    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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
