package com.summer.desktop.module.app.main;

//by summer on 2017-06-07.

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.summer.desktop.module.app.apps.AppsFrag;

public class AppMainAdapter extends FragmentStatePagerAdapter {


    public AppMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new AppsFrag();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
