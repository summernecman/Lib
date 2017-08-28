package com.siweisoft.service.netdb.user;

//by summer on 2017-07-10.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.ui.user.login.UserBean;

public interface UserI {

    public void registed(String username, OnFinishListener onFinishListener);

    public void regist(UserBean userBean, OnFinishListener onFinishListener);

    public void login(UserBean userBean, OnFinishListener onFinishListener);


    public void getUserInfo(String name, OnFinishListener onFinishListener);

    public void loginOut(UserBean userBean, OnFinishListener onFinishListener);
}
