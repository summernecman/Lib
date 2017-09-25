package com.siweisoft.service.bean;

//by summer on 17-09-11.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.siweisoft.service.netdb.user.UserBean;

import java.util.ArrayList;

public class AllUserBean extends BaseBean {

    private UserBean me;

    private ArrayList<UserBean> other;

    public AllUserBean() {
    }

    public AllUserBean(UserBean me, ArrayList<UserBean> other) {
        this.me = me;
        this.other = other;
    }

    @Bindable
    public UserBean getMe() {
        return me;
    }

    public void setMe(UserBean me) {
        this.me = me;
    }

    @Bindable
    public ArrayList<UserBean> getOther() {
        return other;
    }

    public void setOther(ArrayList<UserBean> other) {
        this.other = other;
    }


}
