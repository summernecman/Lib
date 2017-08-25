package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

public class RemarkDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe;

    UserBean userBean;


    public RemarkDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
