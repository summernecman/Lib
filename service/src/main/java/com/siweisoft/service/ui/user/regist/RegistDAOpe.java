package com.siweisoft.service.ui.user.regist;

//by summer on 2017-07-10.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.user.login.UserBean;

public class RegistDAOpe extends BaseDAOpe {

    UserBean userBean = new UserBean();

    UserI userI;

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

    public UserI getUserI() {
        return userI;
    }
}
