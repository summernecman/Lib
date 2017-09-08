package com.siweisoft.service.ui.chat.videochat;

//by summer on 2017-07-04.

import android.content.Context;
import android.view.View;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragVideochatBinding;
import com.siweisoft.service.netdb.user.UserBean;

public class VideoChatUIOpe extends BaseUIOpe<FragVideochatBinding> {
    public VideoChatUIOpe(Context context) {
        super(context);
    }

    public void initIcon(UserBean info) {
        if (info.getUsertype() == 0) {
            bind.btnSwitchvideo.setVisibility(View.GONE);
        }
        bind.btnCameraControl.setTag(R.id.data, true);
        bind.btnSpeakControl.setTag(R.id.data, true);
        bind.btnCameraControl.setTag(R.id.data, true);
        bind.btnSpeakControlRemove.setTag(R.id.data, true);
    }
}
