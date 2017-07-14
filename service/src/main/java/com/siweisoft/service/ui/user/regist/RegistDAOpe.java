package com.siweisoft.service.ui.user.regist;

//by summer on 2017-07-10.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.user.login.UserInfo;

public class RegistDAOpe extends BaseDAOpe {

    UserInfo userInfo = new UserInfo();

    UserI userI;

    public RegistDAOpe(Context context) {
        super(context);
        userI = new UserNetOpe(context);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserI getUserI() {
        return userI;
    }
}
