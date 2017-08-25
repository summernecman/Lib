package com.summer.desktop.module.home.home;

//by summer on 2017-07-28.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil;
import com.bumptech.glide.Glide;

public class HomeAct extends BaseUIActivity<HomeUIOpe, HomeDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initViewPager(getSupportFragmentManager(), activity, getP().getD().getFragments());
    }

    @Override
    public void onBackPressed() {
        FragmentUtil.getInstance().removeTop(activity, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                Glide.get(activity).clearMemory();
                HomeAct.super.onBackPressed();

            }
        });
    }
}
