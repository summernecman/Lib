package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.comment.CommentI;
import com.siweisoft.service.netdb.comment.CommentOpe;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class UserInfoDAOpe extends BaseDAOpe {

    UserBean userBean;

    CommentI commentI;
    public UserInfoDAOpe(Context context) {
        super(context);
        commentI = new CommentOpe(context);
    }

    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("业务熟练");
        data.add("态度好");
        data.add("专业");
        data.add("解决问题");
        data.add("未解决问题");
        data.add("态度差");

        return data;
    }

    public void getRemarks(UserBean userBean, final OnFinishListener onFinishListener) {
        commentI.getCommentByUserName(userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> res = (ArrayList<CommentBean>) o;
                onFinishListener.onFinish(res);
            }
        });

    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
