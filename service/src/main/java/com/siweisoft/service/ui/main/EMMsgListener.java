package com.siweisoft.service.ui.main;

//by summer on 17-09-21.

import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.siweisoft.service.ui.chat.remark.RemarkFrag;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class EMMsgListener implements EMMessageListener {
    @Override
    public void onMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        if (messages.get(0).getBody() instanceof EMCmdMessageBody) {
            EMCmdMessageBody emCmdMessageBody = (EMCmdMessageBody) messages.get(0).getBody();
            MessageEvent messageEvent = new MessageEvent(EMMsgListener.class.getName(), RemarkFrag.class.getName(), emCmdMessageBody.action());
            EventBus.getDefault().post(messageEvent);
        }

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }
}
