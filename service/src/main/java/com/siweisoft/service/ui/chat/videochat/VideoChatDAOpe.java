package com.siweisoft.service.ui.chat.videochat;

//by summer on 2017-07-04.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.ui.main.RoleInfo;

public class VideoChatDAOpe extends BaseDAOpe {

    RoleInfo roleInfo;

    public VideoChatDAOpe(Context context) {
        super(context);
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }
}
