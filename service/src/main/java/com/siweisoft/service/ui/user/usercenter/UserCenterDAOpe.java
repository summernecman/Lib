package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.collection.CollectionI;
import com.siweisoft.service.netdb.collection.CollectionOpe;
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

    CollectionI collectionI;

    public UserCenterDAOpe(Context context) {
        super(context);
    }


    public UserInfoDAOpe getUserInfoDAOpe() {
        if (userInfoDAOpe == null) {
            userInfoDAOpe = new UserInfoDAOpe(context);
        }
        return userInfoDAOpe;
    }

    public UserI getUserI() {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        return userI;
    }

    public void getUserCallInfo(OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        UserBean userBean = new UserBean();
        userBean.setPhone(Value.userBean.getPhone());
        userI.getUserCallInfo(userBean, onFinishListener);
    }

    public void getUserTips(UserBean userBean, OnFinishListener onFinishListener) {
        if (commentI == null) {
            commentI = new CommentOpe(context);
        }
        commentI.getUserTips(userBean, onFinishListener);
    }

    public void getCommentNumByUserName(UserBean userBean, OnFinishListener onFinishListener) {
        if (commentI == null) {
            commentI = new CommentOpe(context);
        }
        commentI.getCommentNumByUserName(userBean, onFinishListener);
    }

    public void getCollectionNumByUserId(UserBean userBean, OnFinishListener onFinishListener) {
        if (collectionI == null) {
            collectionI = new CollectionOpe(context);
        }
        collectionI.getCollectionNumByUserId(userBean, onFinishListener);
    }
}
