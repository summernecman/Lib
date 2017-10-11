package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnLoadingInterf;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.hyphenate.exceptions.EMServiceNotReadyException;
import com.hyphenate.exceptions.HyphenateException;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.chat.remark.RemarkFrag;
import com.siweisoft.service.ui.main.VideoChatListener;

import butterknife.OnClick;

public class VideoChatFrag extends BaseServerFrag<VideoChatUIOpe, VideoChatDAOpe> {




    @Override
    public void doThing() {
        super.doThing();
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(Value.DATA_DATA));
        //发起者
        if (getP().getD().isFromUser(getP().getD().getVideoBean())) {
            //发起视频者先创建一条视频记录
            getP().getD().insert_and_getid_fromvieo(getP().getD().getVideoBean(), new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    //获取创建的视频的相关信息 以及id
                    getP().getD().setVideoBean((VideoBean) o);
                    try {//发起通话 并将视频通话双方信息发送给接受方
                        EMClient.getInstance().callManager().makeVideoCall(getP().getD().getVideoBean().getToUser().getPhone(), GsonUtil.getInstance().toJson(getP().getD().getVideoBean()));
                    } catch (EMServiceNotReadyException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (getP().getD().isSendVideo(getP().getD().getVideoBean())) {
                        EMClient.getInstance().callManager().setSurfaceView(getP().getU().bind.surfaceview, null);
                    } else {
                        EMClient.getInstance().callManager().setSurfaceView(null, getP().getU().bind.surfaceview);
                        getP().getU().bind.btnCamera.setVisibility(View.GONE);
                        getP().getU().bind.btnSwitchvideo.setVisibility(View.GONE);
                    }
                    getP().getU().setCallInfo(getP().getD().getVideoBean().getToUser());

                }
            });
        } else {
            //接受者
            try {
                EMClient.getInstance().callManager().answerCall();
            } catch (EMNoActiveCallException e) {
                e.printStackTrace();
            }

            if (getP().getD().isSendVideo(getP().getD().getVideoBean())) {
                EMClient.getInstance().callManager().setSurfaceView(getP().getU().bind.surfaceview, null);
            } else {
                EMClient.getInstance().callManager().setSurfaceView(null, getP().getU().bind.surfaceview);
                getP().getU().bind.btnCamera.setVisibility(View.GONE);
                getP().getU().bind.btnSwitchvideo.setVisibility(View.GONE);
            }
//            getP().getD().setAccept(true);
//            LogUtil.E("接受到录音指令3");
//            getP().getD().setStart(System.currentTimeMillis());
//            //非发送视频信息方将录制视频
//            if (!getP().getD().isSendVideo(getP().getD().getVideoBean())) {
//                ToastUtil.getInstance().showLong(activity, "3s后开始录制");
//                HandleUtil.getInstance().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        getP().getD().setStart(System.currentTimeMillis());
//                        EMClient.getInstance().callManager().getVideoCallHelper().startVideoRecord(VideoValue.getRecordFileDir().getPath());
//                        ToastUtil.getInstance().showLong(activity, "开始录制");
//                    }
//                }, 3000);
//            }
        }
    }



    @Override
    public void onDestroy() {
        getP().getD().getThreadUtil().stop();
        getP().getD().setEnd(System.currentTimeMillis());
        if (getP().getD().getVideoBean() == null) {
            return;
        }
        //录制方
        if (!getP().getD().isSendVideo(getP().getD().getVideoBean())) {
            LogUtil.E(getP().getD().getPath());
            getP().getD().getVideoBean().setFile(getP().getD().getPath());
            getP().getD().getVideoBean().setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
            getP().getD().getVideoBean().setTimenum(getP().getD().getMinute());
        } else {
            //非录制方
            getP().getD().getVideoBean().setFile("");
            getP().getD().getVideoBean().setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
            getP().getD().getVideoBean().setTimenum(getP().getD().getMinute());
        }
        RemarkFrag remarkFrag = new RemarkFrag();
        remarkFrag.setArguments(new Bundle());
        remarkFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideoBean());
        FragmentUtil2.getInstance().add(activity, Value.ROOTID_TWO, remarkFrag);
        getP().getU().bind.surfaceview.release();
        super.onDestroy();
    }


    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        if (VideoChatListener.class.getName().equals(event.sender) && VideoChatFrag.class.getName().equals(event.dealer)) {
            final EMCallStateChangeListener.CallState state = (EMCallStateChangeListener.CallState) event.data;
            switch (state) {
                case VIDEO_PAUSE:
                    ToastUtil.getInstance().showShort(activity, "对方关闭了视频");
                    break;
                case VOICE_PAUSE:
                    ToastUtil.getInstance().showShort(activity, "对方关闭了音频");
                    break;
                case VIDEO_RESUME:
                    ToastUtil.getInstance().showShort(activity, "对方恢复了视频");
                    break;
                case VOICE_RESUME:
                    ToastUtil.getInstance().showShort(activity, "对方恢复了音频");
                    break;
                case DISCONNECTED:
                    String s = EMClient.getInstance().callManager().getVideoCallHelper().stopVideoRecord();
                    if (!NullUtil.isStrEmpty(s)) {
                        getP().getD().setPath(s);
                    }
                    ToastUtil.getInstance().showShort(activity, "通话已结束");
                    try {
                        EMClient.getInstance().callManager().endCall();
                    } catch (EMNoActiveCallException e) {
                        e.printStackTrace();
                    }
                    FragmentUtil2.getInstance().removeTop(activity, Value.FULLSCREEN);
                    break;
                case ACCEPTED:
                    LogUtil.E("接受到录音指令2");
                    getP().getU().hideCallInfo();
                    //建立通话后
                    if (!getP().getD().isSendVideo(getP().getD().getVideoBean())) {
                        ToastUtil.getInstance().showLong(activity, "开始录制");
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getP().getD().setStart(System.currentTimeMillis());
                                EMClient.getInstance().callManager().getVideoCallHelper().stopVideoRecord();
                                EMClient.getInstance().callManager().getVideoCallHelper().startVideoRecord(VideoValue.getRecordFileDir().getPath());
                                ToastUtil.getInstance().showLong(activity, "开始录制");
                            }
                        });
                    }

                    getP().getD().getThreadUtil().run(1000, new OnLoadingInterf() {
                        @Override
                        public Void onStarLoading(Object o) {
                            getP().getU().bind.tvTime.setText(StringUtil.secondToMinute((int) o));
                            if ((int) o == 300) {
                                //非发送视频信息方将录制视频
                                if (!getP().getD().isLocalSendVideo(Value.userBean, getP().getD().getVideoBean().getToUser())) {
                                    ToastUtil.getInstance().showShort(activity, "已经视频超过5分钟,为了保证网络不好的情况下视频文件的传输,建议结束当前视频，重新发起视频");
                                }
                            }
                            return null;
                        }

                        @Override
                        public Void onStopLoading(Object o) {
                            return null;
                        }
                    });
                    break;
            }
        }
    }

    @OnClick({R.id.endCall, R.id.btn_switchvideo, R.id.btn_speak, R.id.btn_camera})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.endCall:
//                String s= EMClient.getInstance().callManager().getVideoCallHelper().stopVideoRecord();
//                getP().getD().setPath(s);
                try {
                    EMClient.getInstance().callManager().endCall();
                } catch (EMNoActiveCallException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_switchvideo:
                EMClient.getInstance().callManager().switchCamera();
                break;
            case R.id.btn_speak:
                getP().getU().bind.btnSpeak.setSelected(!getP().getU().bind.btnSpeak.isSelected());
                if (getP().getU().bind.btnSpeak.isSelected()) {
                    try {
                        EMClient.getInstance().callManager().pauseVoiceTransfer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        EMClient.getInstance().callManager().resumeVoiceTransfer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_camera:
                getP().getU().bind.btnCamera.setSelected(!getP().getU().bind.btnCamera.isSelected());
                if (getP().getU().bind.btnCamera.isSelected()) {
                    try {
                        EMClient.getInstance().callManager().pauseVideoTransfer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        EMClient.getInstance().callManager().resumeVideoTransfer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
