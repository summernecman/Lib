package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-20.

import com.android.lib.util.LogUtil;
import com.hyphenate.chat.EMCallStateChangeListener;

import org.greenrobot.eventbus.EventBus;

public class VideoChatListener implements EMCallStateChangeListener {
    @Override
    public void onCallStateChanged(CallState callState, CallError error) {
        LogUtil.E(callState);
        switch (callState) {
            case CONNECTING: // 正在连接对方

                break;
            case CONNECTED: // 双方已经建立连接

                break;

            case ACCEPTED: // 电话接通成功
                VideoChatMsg videoChatMsg = new VideoChatMsg();
                videoChatMsg.code = VideoChatMsg.CODE_START_RECORD;
                EventBus.getDefault().post(videoChatMsg);
                break;
            case DISCONNECTED: // 电话断了
                VideoChatMsg videoChatMsg1 = new VideoChatMsg();
                videoChatMsg1.code = VideoChatMsg.CODE_END_RECORD;
                EventBus.getDefault().post(videoChatMsg1);
                break;
            case NETWORK_UNSTABLE: //网络不稳定
                if (error == CallError.ERROR_NO_DATA) {
                    //无通话数据
                } else {
                }
                break;
            case NETWORK_NORMAL: //网络恢复正常

                break;
            default:
                break;
        }

    }
}
