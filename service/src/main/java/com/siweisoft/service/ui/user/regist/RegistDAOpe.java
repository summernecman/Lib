package com.siweisoft.service.ui.user.regist;

//by summer on 2017-07-10.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;

public class RegistDAOpe extends BaseDAOpe {

    UserBean userBean = new UserBean();

    private UserI userI;

    public RegistDAOpe(Context context) {
        super(context);
        userI = new UserNetOpe(context);
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public void regist(UserBean userBean, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.regist(userBean, onFinishListener);
    }

}
