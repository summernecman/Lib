package com.summer.desktop.module.app;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.android.lib.base.adapter.AppPagerAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.summer.desktop.databinding.FragAppMainBinding;

import java.util.ArrayList;

public class AppMainUIBean extends BaseUIBean<FragAppMainBinding> {

    public AppMainUIBean(Context context) {
        super(context);
    }

    public void initViewPager(FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        viewDataBinding.appViewpager.setAdapter(new AppPagerAdapter(fragmentActivity.getSupportFragmentManager(), context, fragments));
    }
}
