package com.siweisoft.service.ui.main;

//by summer on 17-09-20.

import android.content.Context;

import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.siweisoft.service.ui.chat.recept.ReceiptFrag;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import org.greenrobot.eventbus.EventBus;

public class VideoChatListener implements EMCallStateChangeListener {

    private Context context;

    public VideoChatListener(Context context) {
        this.context = context;
    }

    public VideoChatListener() {
    }

    @Override
    public void onCallStateChanged(CallState callState, CallError error) {
        LogUtil.E("callstate" + callState.name() + ":" + error.name());
        switch (error) {
            case ERROR_BUSY:
                ToastUtil.getInstance().showShort(context, "对方正忙");
                break;
            case ERROR_NORESPONSE:
                ToastUtil.getInstance().showShort(context, "对方无反应");
                break;
            case REJECTED:
                ToastUtil.getInstance().showShort(context, "对方已挂断");
                break;
            case ERROR_UNAVAILABLE:
                ToastUtil.getInstance().showShort(context, "无法连接对方,对方可能不在线");
                break;
            default:
                //ToastUtil.getInstance().showShort(context, ""+error.toString());
                break;
        }


        switch (callState) {
            case DISCONNECTED: // 电话断了
                MessageEvent v = new MessageEvent();
                v.sender = VideoChatListener.class.getName();
                v.dealer = ReceiptFrag.class.getName();
                v.data = callState;
                EventBus.getDefault().post(v);
            case ACCEPTED: // 电话接通成功
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
