package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.system.UUUIDUtil;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.videochat.chatutil.ChatInit;

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
        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
        if (FragmentUtil2.fragMap.get(Value.getNowRoot()).size() == 0) {
            ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
            ChatInit.getInstance().doLoginOut();
            ((ServieApp) getApplication()).exit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(loginInfoBroadCast);
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
