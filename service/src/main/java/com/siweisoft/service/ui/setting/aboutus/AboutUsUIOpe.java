package com.siweisoft.service.ui.setting.aboutus;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragAboutusBinding;

public class AboutUsUIOpe extends BaseUIOpe<FragAboutusBinding> {


    public AboutUsUIOpe(Context context) {
        super(context);
    }

    public void initVersion(String v) {
        bind.tvVersion.setText(v);
    }
}
