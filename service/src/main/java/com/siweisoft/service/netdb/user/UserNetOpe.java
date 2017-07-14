package com.siweisoft.service.netdb.user;

//by summer on 2017-07-12.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.service.bean.UserReqBean;
import com.siweisoft.service.ui.user.login.UserInfo;

public class UserNetOpe extends BaseDAOpe implements UserI {

    public UserNetOpe(Context context) {
        super(context);
    }

    @Override
    public void registed(String username, OnFinishListener onFinishListener) {

    }

    @Override
    public void regist(final UserInfo userInfo, final OnFinishListener onFinishListener) {
        final UserReqBean userReqBean = new UserReqBean();
        userReqBean.setIscustomer(userInfo.type.get() ? 1 : 0);
        userReqBean.setBelong("");
        userReqBean.setPhone(userInfo.name.get());
        userReqBean.setPwd(userInfo.pwd.get());
        userReqBean.setUsername(userInfo.name.get());


        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/eee/regist", userReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(b + "" + o);
                if (o.isException()) {
                    ToastUtil.getInstance().showShort(context, o.getErrorMessage());
                }
                onFinishListener.onFinish(b);
            }
        });

    }

    @Override
    public void login(final UserInfo userInfo, final OnFinishListener onFinishListener) {
        final UserReqBean userReqBean = new UserReqBean();
        userReqBean.setIscustomer(userInfo.type.get() ? 1 : 0);
        userReqBean.setBelong("");
        userReqBean.setPhone(userInfo.name.get());
        userReqBean.setPwd(userInfo.pwd.get());
        userReqBean.setUsername(userInfo.name.get());
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/eee/login", userReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(b + "" + o);
                LoginResBean loginResBean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), LoginResBean.class);
                userInfo.type.set(loginResBean.getUserBean().getIscustomer() == 0 ? false : true);
                onFinishListener.onFinish(loginResBean.isSuccess());
            }
        });
    }

    @Override
    public void logout(UserInfo userInfo, OnFinishListener onFinishListener) {

    }

    @Override
    public void getUserInfo(String name, final OnFinishListener onFinishListener) {
        final UserReqBean userReqBean = new UserReqBean();
        userReqBean.setPhone(name);
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/eee/getuserinfo", userReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(b + "" + o);
                if (o.getData() != null) {
                    UserReqBean userReqBean1 = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserReqBean.class);
                    onFinishListener.onFinish(userReqBean1);
                } else {
                    onFinishListener.onFinish(null);
                }

            }
        });
    }
}
