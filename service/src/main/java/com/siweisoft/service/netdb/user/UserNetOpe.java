package com.siweisoft.service.netdb.user;

//by summer on 2017-07-12.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.netadapter.DelayUINetAdapter;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.siweisoft.service.ui.user.login.UserBean;

public class UserNetOpe extends BaseDAOpe implements UserI {

    public UserNetOpe(Context context) {
        super(context);
    }

    @Override
    public void registed(String username, OnFinishListener onFinishListener) {

    }

    @Override
    public void regist(final UserBean userBean, final OnFinishListener onFinishListener) {

    }

    @Override
    public void login(final UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/login", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }


    @Override
    public void getLoginInfo(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/getLoginInfo", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void loginOut(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/loginOut", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void setHeadUrl(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/setHeadurl", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void setName(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/setUserName", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }
}
