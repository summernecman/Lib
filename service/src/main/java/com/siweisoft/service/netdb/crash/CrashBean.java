package com.siweisoft.service.netdb.crash;

//by summer on 17-09-08.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.siweisoft.service.netdb.user.UserBean;

public class CrashBean extends BaseBean {

    private int id;

    private String error;

    private String createdtime;

    private UserBean userBean;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Bindable
    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    @Bindable
    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
