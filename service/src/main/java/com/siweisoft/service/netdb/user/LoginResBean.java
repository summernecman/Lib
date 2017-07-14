package com.siweisoft.service.netdb.user;

import com.siweisoft.service.bean.UserReqBean;

import java.io.Serializable;

public class LoginResBean implements Serializable {

    boolean success;

    UserReqBean userBean;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserReqBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserReqBean userBean) {
        this.userBean = userBean;
    }
}