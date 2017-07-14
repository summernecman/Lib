package com.siweisoft.service.ui.chat.videochat;

//by summer on 2017-07-04.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.system.HandleUtil;
import com.bairuitech.anychat.AnyChatCoreSDK;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.RoleInfo;
import com.siweisoft.service.videochat.chatutil.CallingCenter;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import butterknife.OnClick;

public class VideoChatFrag extends BaseUIFrag<VideoChatUIBean, VideoChatDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUi().initIcon(Value.userInfo);
        getOpes().getDa().setRoleInfo((RoleInfo) getArguments().getSerializable(ValueConstant.DATA_DATA2));
        ChatInit.getInstance().getAnyChatSDK().mSensorHelper.InitSensor(activity);                  // 启动 AnyChat 传感器监听
        AnyChatCoreSDK.mCameraHelper.SetContext(activity.getApplicationContext());                                          // 初始化 Camera 上下文句柄
        if (!Value.userInfo.type.get()) {
            // 设置录像格式（0表示mp4）
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_RECORD_FILETYPE, 0);
            getOpes().getUi().viewDataBinding.surfaceviewLocal.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //设置 SURFACE_TYPE_PUSH_BUFFERS 模式
            getOpes().getUi().viewDataBinding.surfaceviewLocal.getHolder().addCallback(AnyChatCoreSDK.mCameraHelper);// 打开本地视频预览，开始采集本地视频数据
            LogUtil.E("hh:" + getOpes().getDa().getRoleInfo().toString());
            if (AnyChatCoreSDK.GetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_CAPDRIVER) == AnyChatDefine.VIDEOCAP_DRIVER_JAVA) { // 如果是采用Java视频采集，则需要设置Surface的CallBack
                getOpes().getUi().viewDataBinding.surfaceviewLocal.getHolder().addCallback(AnyChatCoreSDK.mCameraHelper);
            }
            getOpes().getUi().viewDataBinding.surfaceviewLocal.setZOrderOnTop(true);
            ChatInit.getInstance().openLocalCamera();
            //打开音频
            ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()), 1);
        } else {
            if (AnyChatCoreSDK.GetSDKOptionInt(AnyChatDefine.BRAC_SO_VIDEOSHOW_DRIVERCTRL) == AnyChatDefine.VIDEOSHOW_DRIVER_JAVA) {// 如果是采用Java视频显示，则需要设置Surface的CallBack
                int index = ChatInit.getInstance().getAnyChatSDK().mVideoHelper.bindVideo(getOpes().getUi().viewDataBinding.surfaceviewLocal.getHolder());
                ChatInit.getInstance().getAnyChatSDK().mVideoHelper.SetVideoUser(index, Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()));
            }
            ChatInit.getInstance().openLocalCamera(Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()));
            ChatInit.getInstance().loadRemoveVideo(getOpes().getUi().viewDataBinding.surfaceviewLocal, Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()));
            HandleUtil.getInstance().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ChatInit.getInstance().startRecordVideo(Value.userInfo, getOpes().getDa().getRoleInfo());
                }
            }, 1000);
        }
    }

    @OnClick({R.id.btn_switchvideo, R.id.endCall, R.id.btn_cameraControl, R.id.btn_speakControl, R.id.btn_speakControl_remove})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_switchvideo:
                if (AnyChatCoreSDK.GetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_CAPDRIVER) == AnyChatDefine.VIDEOCAP_DRIVER_JAVA) {
                    AnyChatCoreSDK.mCameraHelper.SwitchCamera();
                    return;
                }
                String strVideoCaptures[] = ChatInit.getInstance().getAnyChatSDK().EnumVideoCapture();
                String temp = ChatInit.getInstance().getAnyChatSDK().GetCurVideoCapture();
                for (int i = 0; i < strVideoCaptures.length; i++) {
                    if (!temp.equals(strVideoCaptures[i])) {
                        ChatInit.getInstance().getAnyChatSDK().UserCameraControl(-1, 0);
                        ChatInit.getInstance().getAnyChatSDK().SelectVideoCapture(strVideoCaptures[i]);
                        ChatInit.getInstance().getAnyChatSDK().UserCameraControl(-1, 1);
                        break;
                    }
                }
                break;
            case R.id.endCall:
                ChatInit.getInstance().stopRecordVideo();
                CallingCenter.getInstance().VideoCallControl(AnyChatDefine.ANYCHAT_STREAMPLAY_EVENT_FINISH, Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()), AnyChatDefine.BRAC_ERRORCODE_SESSION_REFUSE, 0, 0, "");
                ChatInit.getInstance().closeRemoveVideo(Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()));
                ChatInit.getInstance().getAnyChatSDK().UserCameraControl(getArguments().getInt(ValueConstant.DATA_DATA), 0);
                ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(getArguments().getInt(ValueConstant.DATA_DATA), 0);
                ChatInit.getInstance().getAnyChatSDK().UserCameraControl(-1, 0);
                ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 0);
                ChatInit.getInstance().getAnyChatSDK().removeEvent(this);
                ChatInit.getInstance().getAnyChatSDK().mSensorHelper.DestroySensor();
                FragmentUtil.getInstance().removeTop(activity);
                break;
            case R.id.btn_speakControl:
                if ((Boolean) getOpes().getUi().viewDataBinding.btnSpeakControl.getTag(R.id.data)) {
                    getOpes().getUi().viewDataBinding.btnSpeakControl.setTag(R.id.data, false);
                    getOpes().getUi().viewDataBinding.btnSpeakControl.setImageResource(R.drawable.speak_off);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 0);
                } else {
                    getOpes().getUi().viewDataBinding.btnSpeakControl.setTag(R.id.data, true);
                    getOpes().getUi().viewDataBinding.btnSpeakControl.setImageResource(R.drawable.speak_on);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 1);
                }
                break;
            case R.id.btn_speakControl_remove:
                if ((Boolean) getOpes().getUi().viewDataBinding.btnSpeakControlRemove.getTag(R.id.data)) {
                    getOpes().getUi().viewDataBinding.btnSpeakControlRemove.setTag(R.id.data, false);
                    getOpes().getUi().viewDataBinding.btnSpeakControlRemove.setImageResource(R.drawable.speak_off);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()), 0);
                } else {
                    getOpes().getUi().viewDataBinding.btnSpeakControlRemove.setTag(R.id.data, true);
                    getOpes().getUi().viewDataBinding.btnSpeakControlRemove.setImageResource(R.drawable.speak_on);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()), 1);
                }
                break;
            case R.id.btn_cameraControl:
                if ((Boolean) getOpes().getUi().viewDataBinding.btnCameraControl.getTag(R.id.data)) {
                    getOpes().getUi().viewDataBinding.btnCameraControl.setTag(R.id.data, false);
                    getOpes().getUi().viewDataBinding.btnCameraControl.setImageResource(R.drawable.camera_off);
                    ChatInit.getInstance().getAnyChatSDK().UserCameraControl(Value.userInfo.type.get() ? Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()) : -1, 0);

                } else {
                    getOpes().getUi().viewDataBinding.btnCameraControl.setTag(R.id.data, true);
                    getOpes().getUi().viewDataBinding.btnCameraControl.setImageResource(R.drawable.camera_on);
                    ChatInit.getInstance().getAnyChatSDK().UserCameraControl(Value.userInfo.type.get() ? Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()) : -1, 1);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ChatInit.getInstance().stopRecordVideo();
        CallingCenter.getInstance().VideoCallControl(AnyChatDefine.ANYCHAT_STREAMPLAY_EVENT_FINISH, Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()), AnyChatDefine.BRAC_ERRORCODE_SESSION_REFUSE, 0, 0, "");
        ChatInit.getInstance().closeRemoveVideo(Integer.parseInt(getOpes().getDa().getRoleInfo().getUserID()));
        ChatInit.getInstance().getAnyChatSDK().UserCameraControl(getArguments().getInt(ValueConstant.DATA_DATA), 0);
        ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(getArguments().getInt(ValueConstant.DATA_DATA), 0);
        ChatInit.getInstance().getAnyChatSDK().UserCameraControl(-1, 0);
        ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 0);
        ChatInit.getInstance().getAnyChatSDK().removeEvent(this);
        ChatInit.getInstance().getAnyChatSDK().mSensorHelper.DestroySensor();
    }


}
