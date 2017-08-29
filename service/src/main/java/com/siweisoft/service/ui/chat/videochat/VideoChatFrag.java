package com.siweisoft.service.ui.chat.videochat;

//by summer on 2017-07-04.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.bairuitech.anychat.AnyChatCoreSDK;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.AnyChatRecordEventImp;
import com.siweisoft.service.videochat.chatutil.CallingCenter;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import butterknife.OnClick;

public class VideoChatFrag extends BaseUIFrag<VideoChatUIOpe, VideoChatDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().initIcon(Value.userBean);
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        LogUtil.E(getP().getD().getVideoBean());
        ChatInit.getInstance().getAnyChatSDK().mSensorHelper.InitSensor(activity);                  // 启动 AnyChat 传感器监听
        AnyChatCoreSDK.mCameraHelper.SetContext(activity.getApplicationContext());                                          // 初始化 Camera 上下文句柄
        if (Value.userBean.getUsertype() != 0) {
            // 设置录像格式（0表示mp4）
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_RECORD_FILETYPE, 0);
            getP().getU().bind.surfaceviewLocal.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //设置 SURFACE_TYPE_PUSH_BUFFERS 模式
            getP().getU().bind.surfaceviewLocal.getHolder().addCallback(AnyChatCoreSDK.mCameraHelper);// 打开本地视频预览，开始采集本地视频数据
            if (AnyChatCoreSDK.GetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_CAPDRIVER) == AnyChatDefine.VIDEOCAP_DRIVER_JAVA) { // 如果是采用Java视频采集，则需要设置Surface的CallBack
                getP().getU().bind.surfaceviewLocal.getHolder().addCallback(AnyChatCoreSDK.mCameraHelper);
            }
            getP().getU().bind.surfaceviewLocal.setZOrderOnTop(true);
            ChatInit.getInstance().openLocalCamera();
            //打开音频
            ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(getP().getD().getVideoBean().getOtherid()), 1);
            ChatInit.getInstance().getAnyChatSDK().SetRecordSnapShotEvent(new AnyChatRecordEventImp(activity));
        } else {
            if (AnyChatCoreSDK.GetSDKOptionInt(AnyChatDefine.BRAC_SO_VIDEOSHOW_DRIVERCTRL) == AnyChatDefine.VIDEOSHOW_DRIVER_JAVA) {// 如果是采用Java视频显示，则需要设置Surface的CallBack
                int index = ChatInit.getInstance().getAnyChatSDK().mVideoHelper.bindVideo(getP().getU().bind.surfaceviewLocal.getHolder());
                ChatInit.getInstance().getAnyChatSDK().mVideoHelper.SetVideoUser(index, Integer.parseInt(getP().getD().getVideoBean().getOtherid()));
            }
            ChatInit.getInstance().openLocalCamera(Integer.parseInt(getP().getD().getVideoBean().getOtherid()));
            ChatInit.getInstance().loadRemoveVideo(getP().getU().bind.surfaceviewLocal, Integer.parseInt(getP().getD().getVideoBean().getOtherid()));
            ChatInit.getInstance().getAnyChatSDK().SetRecordSnapShotEvent(new AnyChatRecordEventImp(activity));
            ChatInit.getInstance().startRecordVideo(Value.userBean, getP().getD().getVideoBean());
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
                ChatInit.getInstance().stopRecordVideo(Value.userBean);
                CallingCenter.getInstance().VideoCallControl(AnyChatDefine.ANYCHAT_STREAMPLAY_EVENT_FINISH, Integer.parseInt(getP().getD().getVideoBean().getOtherid()), AnyChatDefine.BRAC_ERRORCODE_SESSION_REFUSE, 0, 0, "");
                ChatInit.getInstance().closeRemoveVideo(Integer.parseInt(getP().getD().getVideoBean().getOtherid()));
                ChatInit.getInstance().getAnyChatSDK().UserCameraControl(Integer.parseInt(Value.userBean.getChatid()), 0);
                ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(Value.userBean.getChatid()), 0);
                ChatInit.getInstance().getAnyChatSDK().UserCameraControl(-1, 0);
                ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 0);
                ChatInit.getInstance().getAnyChatSDK().removeEvent(this);
                ChatInit.getInstance().getAnyChatSDK().mSensorHelper.DestroySensor();
                FragmentUtil2.getInstance().removeTop(activity, R.id.serviceroot);
                break;
            case R.id.btn_speakControl:
                if ((Boolean) getP().getU().bind.btnSpeakControl.getTag(R.id.data)) {
                    getP().getU().bind.btnSpeakControl.setTag(R.id.data, false);
                    getP().getU().bind.btnSpeakControl.setImageResource(R.drawable.speak_off);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 0);
                } else {
                    getP().getU().bind.btnSpeakControl.setTag(R.id.data, true);
                    getP().getU().bind.btnSpeakControl.setImageResource(R.drawable.speak_on);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 1);
                }
                break;
            case R.id.btn_speakControl_remove:
                if ((Boolean) getP().getU().bind.btnSpeakControlRemove.getTag(R.id.data)) {
                    getP().getU().bind.btnSpeakControlRemove.setTag(R.id.data, false);
                    getP().getU().bind.btnSpeakControlRemove.setImageResource(R.drawable.speak_off);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(getP().getD().getVideoBean().getOtherid()), 0);
                } else {
                    getP().getU().bind.btnSpeakControlRemove.setTag(R.id.data, true);
                    getP().getU().bind.btnSpeakControlRemove.setImageResource(R.drawable.speak_on);
                    ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(getP().getD().getVideoBean().getOtherid()), 1);
                }
                break;
            case R.id.btn_cameraControl:
                if ((Boolean) getP().getU().bind.btnCameraControl.getTag(R.id.data)) {
                    getP().getU().bind.btnCameraControl.setTag(R.id.data, false);
                    getP().getU().bind.btnCameraControl.setImageResource(R.drawable.camera_off);
                    //ChatInit.getInstance().getAnyChatSDK().UserCameraControl(Value.userBean.type.get() ? Integer.parseInt(getP().getD().getRoleInfo().getUserID()) : -1, 0);

                } else {
                    getP().getU().bind.btnCameraControl.setTag(R.id.data, true);
                    getP().getU().bind.btnCameraControl.setImageResource(R.drawable.camera_on);
                    //ChatInit.getInstance().getAnyChatSDK().UserCameraControl(Value.userBean.type.get() ? Integer.parseInt(getP().getD().getRoleInfo().getUserID()) : -1, 1);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ChatInit.getInstance().stopRecordVideo(Value.userBean);
        CallingCenter.getInstance().VideoCallControl(AnyChatDefine.ANYCHAT_STREAMPLAY_EVENT_FINISH, Integer.parseInt(getP().getD().getVideoBean().getOtherid()), AnyChatDefine.BRAC_ERRORCODE_SESSION_REFUSE, 0, 0, "");
        ChatInit.getInstance().closeRemoveVideo(Integer.parseInt(getP().getD().getVideoBean().getOtherid()));
        ChatInit.getInstance().getAnyChatSDK().UserCameraControl(Integer.parseInt(Value.userBean.getChatid()), 0);
        ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(Integer.parseInt(Value.userBean.getChatid()), 0);
        ChatInit.getInstance().getAnyChatSDK().UserCameraControl(-1, 0);
        ChatInit.getInstance().getAnyChatSDK().UserSpeakControl(-1, 0);
        ChatInit.getInstance().getAnyChatSDK().removeEvent(this);
        ChatInit.getInstance().getAnyChatSDK().mSensorHelper.DestroySensor();
    }


}
