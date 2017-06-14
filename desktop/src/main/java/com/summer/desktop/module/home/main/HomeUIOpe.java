package com.summer.desktop.module.home.main;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.summer.desktop.bean.uibean.MainHomeUIBean;
import com.summer.lib.base.listener.DoubleClickListener;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.util.LogUtil;
import com.summer.lib.view.bottommenu.BottomItemView;

public class HomeUIOpe extends BaseUIOpe<MainHomeUIBean> {


    public HomeUIOpe(Context context) {
        super(context, new MainHomeUIBean(context, null));
    }


    public void initViewPager(FragmentActivity activity) {
        getUiBean().getHomeViewpager().setAdapter(new homeMainAdapter(activity.getSupportFragmentManager()));
        getUiBean().getHomeViewpager().setOffscreenPageLimit(3);
        getUiBean().getHomeViewpager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int c = getUiBean().getBottomViewpager().getCurrentItem();
                int n = (c / 3) * 3;
                if (((c + 2) % 3) != position) {
                    getUiBean().getBottomViewpager().setCurrentItem(n + position + 1);
                    LogUtil.E("2" + position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initBottom(DoubleClickListener doubleClickListener, View.OnClickListener listener) {
        getUiBean().getBottomViewpager().setAdapter(new BottomItemView.MenuAdapter(context));
        getUiBean().getBottomViewpager().setOffscreenPageLimit(7);
        getUiBean().getBottomViewpager().setCurrentItem(1000);
        ((BottomItemView.MenuAdapter) getUiBean().getBottomViewpager().getAdapter()).setOnClickListener(listener);
        getUiBean().getBottomViewpager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if ((position + 2) % 3 != getUiBean().getHomeViewpager().getCurrentItem()) {
                    getUiBean().getHomeViewpager().setCurrentItem((position + 2) % 3);
                    LogUtil.E("1" + position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ((BottomItemView.MenuAdapter) getUiBean().getBottomViewpager().getAdapter()).setDoubleClickListener(doubleClickListener);
    }

    public void selectPager(int position) {
        getUiBean().getHomeViewpager().setCurrentItem(position % 2);
        getUiBean().getBottomViewpager().setCurrentItem(position - 2);
    }


}
