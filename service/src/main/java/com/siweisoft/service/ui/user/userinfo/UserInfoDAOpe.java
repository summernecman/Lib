package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TipsBean;
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

    public TipsBean getData() {
        TipsBean tipsBean = new TipsBean();
        ArrayList<TipBean> tipBeen = new ArrayList<>();
        tipBeen.add(new TipBean(0, "业务熟练", 0));
        tipBeen.add(new TipBean(1, "态度好", 0));
        tipBeen.add(new TipBean(2, "专业", 0));
        tipBeen.add(new TipBean(3, "解决问题", 0));
        tipBeen.add(new TipBean(4, "未解决问题", 0));
        tipBeen.add(new TipBean(5, "态度差", 0));
        tipsBean.setTipBeen(tipBeen);
        return tipsBean;
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
