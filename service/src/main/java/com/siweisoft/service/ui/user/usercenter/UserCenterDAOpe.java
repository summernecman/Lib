package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.comment.CommentI;
import com.siweisoft.service.netdb.comment.CommentOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

public class UserCenterDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe;

    UserI userI;

    CommentI commentI;

    public UserCenterDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
        userI = new UserNetOpe(context);
        commentI = new CommentOpe(context);
    }

    public UserInfoDAOpe getUserInfoDAOpe() {
        return userInfoDAOpe;
    }

    public UserI getUserI() {
        return userI;
    }

    public void getUserCallInfo(OnFinishListener onFinishListener) {
        UserBean userBean = new UserBean();
        userBean.setPhone(Value.userBean.getPhone());
        userI.getUserCallInfo(userBean, onFinishListener);
    }

    public void getUserTips(UserBean userBean, OnFinishListener onFinishListener) {
        commentI.getUserTips(userBean, onFinishListener);
    }
}
