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
import android.util.Log;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.exception.exception.CrashHander;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.UUUIDUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.siweisoft.service.R;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.netdb.crash.CrashBean;
import com.siweisoft.service.netdb.crash.CrashI;
import com.siweisoft.service.netdb.crash.CrashOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.recept.ReceiptFrag;

import butterknife.OnClick;
import butterknife.Optional;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> implements OnFinishListener {

    LoginInfoBroadCast loginInfoBroadCast;

    CrashI crashI;

    OnTitleClick onTitleClick;

    CallReceiver callReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initViewPager(getSupportFragmentManager(), getP().getD().getFragment());
        loginInfoBroadCast = new LoginInfoBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + ValueConstant.ACITON_GLOB_CAST);
        //registerReceiver(loginInfoBroadCast, intentFilter);
        CrashHander.getInstance().init(this, this);

        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
        callReceiver = new CallReceiver();
        registerReceiver(callReceiver, callFilter);

        EMClient.getInstance().callManager().addCallStateChangeListener(new VideoChatListener(activity));
        EMClient.getInstance().chatManager().addMessageListener(new EMMsgListener());
        EMClient.getInstance().addConnectionListener(new ChatConnectListener(activity));

    }

    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }


        if (FragmentUtil2.fragMap.get(Value.FULLSCREEN) != null
                && FragmentUtil2.fragMap.get(Value.FULLSCREEN).size() >= 1
                && FragmentUtil2.fragMap.get(Value.FULLSCREEN).get(FragmentUtil2.fragMap.get(Value.FULLSCREEN).size() - 1).getClass().getName().equals(ReceiptFrag.class.getName())) {
            FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
            try {
                EMClient.getInstance().callManager().rejectCall();
            } catch (EMNoActiveCallException e) {
                e.printStackTrace();
            }
            FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);

            return;
        }


        if (FragmentUtil2.fragMap.get(Value.FULLSCREEN) != null && FragmentUtil2.fragMap.get(Value.FULLSCREEN).size() == 1) {
            FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
            return;
        }

        if (FragmentUtil2.fragMap.get(Value.getNowRoot()).size() == 1) {
            FragmentUtil2.getInstance().clear();
            //ToastUtil.getInstance().showShort(activity, "请从设置里退出");
            this.finish();
        } else {
            FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(loginInfoBroadCast);
        unregisterReceiver(callReceiver);
        if (Value.room == null) {
            return;
        }
        EMClient.getInstance().chatroomManager().leaveChatRoom(Value.room.getId());
    }


    @Optional
    @OnClick({R.id.ftv_back, R.id.ftv_title, R.id.ftv_right, R.id.ftv_right2})
    public void onClickEventListener(View v) {
        if (onTitleClick != null) {
            boolean b = onTitleClick.onTitleClick(v);
            if (b) {
                return;
            }
        }
        switch (v.getId()) {
            case R.id.ftv_right:
                break;
            case R.id.ftv_title:
                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
            case R.id.ftv_right2:
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
                EMClient.getInstance().logout(true);
                if (Value.room != null) {
                    EMClient.getInstance().chatroomManager().leaveChatRoom(Value.room.getId());
                }
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                ((ServieApp) activity.getApplication()).exit();
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
