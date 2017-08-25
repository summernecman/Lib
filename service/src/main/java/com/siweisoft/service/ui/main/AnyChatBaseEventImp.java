package com.siweisoft.service.ui.main;

//by summer on 2017-07-06.

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.bairuitech.anychat.AnyChatBaseEvent;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.user.userlist.UserListFrag;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import org.greenrobot.eventbus.EventBus;

public class AnyChatBaseEventImp implements AnyChatBaseEvent {


    FragmentActivity activity;

    public AnyChatBaseEventImp(FragmentActivity mainAct) {
        this.activity = mainAct;
    }

    /**
     * 表示是否连接成功
     *
     * @param b
     */
    @Override
    public void OnAnyChatConnectMessage(boolean b) {
        ToastUtil.getInstance().showShort(activity, "OnAnyChatConnectMessage" + b);
        //  FragmentUtil2.getInstance().removeTop(activity,R.id.act_base_root);
    }

    /***
     *
     * @param dwUserId         表示自己的用户 ID 号,当 dwErrorCode 为 0 时有效
     * @param dwErrorCode      错代码，可判断登录是否成功
     */
    @Override
    public void OnAnyChatLoginMessage(int dwUserId, int dwErrorCode) {
        LogUtil.E("OnAnyChatLoginMessage" + dwUserId + "--" + dwErrorCode);
        ChatInit.getInstance().enterRoom(VideoValue.URL.ROOMID, "");
        Value.userBean.setChatid(dwUserId + "");
        Value.userBean.setChatid(dwErrorCode + "");
        Intent intent = new Intent(activity, MainAct.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    /**
     * @param dwRoomId    表示进入的房间 ID 号
     * @param dwErrorCode 出错代码，可判断进入房间是否成功
     */
    @Override
    public void OnAnyChatEnterRoomMessage(int dwRoomId, int dwErrorCode) {
        LogUtil.E("OnAnyChatEnterRoomMessage" + dwRoomId + "" + dwErrorCode);
        ChatInit.getInstance().openLocalCamera();
    }

    /***
     *
     * @param dwUserNum         表示当前房间的在线用户数（包含自己）
     * @param dwRoomId          房间编号
     */
    @Override
    public void OnAnyChatOnlineUserMessage(int dwUserNum, int dwRoomId) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.sender = MainAct.class.getName();
        messageEvent.dealer = UserListFrag.class.getName();
        EventBus.getDefault().post(messageEvent);
    }

    /***
     *
     * @param dwUserId          表示当前房间活动用户的 ID 号
     * @param bEnter            true 表示进入房间，false 表示离开房间
     */
    @Override
    public void OnAnyChatUserAtRoomMessage(int dwUserId, boolean bEnter) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.sender = MainAct.class.getName();
        messageEvent.dealer = UserListFrag.class.getName();
        EventBus.getDefault().post(messageEvent);
    }

    /***
     *
     * @param dwReason          表示连接被关闭的原因
     */
    @Override
    public void OnAnyChatLinkCloseMessage(int dwReason) {

    }
}
