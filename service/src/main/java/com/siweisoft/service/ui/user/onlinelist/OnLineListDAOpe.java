package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.bean.AllUserBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.main.RoleInfo;

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

    public void getUnTypeUserList(UserBean data, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getUnTypeUserList(data, onFinishListener);
    }


    public void getOtherUsersInfoByPhone(AllUserBean data, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getOtherUsersInfoByPhone(data, onFinishListener);
    }

    public ArrayList<UserBean> getOnlineUsersInfo(ArrayList<UserBean> netusers, ArrayList<UserBean> chatusers) {
        for (int i = 0; netusers != null && i < netusers.size(); i++) {
            for (int j = 0; chatusers != null && j < chatusers.size(); j++) {
                if (netusers.get(i).getPhone().equals(chatusers.get(j).getPhone())) {
                    netusers.get(i).setState(UserBean.STATE_ONLINE);
                    netusers.get(i).setChatid(chatusers.get(j).getChatid());
                    break;
                }
            }
        }
        return netusers;
    }


}
