package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.ActMainBinding;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding> {
    public MainUIOpe(Context context) {
        super(context);
    }

    public void initViewPager(FragmentManager fm, final ArrayList<Fragment> fragments) {
        bind.vpVp.setOffscreenPageLimit(3);
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
                Value.setPostion(position);
            }
        });
        bind.vpVp.setCurrentItem(1);
        bind.homebottomview.setOnAppItemSelectListener(new OnAppItemSelectListener() {
            @Override
            public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
                bind.vpVp.setCurrentItem(position);
                Value.setPostion(position);
            }
        });
    }
}
