package com.summer.desktop.bean.uibean;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.lib.base.interf.view.OnPageChangeAdapterI;
import com.summer.lib.base.listener.DoubleClickListener;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.view.bottommenu.BottomItemView;

import butterknife.BindView;

public class MainHomeUIBean extends BaseUIBean {


    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;
    @BindView(R.id.bottom_viewpager)
    BottomItemView bottomViewpager;

    public MainHomeUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.act_home_main);
    }

    public void initHomeViewPager(PagerAdapter adapter) {
        getHomeViewpager().setAdapter(adapter);
        getHomeViewpager().setOffscreenPageLimit(3);
        getHomeViewpager().addOnPageChangeListener(new OnPageChangeAdapterI() {
            @Override
            public void onPageSelected(int position) {
                int c = getBottomViewpager().getCurrentItem();
                int n = (c / 3) * 3;
                if (((c + 2) % 3) != position) {
                    getBottomViewpager().setCurrentItem(n + position + 1);
                }
            }
        });
    }

    public void initBottom(PagerAdapter adapter, DoubleClickListener doubleClickListener, View.OnClickListener listener) {
        getBottomViewpager().setAdapter(adapter);
        getBottomViewpager().setOffscreenPageLimit(7);
        getBottomViewpager().setCurrentItem(1000);
        ((BottomItemView.MenuAdapter) getBottomViewpager().getAdapter()).setOnClickListener(listener);
        getBottomViewpager().addOnPageChangeListener(new OnPageChangeAdapterI() {
            @Override
            public void onPageSelected(int position) {
                if ((position + 2) % 3 != getHomeViewpager().getCurrentItem()) {
                    getHomeViewpager().setCurrentItem((position + 2) % 3);
                }

            }
        });
        ((BottomItemView.MenuAdapter) getBottomViewpager().getAdapter()).setDoubleClickListener(doubleClickListener);
    }


    public ViewPager getHomeViewpager() {
        return homeViewpager;
    }

    public BottomItemView getBottomViewpager() {
        return bottomViewpager;
    }
}
