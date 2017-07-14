package com.siweisoft.service.ui.chat.videochat;

//by summer on 2017-07-04.

import android.content.Context;
import android.view.View;

import com.android.lib.base.ope.BaseUIBean;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragVideochatBinding;
import com.siweisoft.service.ui.user.login.UserInfo;

public class VideoChatUIBean extends BaseUIBean<FragVideochatBinding> {
    public VideoChatUIBean(Context context) {
        super(context);
    }

    public void initIcon(UserInfo info) {
        if (info.type.get()) {
            viewDataBinding.btnSwitchvideo.setVisibility(View.GONE);
        }
        viewDataBinding.btnCameraControl.setTag(R.id.data, true);
        viewDataBinding.btnSpeakControl.setTag(R.id.data, true);
        viewDataBinding.btnCameraControl.setTag(R.id.data, true);
        viewDataBinding.btnSpeakControlRemove.setTag(R.id.data, true);
    }
}
