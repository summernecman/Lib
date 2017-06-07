package com.summer.desktop.module.home.main;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.summer.desktop.bean.uibean.MainHomeUIBean;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.view.bottommenu.BottomItemView;

public class HomeUIOpe extends BaseUIOpe<MainHomeUIBean> {


    public HomeUIOpe(Context context) {
        super(context, new MainHomeUIBean(context, null));
    }

    public void initViewPager(FragmentActivity activity) {
        getUiBean().getHomeViewpager().setAdapter(new homeMainAdapter(activity.getSupportFragmentManager()));
        getUiBean().getHomeViewpager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getUiBean().getBottomViewpager().setCurrentItem(10 + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initBottom() {
        getUiBean().getBottomViewpager().setAdapter(new BottomItemView.MenuAdapter(context));
    }

    public void selectPager(int position) {
        getUiBean().getHomeViewpager().setCurrentItem(position % 2);
        getUiBean().getBottomViewpager().setCurrentItem(position - 2);
    }


}
