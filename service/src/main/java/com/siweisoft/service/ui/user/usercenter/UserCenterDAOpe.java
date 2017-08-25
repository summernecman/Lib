package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

public class UserCenterDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe;

    public UserCenterDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
    }
}
