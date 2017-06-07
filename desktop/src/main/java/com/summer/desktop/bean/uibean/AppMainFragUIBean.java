package com.summer.desktop.bean.uibean;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class AppMainFragUIBean extends BaseUIBean {


    @BindView(R.id.app_viewpager)
    ViewPager appViewpager;

    public AppMainFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_app_main);
    }

    public ViewPager getAppViewpager() {
        return appViewpager;
    }
}
