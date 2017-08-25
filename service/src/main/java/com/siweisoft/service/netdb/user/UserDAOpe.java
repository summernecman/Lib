package com.siweisoft.service.netdb.user;

//by summer on 2017-07-10.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.ui.user.login.UserBean;

public class UserDAOpe extends BaseDAOpe implements UserI {

    public static final String user = "user";

    public UserDAOpe(Context context) {
        super(context);
    }

    @Override
    public void registed(String username, final OnFinishListener onFinishListener) {
    }

    @Override
    public void regist(UserBean userBean, final OnFinishListener onFinishListener) {
    }

    @Override
    public void login(final UserBean userBean, final OnFinishListener onFinishListener) {
    }

    @Override
    public void logout(UserBean userBean, OnFinishListener onFinishListener) {

    }

    @Override
    public void getUserInfo(String name, final OnFinishListener onFinishListener) {

    }

}
