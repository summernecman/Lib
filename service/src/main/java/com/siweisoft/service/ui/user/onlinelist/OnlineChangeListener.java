package com.siweisoft.service.ui.user.onlinelist;

//by summer on 17-09-25.

import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.EMChatRoomChangeListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class OnlineChangeListener implements EMChatRoomChangeListener {

    @Override
    public void onChatRoomDestroyed(String roomId, String roomName) {
        LogUtil.E("onMemberJoined", roomName);
    }

    @Override
    public void onMemberJoined(String roomId, String participant) {
        LogUtil.E("onMemberJoined", participant);
        MessageEvent messageEvent = new MessageEvent(OnlineChangeListener.class.getName(), OnLineListFrag.class.getName(), participant);
        EventBus.getDefault().post(messageEvent);
    }

    @Override
    public void onMemberExited(String roomId, String roomName, String participant) {
        LogUtil.E("onMemberExited", "" + participant);
        MessageEvent messageEvent = new MessageEvent(OnlineChangeListener.class.getName(), OnLineListFrag.class.getName(), participant);
        EventBus.getDefault().post(messageEvent);
    }

    @Override
    public void onRemovedFromChatRoom(String roomId, String roomName, String participant) {
        LogUtil.E("onRemovedFromChatRoom", "" + participant);
    }

    @Override
    public void onMuteListAdded(String chatRoomId, List<String> mutes, long expireTime) {
        LogUtil.E("onMuteListAdded", "" + mutes);
    }

    @Override
    public void onMuteListRemoved(String chatRoomId, List<String> mutes) {
        LogUtil.E("onMuteListRemoved", "" + mutes);
    }

    @Override
    public void onAdminAdded(String chatRoomId, String admin) {
        LogUtil.E("onAdminAdded", "" + admin);
    }

    @Override
    public void onAdminRemoved(String chatRoomId, String admin) {
        LogUtil.E("onAdminRemoved", "" + admin);
    }

    @Override
    public void onOwnerChanged(String chatRoomId, String newOwner, String oldOwner) {
        LogUtil.E("onOwnerChanged", "" + newOwner);
    }

    @Override
    public void onAnnouncementChanged(String chatRoomId, String announcement) {
        LogUtil.E("onAnnouncementChanged", announcement);
    }
}
