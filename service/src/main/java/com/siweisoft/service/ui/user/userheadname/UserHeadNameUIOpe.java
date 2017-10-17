package com.siweisoft.service.ui.user.userheadname;

//by summer on 17-08-30.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragUserheadnameBinding;
import com.siweisoft.service.ui.Constant.Value;

public class UserHeadNameUIOpe extends BaseUIOpe<FragUserheadnameBinding> {

    public UserHeadNameUIOpe(Context context) {
        super(context);
    }

    public void initInfo() {
        bind.setUserheadname(Value.getUserInfo());
        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + Value.getUserInfo().getHeadurl()).into(bind.ivHead);
        LogUtil.E(Value.getUserInfo().getHeadurl());
    }


}
