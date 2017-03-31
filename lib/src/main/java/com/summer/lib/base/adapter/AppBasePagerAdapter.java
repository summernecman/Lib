package com.summer.lib.base.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ${viwmox} on 2016-04-27.
 */
public abstract class AppBasePagerAdapter extends FragmentStatePagerAdapter {

    protected Context context;


    public AppBasePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

}
