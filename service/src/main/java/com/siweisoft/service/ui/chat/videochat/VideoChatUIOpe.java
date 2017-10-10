package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.content.Context;
import android.view.View;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragVideochatBinding;
import com.siweisoft.service.netdb.user.UserBean;

public class VideoChatUIOpe extends BaseUIOpe<FragVideochatBinding> {


    public VideoChatUIOpe(Context context) {
        super(context);
    }

    public void setCallInfo(UserBean userBean) {
        bind.tvCallinfo.setVisibility(View.VISIBLE);
        bind.tvCallinfo.setText("正在向 " + userBean.getPhone() + "(" + userBean.getName() + ") 发起视频通话");
    }

    public void hideCallInfo() {
        bind.tvCallinfo.setVisibility(View.GONE);
    }

}
