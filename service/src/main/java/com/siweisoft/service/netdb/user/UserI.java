package com.siweisoft.service.netdb.user;

//by summer on 2017-07-10.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.ui.user.login.UserInfo;

public interface UserI {

    public void registed(String username, OnFinishListener onFinishListener);

    public void regist(UserInfo userInfo, OnFinishListener onFinishListener);

    public void login(UserInfo userInfo, OnFinishListener onFinishListener);

    public void logout(UserInfo userInfo, OnFinishListener onFinishListener);

    public void getUserInfo(String name, OnFinishListener onFinishListener);
}
