package com.summer.desktop.module.home.main;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.summer.desktop.bean.uibean.MainHomeUIBean;
import com.summer.desktop.module.app.apps.AppsFrag;
import com.summer.desktop.module.day.main.DayFrag;
import com.summer.desktop.module.note.main.NoteMainFrag;
import com.summer.lib.base.adapter.AppPagerAdapter;
import com.summer.lib.base.listener.DoubleClickListener;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.util.FragmentUtil;
import com.summer.lib.view.bottommenu.BottomItemView;

import java.util.ArrayList;

public class HomeUIOpe extends BaseUIOpe<MainHomeUIBean> {


    public HomeUIOpe(Context context) {
        super(context, new MainHomeUIBean(context, null));
    }


    public void init(FragmentActivity activity, DoubleClickListener doubleClickListener, View.OnClickListener listener) {
        FragmentUtil.getInstance().initClear(activity);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DayFrag());
        fragments.add(new AppsFrag());
        fragments.add(new NoteMainFrag());
        getUiBean().initHomeViewPager(new AppPagerAdapter(activity.getSupportFragmentManager(), activity, fragments));
        getUiBean().initBottom(new BottomItemView.MenuAdapter(context), doubleClickListener, listener);
        ((BottomItemView.MenuAdapter) getUiBean().getBottomViewpager().getAdapter()).setDoubleClickListener(doubleClickListener);
    }

}
