package com.siweisoft.service.ui.main;

//by summer on 17-09-22.

import android.app.Activity;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.netdb.crash.CrashBean;
import com.siweisoft.service.netdb.crash.CrashI;
import com.siweisoft.service.netdb.crash.CrashOpe;
import com.siweisoft.service.ui.Constant.Value;

public class ChatConnectListener implements EMConnectionListener {

    private Activity app;

    CrashI crashI;

    public ChatConnectListener(Activity app) {
        this.app = app;
    }

    @Override
    public void onConnected() {
        LogUtil.E("onConnected");
    }

    @Override
    public void onDisconnected(int errorCode) {
        LogUtil.E("onDisconnected1" + errorCode);
        String s = "";
        switch (errorCode) {
            case EMError.USER_KICKED_BY_OTHER_DEVICE:
                s = "用户被其他设备踢掉";
                break;
            case EMError.USER_LOGIN_ANOTHER_DEVICE:
                s = "账户在另外一台设备登录";
                break;
            case EMError.SERVER_UNKNOWN_ERROR:
                s = "" + errorCode;
            case EMError.NETWORK_ERROR:
                reconnect();
                return;
            default:
                s = "应用断开了连接";
                break;
        }
        sendCrash(errorCode);
        disconc(s);

    }

    public void sendCrash(int errorCode) {
        if (errorCode != EMError.EM_NO_ERROR) {
            final CrashBean crashBean = new CrashBean();
            crashBean.setError(errorCode + "");
            crashBean.setCreatedtime(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
            crashBean.setUserBean(Value.getUserInfo());
            if (crashI == null) {
                crashI = new CrashOpe(app);
            }
            crashI.sendCrash(crashBean, new OnFinishListener() {
                @Override
                public void onFinish(Object o) {

                }
            });
        }
    }

    public void disconc(String s) {
        if (Value.getRoom() != null) {
            EMClient.getInstance().chatroomManager().leaveChatRoom(Value.getRoom().getId());
        }
        EMClient.getInstance().logout(true);
        LogUtil.E("onDisconnected2");
        final String finalS = s;
        app.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.getInstance().showLong(app, finalS);
                LogUtil.E("onDisconnected3");
                FragmentUtil2.getInstance().clear();
                ((ServieApp) app.getApplication()).exit();
                LogUtil.E("onDisconnected4");
            }
        });
    }

    public void reconnect() {
        EMClient.getInstance().login(Value.getUserInfo().getPhone(), "111111", new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
            }

            @Override
            public void onError(int code, String error) {
                sendCrash(EMError.NETWORK_ERROR);
                disconc(error);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }
}
