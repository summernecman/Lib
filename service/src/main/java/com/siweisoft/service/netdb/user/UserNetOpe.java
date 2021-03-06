package com.siweisoft.service.netdb.user;

//by summer on 2017-07-12.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.netadapter.DelayUINetAdapter;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.netdb.video.VideoTimeBean;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

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
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/getLoginInfo", baseReqBean, new OnNetWorkReqAdapter(context) {
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

    @Override
    public void getUserCallInfo(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getusercallinfo", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                VideoTimeBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), VideoTimeBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUsersInfoByPhone(ArrayList<UserBean> userBeen, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBeen));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getUsersInfoByPhone", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> userBeen, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBeen));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getArrayUsersInfoByPhone", baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<ArrayList<UserBean>> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<ArrayList<UserBean>>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }
}
