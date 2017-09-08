package com.siweisoft.service.ui.user.userlist;

//by summer on 17-09-06.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;

public class UserListDAOpe extends BaseDAOpe {

    UserI userI;

    UserBean userBean;

    public UserListDAOpe(Context context) {
        super(context);
    }

    public void getUserListWithOutMe(UserBean userBean, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getUserListWithOutMe(userBean, onFinishListener);
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
