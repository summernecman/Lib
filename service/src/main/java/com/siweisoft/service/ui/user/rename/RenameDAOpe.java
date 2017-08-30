package com.siweisoft.service.ui.user.rename;

//by summer on 17-08-30.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;

public class RenameDAOpe extends BaseDAOpe {

    UserBean userBean;


    UserI userI;

    public RenameDAOpe(Context context) {
        super(context);
        userBean = new UserBean();
        userI = new UserNetOpe(context);
        userBean.setPhone(Value.userBean.getPhone());
        userBean.setName(Value.userBean.getName());
    }

    public UserBean getUserBean() {
        return userBean;
    }


}
