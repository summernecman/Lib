package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.system.UUUIDUtil;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;

import butterknife.OnClick;
import butterknife.Optional;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> {

    LoginInfoBroadCast loginInfoBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initViewPager(getSupportFragmentManager(), getP().getD().getFragment());
        //FragmentUtil2.getInstance().add(activity, R.id.serviceroot,new HomeFrag());
        loginInfoBroadCast = new LoginInfoBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + ValueConstant.ACITON_GLOB_CAST);
        registerReceiver(loginInfoBroadCast, intentFilter);
    }

    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }

        if (FragmentUtil2.fragMap.get(Value.FULLSCREEN).size() == 1) {
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
        switch (v.getId()) {
            case R.id.ftv_title:

                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
        }
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

}
