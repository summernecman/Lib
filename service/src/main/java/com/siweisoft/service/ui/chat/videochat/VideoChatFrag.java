package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.View;

import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.hyphenate.exceptions.EMServiceNotReadyException;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.util.RecordService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

import static android.content.Context.MEDIA_PROJECTION_SERVICE;

public class VideoChatFrag extends BaseServerFrag<VideoChatUIOpe, VideoChatDAOpe> {


    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;
    private static final int AUDIO_REQUEST_CODE = 103;

    private MediaProjectionManager projectionManager;
    private MediaProjection mediaProjection;
    private RecordService recordService;



    @Override
    public void doThing() {
        super.doThing();
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(Value.DATA_DATA));


        try {//单参数
            EMClient.getInstance().callManager().makeVideoCall(getP().getD().getVideoBean().getToUser().getPhone(), GsonUtil.getInstance().toJson(getP().getD().getVideoBean()));
        } catch (EMServiceNotReadyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (getP().getD().isLocalSendVideo(Value.userBean, getP().getD().getVideoBean().getToUser())) {
            EMClient.getInstance().callManager().setSurfaceView(getP().getU().bind.surfaceviewLocal, null);
        } else {
            EMClient.getInstance().callManager().setSurfaceView(null, getP().getU().bind.surfaceviewLocal);
        }


        projectionManager = (MediaProjectionManager) activity.getSystemService(MEDIA_PROJECTION_SERVICE);


        Intent intent = new Intent(activity, RecordService.class);
        activity.bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activity.unbindService(connection);
        recordService.stopRecord();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            RecordService.RecordBinder binder = (RecordService.RecordBinder) service;
            recordService = binder.getRecordService();
            recordService.setConfig(metrics.widthPixels, metrics.heightPixels, metrics.densityDpi);


            Intent captureIntent = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                captureIntent = projectionManager.createScreenCaptureIntent();
            }
            startActivityForResult(captureIntent, RECORD_REQUEST_CODE);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECORD_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mediaProjection = projectionManager.getMediaProjection(resultCode, data);
            }
            recordService.setMediaProject(mediaProjection);
            recordService.startRecord();
        }
    }


    @Override
    public void initData() {
        super.initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CommentBean bean) {
        try {
            EMClient.getInstance().callManager().endCall();
        } catch (EMNoActiveCallException e) {
            e.printStackTrace();
        }
        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
    }

    @OnClick({R.id.endCall, R.id.btn_switchvideo})
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

        }
    }
}
