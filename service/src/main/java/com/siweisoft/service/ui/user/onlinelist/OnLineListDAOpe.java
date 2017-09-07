package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.main.RoleInfo;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class OnLineListDAOpe extends BaseDAOpe {

    RoleInfo roleInfo;

    UserI userI;

    public OnLineListDAOpe(Context context) {
        super(context);
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    public UserI getUserI() {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        return userI;
    }

    public void getUsersInfoByPhone(ArrayList<UserBean> data, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getUsersInfoByPhone(data, onFinishListener);
    }


}
