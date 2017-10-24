package com.siweisoft.service.ui.jpush;

//by summer on 17-10-24.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.lib.util.LogUtil;

import cn.jpush.android.api.JPushInterface;

public class JpushSdkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.E(intent.getAction());
        //{"message":"rfdfdsfsafd","title":"","ad_id":"4178287839","show_type":4}
        switch (intent.getAction()) {
            case JPushInterface.ACTION_MESSAGE_RECEIVED:
                LogUtil.E(intent.getExtras().getString(JPushInterface.EXTRA_MESSAGE));
                break;
        }
    }
}
