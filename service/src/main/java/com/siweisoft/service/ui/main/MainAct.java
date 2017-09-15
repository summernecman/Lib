package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.exception.exception.CrashHander;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.UUUIDUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.siweisoft.service.R;
import com.siweisoft.service.netdb.crash.CrashBean;
import com.siweisoft.service.netdb.crash.CrashI;
import com.siweisoft.service.netdb.crash.CrashOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.OnClick;
import butterknife.Optional;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> implements OnFinishListener {

    LoginInfoBroadCast loginInfoBroadCast;

    CrashI crashI;

    OnTitleClick onTitleClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initViewPager(getSupportFragmentManager(), getP().getD().getFragment());
        //FragmentUtil2.getInstance().add(activity, R.id.serviceroot,new HomeFrag());
        loginInfoBroadCast = new LoginInfoBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + ValueConstant.ACITON_GLOB_CAST);
        registerReceiver(loginInfoBroadCast, intentFilter);
        CrashHander.getInstance().init(this, this);


        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
        registerReceiver(new CallReceiver(), callFilter);


        EMClient.getInstance().callManager().addCallStateChangeListener(new EMCallStateChangeListener() {
            @Override
            public void onCallStateChanged(CallState callState, CallError error) {
                LogUtil.E(callState);
                switch (callState) {
                    case CONNECTING: // 正在连接对方
                        break;
                    case CONNECTED: // 双方已经建立连接
                        try {
                            EMClient.getInstance().callManager().answerCall();
                        } catch (EMNoActiveCallException e) {
                            e.printStackTrace();
                        }
                        break;

                    case ACCEPTED: // 电话接通成功
                        File file = new File(Environment.getExternalStorageDirectory(), "files");
                        if (!file.exists()) {
                            file.mkdirs();
                        }

                        EMClient.getInstance().callManager().getVideoCallHelper().startVideoRecord(file.getPath());
                        break;
                    case DISCONNECTED: // 电话断了
                        LogUtil.E(EMClient.getInstance().callManager().getVideoCallHelper().stopVideoRecord());
                        MessageEvent messageEvent = new MessageEvent();
                        messageEvent.sender = MainAct.class.getName();
                        messageEvent.dealer = VideoChatFrag.class.getName();
                        EventBus.getDefault().post(messageEvent);
                        break;
                    case NETWORK_UNSTABLE: //网络不稳定
                        if (error == CallError.ERROR_NO_DATA) {
                            //无通话数据
                        } else {
                        }
                        break;
                    case NETWORK_NORMAL: //网络恢复正常

                        break;
                    default:
                        break;
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }

        if (FragmentUtil2.fragMap.get(Value.FULLSCREEN) != null && FragmentUtil2.fragMap.get(Value.FULLSCREEN).size() == 1) {
            FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
            return;
        }

        if (FragmentUtil2.fragMap.get(Value.getNowRoot()).size() == 1) {
//            ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
//            ChatInit.getInstance().doLoginOut();
//            ((ServieApp) getApplication()).exit();
            ToastUtil.getInstance().showShort(activity, "请从设置里退出");
        } else {
            FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(loginInfoBroadCast);
    }


    @Optional
    @OnClick({R.id.ftv_back, R.id.ftv_title, R.id.ftv_right})
    public void onClickEventListener(View v) {
        if (onTitleClick != null) {
            boolean b = onTitleClick.onTitleClick(v);
            if (b) {
                return;
            }
        }
        switch (v.getId()) {
            case R.id.ftv_title:
                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
        }
    }

    @Override
    public void onFinish(Object o) {
        if (crashI == null) {
            crashI = new CrashOpe(this);
        }
        final CrashBean crashBean = new CrashBean();
        crashBean.setError((String) o);
        crashBean.setCreatedtime(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        crashBean.setUserBean(Value.userBean);
        crashI.sendCrash(crashBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {

            }
        });
    }

    public void setOnTitleClick(MainAct.OnTitleClick onTitleClick) {
        this.onTitleClick = onTitleClick;
    }

    class LoginInfoBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            getP().getD().getLoginInfo(new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    UserBean userBean = (UserBean) o;
                    if (!UUUIDUtil.getInstance().getUUUId(activity).equals(userBean.getUuuid())) {
                        activity.finish();
                    }

                }
            });
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        //LogUtil.E(ChatInit.getInstance().getAnyChatSDK().GetUserName(Integer.parseInt(Value.userBean.getChatid())));
    }

    public interface OnTitleClick {
        public boolean onTitleClick(View v);
    }

    @SuppressLint("NewApi")
    private void requestReadExternalPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG", "READ permission IS NOT granted...");
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.d("TAG", "11111111111111");
            } else {
                // 0 是自己定义的请求coude
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                Log.d("TAG", "222222222222");
            }
        } else {
            Log.d("TAG", "READ permission is granted...");
        }

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 2);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d("TAG", "requestCode=" + requestCode + "; --->" + permissions.toString() + "; grantResult=" + grantResults.toString());
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    // request successfully, handle you transactions
                } else {
                    // permission denied
                    // request failed
                }
                return;
            }
            default:
                break;

        }
    }
}
