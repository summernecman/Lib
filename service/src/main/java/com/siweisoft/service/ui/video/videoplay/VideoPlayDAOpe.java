package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.NullUtil;
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
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.netdb.videodetail.VideoDetailBean;
import com.siweisoft.service.netdb.videodetail.VideoDetailOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.io.File;

public class VideoPlayDAOpe extends BaseDAOpe {

    VideoBean videoBean;

    UserInfoDAOpe userInfoDAOpe;

    CommentOpe commentI;

    CollectionI collectionI;

    UserBean userBean;

    ShareI shareI;

    VideoI videoi;

    private int type = 0;

    private VideoDetailBean videoDetailBean;

    CollectionBean collectionBean = new CollectionBean();


    VideoDetailOpe videoDetailI;

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
        commentI.getVideoCommentByVideoIdAndCommentId(videoBean, onFinishListener);
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

    public String getVideoComment(VideoDetailBean videoDetailBean, VideoBean videoBean) {
        for (int i = 0; videoBean.getVideoCommentBeans() != null && i < videoBean.getVideoCommentBeans().size(); i++) {
            if (videoDetailBean.getUserid() == videoBean.getVideoCommentBeans().get(i).getUserid()) {
                return videoBean.getVideoCommentBeans().get(i).getTxt();
            }
        }
        return "";
    }


    public void uploadVideo(final VideoBean videoBean, final VideoDetailBean vv, final OnFinishListener onFinishListener, final OnFinishListener onFinishListener2) {
        final String f = videoBean.getFile();
        if (NullUtil.isStrEmpty(videoBean.getFile())) {
            return;
        }
        final String[] ss = videoBean.getFile().split("/");
        File file = new File(Value.getCacheFile(), ss[ss.length - 1]);
        videoBean.setFile(file.getPath());
        if (videoi == null) {
            videoi = new VideoOpe(context);
        }
        if (!file.exists()) {
            BaseResBean o = new BaseResBean();
            o.setException(true);
            o.setErrorMessage("文件不存在");
            onFinishListener.onFinish(o);
            return;
        }
        videoi.updateVideo(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(f);
                if (videoDetailI == null) {
                    videoDetailI = new VideoDetailOpe(context);
                }
                videoDetailI.updateUpload(vv, onFinishListener);
            }
        }, onFinishListener2);
    }


    public void isCommentToCustomer(VideoDetailBean vv, final OnFinishListener onFinishListener) {
        if (videoDetailI == null) {
            videoDetailI = new VideoDetailOpe(context);
        }
        videoDetailI.getCommentToType(vv, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                UserBean u = (UserBean) o;
                if (u != null && u.getUsertype() == UserBean.CUSTOME) {
                    onFinishListener.onFinish(true);
                } else {
                    onFinishListener.onFinish(false);
                }
            }
        });
    }

    public void share(ShareBean shareBean, OnFinishListener onFinishListener) {
        if (shareI == null) {
            shareI = new ShareOpe(context);
        }
        shareI.share(shareBean, onFinishListener);
    }


    public void downloadFile(VideoDetailBean videoDetailBean, NetWork.MyFileDownloadCallBack callBack) {

        NetWork.getInstance(context).download(videoDetailBean.getUrl(), VideoPlayDAOpe.getLoadFile(videoDetailBean), callBack);

    }

    public static String getLoadFile(VideoDetailBean videoDetailBean) {
        String[] ss = videoDetailBean.getUrl().split("/");
        if (ss.length == 0) {
            return "";
        }
        File file = new File(Value.getCacheFile(), ss[ss.length - 1]);
        return file.getPath();
    }


    public VideoDetailBean getVideoDetailBean() {
        return videoDetailBean;
    }

    public void setVideoDetailBean(VideoDetailBean videoDetailBean) {
        this.videoDetailBean = videoDetailBean;
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
