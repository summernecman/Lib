package com.siweisoft.service.ui.home;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragHomeBinding;

import java.util.ArrayList;

public class HomeUIOpe extends BaseUIOpe<FragHomeBinding> {

    public HomeUIOpe(Context context) {
        super(context);
    }

    public void initViewPager(FragmentManager fm, final ArrayList<Fragment> fragments) {

        bind.vpVp.setAdapter(new AppBasePagerAdapter(fm, context) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        bind.vpVp.addOnPageChangeListener(new BaseOnPagerChangeListener() {
            @Override
            public void onPageSelected(int position) {
                bind.homebottomview.select(position);
            }
        });
        bind.vpVp.setCurrentItem(1);
        bind.homebottomview.setOnAppItemSelectListener(new OnAppItemSelectListener() {
            @Override
            public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
                bind.vpVp.setCurrentItem(position);
            }
        });
    }
}
