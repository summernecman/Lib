package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.videochat.chatutil.ChatInit;

public class LoginDAOpe extends BaseDAOpe {

    UserInfo userInfo = new UserInfo();

    UserI userI;


    public LoginDAOpe(Context context) {
        super(context);
        userI = new UserNetOpe(context);
    }

    public void login(UserInfo userInfo) {
        userI.login(userInfo, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if ((Boolean) o) {
                    ChatInit.getInstance().doLogin(VideoValue.URL.IP, VideoValue.URL.PROT, getUserInfo().name.get());
                    Value.userInfo = getUserInfo();
                }
            }
        });
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


}
