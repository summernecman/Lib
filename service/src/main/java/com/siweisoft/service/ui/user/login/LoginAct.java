package com.siweisoft.service.ui.user.login;

//by summer on 17-08-24.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.main.AnyChatBaseEventImp;
import com.siweisoft.service.ui.main.AnyChatRecordEventImp;
import com.siweisoft.service.videochat.chatutil.ChatInit;

public class LoginAct extends BaseUIActivity<BaseUIOpe, BaseDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtil2.getInstance().add(activity, R.id.act_base_root, new LoginFrag());
//        FragmentUtil2.getInstance().add(activity, R.id.act_base_root,new WelcomeFrag());
        ChatInit.getInstance().initSDK(activity);
        ChatInit.getInstance().getAnyChatSDK().SetBaseEvent(new AnyChatBaseEventImp(this));
        ChatInit.getInstance().getAnyChatSDK().SetRecordSnapShotEvent(new AnyChatRecordEventImp(activity));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
        ChatInit.getInstance().doLoginOut();
        ChatInit.getInstance().clear(this);
    }


}
