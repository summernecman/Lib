package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LoadUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.system.UUUIDUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.Constant.Value;

public class LoginDAOpe extends BaseDAOpe {

    UserBean userBean = new UserBean();

    UserI userI;


    public LoginDAOpe(Context context) {
        super(context);
        userBean.setPhone(SPUtil.getInstance().getStr(Value.USERNAME));
        userBean.setPwd(SPUtil.getInstance().getStr(Value.PWD));
        userBean.setUuuid(UUUIDUtil.getInstance().getUUUId(context));
        userI = new UserNetOpe(context);
    }

    public void autoLogin() {

    }

    public void login(UserBean userBean, final OnFinishListener onFinishListener) {
        LoadUtil.getInstance().onStartLoading(context, "login");
        userI.login(userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                final BaseResBean baseResBean = (BaseResBean) o;
                if (baseResBean.isException()) {
                    LoadUtil.getInstance().onStopLoading("login");
                    onFinishListener.onFinish(baseResBean);
                    return;
                }
                final UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getData()), UserBean.class);
                if (res == null) {
                    LoadUtil.getInstance().onStopLoading("login");
                    return;
                }
                EMClient.getInstance().login(res.getPhone(), "111111", new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        LogUtil.E("main", "登录聊天服务器成功！");
                        baseResBean.setData(res);
                        onFinishListener.onFinish(baseResBean);
                        LoadUtil.getInstance().onStopLoading("login");
                        SPUtil.getInstance().init(context).saveStr(Value.USERNAME, res.getPhone());
                        SPUtil.getInstance().saveStr(Value.PWD, res.getPwd());
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        LogUtil.E("main", code + "" + message);
                        baseResBean.setException(true);
                        baseResBean.setErrorMessage(message);
                        onFinishListener.onFinish(baseResBean);
                        LoadUtil.getInstance().onStopLoading("login");
                    }
                });
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
