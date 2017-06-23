package com.android.lib.base.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * viewpager适配器的基类
 */
public abstract class AppBasePagerAdapter extends FragmentStatePagerAdapter {

    /**
     * 上下文
     */
    protected Context context;

    public AppBasePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

}
