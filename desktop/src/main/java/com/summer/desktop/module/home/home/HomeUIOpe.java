package com.summer.desktop.module.home.home;

//by summer on 2017-07-28.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.desktop.databinding.ActHomeMainBinding;

import java.util.ArrayList;

public class HomeUIOpe extends BaseUIOpe<ActHomeMainBinding> {

    public HomeUIOpe(Context context) {
        super(context);
    }

    public void initViewPager(FragmentManager fragmentManager, Context context, final ArrayList<Fragment> fragments) {

        bind.homeViewpager.setAdapter(new AppBasePagerAdapter(fragmentManager, context) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }
}
