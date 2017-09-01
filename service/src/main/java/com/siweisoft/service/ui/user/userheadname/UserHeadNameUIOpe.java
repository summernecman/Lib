package com.siweisoft.service.ui.user.userheadname;

//by summer on 17-08-30.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.databinding.FragUserheadnameBinding;
import com.siweisoft.service.ui.Constant.Value;

public class UserHeadNameUIOpe extends BaseUIOpe<FragUserheadnameBinding> {

    public UserHeadNameUIOpe(Context context) {
        super(context);
    }

    public void initInfo() {
        bind.setUserheadname(Value.userBean);
        GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/" + Value.userBean.getHeadurl()).into(bind.ivHead);
        LogUtil.E(Value.userBean.getHeadurl());
    }

    public void initTitle() {
        bind.test.setTitle2(new TitleBean("返回", "头像和名称", ""));
    }

}
