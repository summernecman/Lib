package com.summer.desktop.module.app.main;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.summer.desktop.bean.uibean.AppMainFragUIBean;
import com.summer.lib.base.ope.BaseUIOpe;

public class AppMainUIOpe extends BaseUIOpe<AppMainFragUIBean> {

    public AppMainUIOpe(Context context) {
        super(context, new AppMainFragUIBean(context, null));
    }

    public void initViewPager(FragmentActivity fragmentActivity) {
        getUiBean().getAppViewpager().setAdapter(new AppMainAdapter(fragmentActivity.getSupportFragmentManager()));
    }
}
