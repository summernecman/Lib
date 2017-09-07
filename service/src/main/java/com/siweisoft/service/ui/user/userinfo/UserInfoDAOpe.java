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
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.ui.user.usercenter.UserCenterDAOpe;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import java.util.ArrayList;

public class UserInfoDAOpe extends BaseDAOpe {

    UserBean userBean;

    CommentI commentI;

    UserI userI;

    UserCenterDAOpe userCenterDAOpe;


    public UserInfoDAOpe(Context context) {
        super(context);

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

    public void getUserCallInfo(UserBean userBean, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getUserCallInfo(userBean, onFinishListener);
    }



    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserCenterDAOpe getUserCenterDAOpe() {
        if (userCenterDAOpe == null) {
            userCenterDAOpe = new UserCenterDAOpe(context);
        }
        return userCenterDAOpe;
    }


    public UserBean getUserChatInfo(UserBean userBean) {
        ArrayList<UserBean> userBeen = ChatInit.getInstance().getUserList();
        for (int i = 0; i < userBeen.size(); i++) {
            if (userBeen.get(i).getPhone().equals(userBean.getPhone())) {
                return userBeen.get(i);
            }
        }
        return null;
    }
}
