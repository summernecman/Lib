package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.databinding.FragSettingBinding;

public class SettingUIOpe extends BaseUIOpe<FragSettingBinding> {


    public SettingUIOpe(Context context) {
        super(context);
    }

    public void setTitle() {
        TitleBean titleBean = new TitleBean("返回", "设置", "");
        bind.test.setTitle2(titleBean);
    }
}
