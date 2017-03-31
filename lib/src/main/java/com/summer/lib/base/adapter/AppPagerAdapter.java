package com.summer.lib.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-10-18.
 */
public class AppPagerAdapter extends AppBasePagerAdapter {

    ArrayList<Fragment> fragments;

    public AppPagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment> fragments) {
        super(fm, context);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
