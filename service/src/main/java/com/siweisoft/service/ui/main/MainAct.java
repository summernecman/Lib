package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.util.FragmentUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.user.login.LoginFrag;
import com.siweisoft.service.videochat.chatutil.ChatInit;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChatInit.getInstance().initSDK(activity);
        ChatInit.getInstance().getAnyChatSDK().SetBaseEvent(new AnyChatBaseEventImp(this));
        ChatInit.getInstance().getAnyChatSDK().SetRecordSnapShotEvent(new AnyChatRecordEventImp());
        FragmentUtil.getInstance().add(activity, R.id.serviceroot, new LoginFrag());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
        ChatInit.getInstance().doLoginOut();
        ChatInit.getInstance().clear(this);
    }

    @Override
    public void onBackPressed() {
        FragmentUtil.getInstance().removeTop(activity);
    }
}
