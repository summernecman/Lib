package com.siweisoft.service.netdb.comment;

//by summer on 17-08-29.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;

public interface CommentI {

    public void addComment(CommentBean commentBean, OnFinishListener onFinishListener);

    public void getCommentNumByUserName(UserBean userBean, OnFinishListener onFinishListener);

    public void getCommentByUserName(UserBean userBean, OnFinishListener onFinishListener);

    public void getCommentByUserNameWithMyOption(CommentBean commentBean, OnFinishListener onFinishListener);

    public void getUserTips(UserBean userBean, OnFinishListener onFinishListener);

    public void getVideoCommentByVideoName(VideoBean videoBean, OnFinishListener onFinishListener);

    public void getVideoCommentByVideoNameAndFrom(VideoBean videoBean, OnFinishListener onFinishListener);

    public void getVideoRateCommentByUseId(UserBean userBean, OnFinishListener onFinishListener);

    public void getVideoCommentByVideoIdAndFrom(VideoBean videoBean, OnFinishListener onFinishListener);

}
