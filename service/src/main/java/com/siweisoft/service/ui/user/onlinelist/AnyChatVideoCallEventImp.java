package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-07.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.bairuitech.anychat.AnyChatDefine;
import com.bairuitech.anychat.AnyChatVideoCallEvent;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.remark.RemarkFrag;
import com.siweisoft.service.ui.main.RoleInfo;
import com.siweisoft.service.videochat.chatutil.CallingCenter;
import com.siweisoft.service.videochat.chatutil.ChatInit;

public class AnyChatVideoCallEventImp implements AnyChatVideoCallEvent {

    private Fragment fragment;

    public AnyChatVideoCallEventImp(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void OnAnyChatVideoCallEvent(int dwEventType, final int dwUserId, int dwErrorCode, int dwFlags, int dwParam, String userStr) {
        LogUtil.E(dwEventType + "-" + dwUserId + "-" + dwUserId + "-" + dwErrorCode + "-" + dwFlags + "-" + dwParam + "-" + "-" + userStr);
        switch (dwEventType) {
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST:
                LogUtil.E("oncllick" + ChatInit.getInstance().getAnyChatSDK().GetUserName(dwUserId) + "向你发来视频请求");
                CallingCenter.getInstance().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REPLY, dwUserId, AnyChatDefine.BRAC_ERRORCODE_SUCCESS, 0, 0, userStr);
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_REPLY:
                LogUtil.E("呼叫成功等待响应");
                ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_START, dwUserId, AnyChatDefine.BRAC_ERRORCODE_SESSION_QUIT, 0, 0, userStr);
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_START:
                final RoleInfo roleInfo = new RoleInfo();
                roleInfo.setName(ChatInit.getInstance().getAnyChatSDK().GetUserName(dwUserId));
                roleInfo.setUserID(dwUserId + "");

                VideoBean videoBean = GsonUtil.getInstance().fromJson(userStr, VideoBean.class);
                if (videoBean.getFromphone().equals(Value.userBean.getPhone())) {
                    videoBean.setTochatid(dwUserId + "");
                    videoBean.setFromchatid(Value.userBean.getChatid());
                } else {
                    videoBean.setFromchatid(dwUserId + "");
                    videoBean.setTochatid(Value.userBean.getChatid());
                }

                RemarkFrag remarkFrag = new RemarkFrag();
                remarkFrag.setArguments(new Bundle());
                remarkFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, videoBean);
                FragmentUtil2.getInstance().add(fragment.getActivity(), Value.getNowRoot(), remarkFrag);


                LogUtil.E("视频呼叫会话开始事件" + userStr);

                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_FINISH:
                LogUtil.E("挂断（结束）呼叫会话");
                FragmentUtil2.getInstance().removeTop(fragment.getActivity(), Value.FULLSCREEN);
                break;
        }
    }
}
