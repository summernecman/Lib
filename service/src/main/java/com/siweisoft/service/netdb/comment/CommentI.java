package com.siweisoft.service.netdb.comment;

//by summer on 17-08-29.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.ui.user.login.UserBean;

public interface CommentI {

    public void addComment(CommentBean commentBean, OnFinishListener onFinishListener);

    public void getCommentByUserName(UserBean userBean, OnFinishListener onFinishListener);

    public void getUserTips(UserBean userBean, OnFinishListener onFinishListener);


}
