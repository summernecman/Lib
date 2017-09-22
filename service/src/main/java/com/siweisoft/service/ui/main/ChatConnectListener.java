package com.siweisoft.service.ui.main;

//by summer on 17-09-22.

import android.app.Activity;

import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.hyphenate.EMConnectionListener;
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
        EMClient.getInstance().logout(true);
        LogUtil.E("onDisconnected2");
        app.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.getInstance().showLong(app, "你的账号在另一台手机登录");
                LogUtil.E("onDisconnected3");
                ((ServieApp) app.getApplication()).exit();
                LogUtil.E("onDisconnected4");
            }
        });
    }
}
