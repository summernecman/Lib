package com.siweisoft.service.ui.main;

//by summer on 17-09-22.

import android.app.Activity;

import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.ServieApp;

public class ChatConnectListener implements EMConnectionListener {

    private Activity app;

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
                s = "未知的server异常";
                break;
            default:
                s = "应用断开了链接";
                break;
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
}
