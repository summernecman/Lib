package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.videochat.chatutil.ChatInit;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initViewPager(getSupportFragmentManager(), getP().getD().getFragment());
        //FragmentUtil2.getInstance().add(activity, R.id.serviceroot,new HomeFrag());
    }

    @Override
    public void onBackPressed() {
        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
        if (FragmentUtil2.fragMap.get(Value.getNowRoot()).size() == 0) {
            ((ServieApp) getApplication()).exit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
        ChatInit.getInstance().doLoginOut();
    }

}
