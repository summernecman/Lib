package com.siweisoft.scales.module.main;

//by summer on 17-08-23.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.ScreenUtil;
import com.siweisoft.scales.databinding.ActMainBinding;

public class MainUIOpe extends BaseUIOpe<ActMainBinding> {


    public MainUIOpe(Context context) {
        super(context);
        bind.incloud.leftDrawer.getLayoutParams().width = (int) (ScreenUtil.w / 2);
        bind.incloud.leftDrawer.requestLayout();

    }
}
