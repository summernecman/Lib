package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.databinding.ActMainBinding;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding> {
    public MainUIOpe(Context context) {
        super(context);
        //StatusBarUtil.getInstance().setStatusBarColorResId((Activity) context, R.color.color_base_nurse);
    }


    public void initTitle() {
        ArrayList<Fragment> fragments = FragmentUtil2.getInstance().getFragMap().get(Value.getNowRoot());
        if (fragments != null && fragments.size() > 0 && fragments.get(fragments.size() - 1) instanceof BaseServerFrag) {
            BaseServerFrag baseUIFrag = (BaseServerFrag) fragments.get(fragments.size() - 1);
            if (baseUIFrag != null) {
                bind.tophead.setTitle2(baseUIFrag.getTitleBean());
//                bind.tophead.ftvBack.setVisibility(View.VISIBLE);
            }
        }
    }

    public void initViewPager(FragmentManager fm, final ArrayList<Fragment> fragments) {
        bind.vpVp.setOffscreenPageLimit(3);
        bind.vpVp.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
//        bind.vpVp.setAdapter(new AppBasePagerAdapter(fm, context) {
//            @Override
//            public Fragment getItem(int position) {
//                return fragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//        });
        bind.vpVp.addOnPageChangeListener(new BaseOnPagerChangeListener() {
            @Override
            public void onPageSelected(int position) {
                bind.homebottomview.select(position);
                Value.setPostion(position);
                initTitle();
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
