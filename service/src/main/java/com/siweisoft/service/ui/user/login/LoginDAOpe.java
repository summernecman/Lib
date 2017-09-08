package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.system.UUUIDUtil;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;

public class LoginDAOpe extends BaseDAOpe {

    UserBean userBean = new UserBean();

    UserI userI;


    public LoginDAOpe(Context context) {
        super(context);
        userBean.setPhone("18721607438");
        userBean.setPwd("111111");
        userBean.setUuuid(UUUIDUtil.getInstance().getUUUId(context));
        userI = new UserNetOpe(context);
    }

    public void login(UserBean userBean, final OnFinishListener onFinishListener) {
        userI.login(userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                UserBean res = (UserBean) o;
                onFinishListener.onFinish(res);
            }
        });
    }

    public String getImageUril() {
        return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504080205794&di=f1615a7fc30840be57ff68b24e6f953e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F342ac65c103853437fc566079913b07eca80888a.jpg";
    }


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }


}
