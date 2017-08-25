package com.siweisoft.service.ui.user.userlist;

//by summer on 2017-07-07.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.bairuitech.anychat.AnyChatDefine;
import com.bairuitech.anychat.AnyChatVideoCallEvent;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;
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
        final RoleInfo roleInfo = new RoleInfo();
        roleInfo.setName(ChatInit.getInstance().getAnyChatSDK().GetUserName(dwUserId));
        roleInfo.setUserID(dwUserId + "");
        switch (dwEventType) {
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST:
                LogUtil.E("oncllick" + ChatInit.getInstance().getAnyChatSDK().GetUserName(dwUserId) + "向你发来视频请求");
                CallingCenter.getInstance().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REPLY, dwUserId, AnyChatDefine.BRAC_ERRORCODE_SUCCESS, 0, 0, "");
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_REPLY:
                LogUtil.E("呼叫成功等待响应");
                ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_START, dwUserId, AnyChatDefine.BRAC_ERRORCODE_SESSION_QUIT, 0, 0, "");
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_START:
                LogUtil.E("视频呼叫会话开始事件");
                Fragment videofragment = new VideoChatFrag();
                Bundle bundle = new Bundle();
                bundle.putSerializable(ValueConstant.DATA_DATA2, roleInfo);
                videofragment.setArguments(bundle);
                FragmentUtil2.getInstance().add(fragment.getActivity(), Value.ROOTID_TWO, videofragment);
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_FINISH:
                LogUtil.E("挂断（结束）呼叫会话");
                if (FragmentUtil2.fragMap.get(Value.ROOTID_TWO) != null &&
                        FragmentUtil2.fragMap.get(Value.ROOTID_TWO).size() > 1 &&
                        FragmentUtil2.fragMap.get(Value.ROOTID_TWO).get(FragmentUtil2.fragMap.get(Value.ROOTID_TWO).size() - 1) instanceof VideoChatFrag) {
                    FragmentUtil2.getInstance().removeTop(fragment.getActivity(), Value.ROOTID_TWO);
                }
                break;
        }
    }
}
