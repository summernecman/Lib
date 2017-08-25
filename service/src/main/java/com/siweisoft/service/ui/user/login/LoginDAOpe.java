package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;

public class LoginDAOpe extends BaseDAOpe {

    UserBean userBean = new UserBean();

    UserI userI;


    public LoginDAOpe(Context context) {
        super(context);
        userBean.setPhone("18721607438");
        userBean.setPwd("111111");
        userI = new UserNetOpe(context);
    }

    public void login(UserBean userBean, final OnFinishListener onFinishListener) {
        userI.login(userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                UserBean res = (UserBean) o;
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
