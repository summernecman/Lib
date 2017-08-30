package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.databinding.FragLoginBinding;
import com.siweisoft.service.ui.Constant.Value;

public class LoginUIOpe extends BaseUIOpe<FragLoginBinding> {


    public LoginUIOpe(Context context) {
        super(context);
    }

    public void initImage(String url) {
        LogUtil.E(Value.userBean.getHeadurl());
        GlideApp.with(context).asBitmap().centerCrop().load("content://com.android.providers.media.documents/document/image%3A115755").into(bind.ivTop);
        //GlideApp.with(context).asBitmap().load(url).centerCrop().into(bind.ivTop);
        // bind.ivTop
    }
}
