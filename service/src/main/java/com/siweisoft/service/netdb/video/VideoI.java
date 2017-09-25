package com.siweisoft.service.netdb.video;

//by summer on 17-08-24.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.user.UserBean;

public interface VideoI {

    public void getVideos(UserBean userBean, OnFinishListener onFinishListener);

    public void addVideo(VideoBean videoBean, OnFinishListener onFinishListener);

    public void getHistoryVideos(UserBean userBean, OnFinishListener onFinishListener);

    public void getVideosByContacts(UserBean userBean, OnFinishListener onFinishListener);

    public void getVideosByContacts2(UserBean userBean, OnFinishListener onFinishListener);

    public void commentVideo(CommentBean commentBean, OnFinishListener onFinishListener);

    public void updateVideo(VideoBean videoBean, OnFinishListener onFinishListener);

    public void updateVideoById(VideoBean videoBean, OnFinishListener onFinishListener);

    public void isVideoUploaded(VideoBean videoBean, OnFinishListener onFinishListener);

    public void setVideoUploaded(VideoBean videoBean, OnFinishListener onFinishListener);

    public void getMaxVideoId(OnFinishListener onFinishListener);

    public void insert_and_getid_fromvieo(VideoBean videoBean, OnFinishListener onFinishListener);


}
