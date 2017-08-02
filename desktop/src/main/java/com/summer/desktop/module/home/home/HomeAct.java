package com.summer.desktop.module.home.home;

//by summer on 2017-07-28.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.util.FragmentUtil;

public class HomeAct extends BaseUIActivity<HomeUIOpe, HomeDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOpes().U().initViewPager(getSupportFragmentManager(), activity, getOpes().D().getFragments());
    }

    @Override
    public void onBackPressed() {
        FragmentUtil.getInstance().removeTop(activity);
    }
}
