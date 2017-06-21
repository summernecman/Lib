package com.summer.desktop.module.home.main;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.summer.desktop.databinding.ActHomeMainBinding;
import com.summer.desktop.module.app.AppItemsFrag;
import com.summer.desktop.module.day.DayFrag;
import com.summer.desktop.module.note.main.NoteMainFrag;
import com.summer.lib.base.adapter.AppPagerAdapter;
import com.summer.lib.base.interf.view.OnPageChangeAdapterI;
import com.summer.lib.base.listener.DoubleClickListener;
import com.summer.lib.base.ope.BaseUIBean;
import com.summer.lib.view.bottommenu.BottomItemView;

import java.util.ArrayList;

public class HomeUIBean extends BaseUIBean<ActHomeMainBinding> {


    public HomeUIBean(Context context) {
        super(context);
    }

    public void init(FragmentActivity activity, DoubleClickListener doubleClickListener, View.OnClickListener listener) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DayFrag());
        fragments.add(new AppItemsFrag());
        fragments.add(new NoteMainFrag());
        initHomeViewPager(new AppPagerAdapter(activity.getSupportFragmentManager(), activity, fragments));
        initBottom(new BottomItemView.MenuAdapter(activity), doubleClickListener, listener);
        ((BottomItemView.MenuAdapter) viewDataBinding.bottomViewpager.getAdapter()).setDoubleClickListener(doubleClickListener);
    }

    public void initHomeViewPager(PagerAdapter adapter) {
        viewDataBinding.homeViewpager.setAdapter(adapter);
        viewDataBinding.homeViewpager.setOffscreenPageLimit(3);
        viewDataBinding.homeViewpager.addOnPageChangeListener(new OnPageChangeAdapterI() {
            @Override
            public void onPageSelected(int position) {
                int c = getViewDataBinding().bottomViewpager.getCurrentItem();
                int n = (c / 3) * 3;
                if (((c + 2) % 3) != position) {
                    getViewDataBinding().bottomViewpager.setCurrentItem(n + position + 1);
                }
            }
        });
    }

    public void initBottom(PagerAdapter adapter, DoubleClickListener doubleClickListener, View.OnClickListener listener) {
        getViewDataBinding().bottomViewpager.setAdapter(adapter);
        getViewDataBinding().bottomViewpager.setOffscreenPageLimit(7);
        getViewDataBinding().bottomViewpager.setCurrentItem(1000);
        ((BottomItemView.MenuAdapter) getViewDataBinding().bottomViewpager.getAdapter()).setOnClickListener(listener);
        getViewDataBinding().bottomViewpager.addOnPageChangeListener(new OnPageChangeAdapterI() {
            @Override
            public void onPageSelected(int position) {
                if ((position + 2) % 3 != getViewDataBinding().homeViewpager.getCurrentItem()) {
                    getViewDataBinding().homeViewpager.setCurrentItem((position + 2) % 3);
                }

            }
        });
        ((BottomItemView.MenuAdapter) getViewDataBinding().bottomViewpager.getAdapter()).setDoubleClickListener(doubleClickListener);
    }

}
