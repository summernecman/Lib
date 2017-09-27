package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.HandleUtil;
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

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

public class VideoChatFrag extends BaseServerFrag<VideoChatUIOpe, VideoChatDAOpe> {




    @Override
    public void doThing() {
        super.doThing();
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(Value.DATA_DATA));
        //发起者
        if (getP().getD().getVideoBean().getFromUser().getPhone().equals(Value.userBean.getPhone())) {
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

                    if (getP().getD().isLocalSendVideo(Value.userBean, getP().getD().getVideoBean().getToUser())) {
                        EMClient.getInstance().callManager().setSurfaceView(getP().getU().bind.surfaceview, null);
                    } else {
                        EMClient.getInstance().callManager().setSurfaceView(null, getP().getU().bind.surfaceview);
                    }


                }
            });

        } else {
            //接受者
            try {
                EMClient.getInstance().callManager().answerCall();
            } catch (EMNoActiveCallException e) {
                e.printStackTrace();
            }

            if (getP().getD().isLocalSendVideo(Value.userBean, getP().getD().getVideoBean().getToUser())) {
                EMClient.getInstance().callManager().setSurfaceView(getP().getU().bind.surfaceview, null);
            } else {
                EMClient.getInstance().callManager().setSurfaceView(null, getP().getU().bind.surfaceview);
            }

            getP().getD().setAccept(true);
            LogUtil.E("接受到录音指令3");
            getP().getD().setStart(System.currentTimeMillis());
            //非发送视频信息方将录制视频
            if (!getP().getD().isLocalSendVideo(Value.userBean, getP().getD().getVideoBean().getToUser())) {
                ToastUtil.getInstance().showLong(activity, "3s后开始录制");
                HandleUtil.getInstance().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getP().getD().setStart(System.currentTimeMillis());
                        EMClient.getInstance().callManager().getVideoCallHelper().startVideoRecord(VideoValue.getRecordFileDir().getPath());
                        ToastUtil.getInstance().showLong(activity, "开始录制");
                    }
                }, 3000);
            }

        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        getP().getU().bind.surfaceview.release();
        getP().getD().setEnd(System.currentTimeMillis());
        if (getP().getD().getVideoBean() == null) {
            return;
        }
        if (!getP().getD().isAccept()) {
            return;
        }
        //录制方
        if (!getP().getD().isLocalSendVideo(Value.userBean, getP().getD().getVideoBean().getToUser())) {
            String s = EMClient.getInstance().callManager().getVideoCallHelper().stopVideoRecord();
            LogUtil.E(s);
            getP().getD().getVideoBean().setFile(s);
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
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(VideoChatMsg msg) {
        switch (msg.code) {
            case VideoChatMsg.CODE_END_RECORD:
                try {
                    EMClient.getInstance().callManager().endCall();
                } catch (EMNoActiveCallException e) {
                    e.printStackTrace();
                }
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                break;
            case VideoChatMsg.CODE_START_RECORD:
                LogUtil.E("接受到录音指令2");
                getP().getD().setAccept(true);
                //建立通话后 视频发起者 并且 非视频信息发送者 将录制视频
                if (getP().getD().getVideoBean().getFromUser().getPhone().equals(Value.userBean.getPhone()) && !getP().getD().isLocalSendVideo(Value.userBean, getP().getD().getVideoBean().getToUser())) {
                    ToastUtil.getInstance().showLong(activity, "3s后开始录制");
                    HandleUtil.getInstance().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getP().getD().setStart(System.currentTimeMillis());
                            EMClient.getInstance().callManager().getVideoCallHelper().startVideoRecord(VideoValue.getRecordFileDir().getPath());
                            ToastUtil.getInstance().showLong(activity, "开始录制");
                        }
                    }, 3000);
                }
                break;
        }

    }

    @OnClick({R.id.endCall, R.id.btn_switchvideo, R.id.btn_speak, R.id.btn_camera})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.endCall:
                try {
                    EMClient.getInstance().callManager().endCall();
                } catch (EMNoActiveCallException e) {
                    e.printStackTrace();
                }
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
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
