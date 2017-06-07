package com.summer.desktop.bean.uibean;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.view.bottommenu.BottomItemView;

import butterknife.BindView;

public class MainHomeUIBean extends BaseUIBean {


    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;
    @BindView(R.id.bottom_viewpager)
    BottomItemView bottomViewpager;

    public MainHomeUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.act_home_main);
    }

    public ViewPager getHomeViewpager() {
        return homeViewpager;
    }

    public BottomItemView getBottomViewpager() {
        return bottomViewpager;
    }
}
