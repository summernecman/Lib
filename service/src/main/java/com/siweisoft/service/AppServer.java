package com.siweisoft.service;

//by summer on 17-10-30.

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.lib.util.LogUtil;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.ui.Constant.Value;

public class AppServer extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        LogUtil.E(2333333);
        EMClient.getInstance().chatroomManager().leaveChatRoom(Value.roomid);
        EMClient.getInstance().logout(true);
        super.onTaskRemoved(rootIntent);
    }
}
