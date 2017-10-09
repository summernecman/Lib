package com.siweisoft.service.ui.main;

//by summer on 17-09-20.

import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.siweisoft.service.ui.chat.recept.ReceiptFrag;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import org.greenrobot.eventbus.EventBus;

public class VideoChatListener implements EMCallStateChangeListener {
    @Override
    public void onCallStateChanged(CallState callState, CallError error) {
        LogUtil.E(callState);
        switch (callState) {
            case ACCEPTED: // 电话接通成功
            case DISCONNECTED: // 电话断了
                MessageEvent v = new MessageEvent();
                v.sender = VideoChatListener.class.getName();
                v.dealer = ReceiptFrag.class.getName();
                v.data = callState;
                EventBus.getDefault().post(v);
            case VIDEO_PAUSE:
            case VOICE_PAUSE:
            case VIDEO_RESUME:
            case VOICE_RESUME:
                MessageEvent v1 = new MessageEvent();
                v1.sender = VideoChatListener.class.getName();
                v1.dealer = VideoChatFrag.class.getName();
                v1.data = callState;
                EventBus.getDefault().post(v1);
                break;
        }

    }
}
