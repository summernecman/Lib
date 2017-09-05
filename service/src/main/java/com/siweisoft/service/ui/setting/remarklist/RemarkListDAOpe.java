package com.siweisoft.service.ui.setting.remarklist;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.comment.CommentI;
import com.siweisoft.service.netdb.comment.CommentOpe;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class RemarkListDAOpe extends BaseDAOpe {

    CommentI commentI;

    public RemarkListDAOpe(Context context) {
        super(context);
    }

    public void getRemarks(UserBean userBean, final OnFinishListener onFinishListener) {
        if (commentI == null) {
            commentI = new CommentOpe(context);
        }
        commentI.getCommentByUserName(userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> res = (ArrayList<CommentBean>) o;
                onFinishListener.onFinish(res);
            }
        });

    }
}
