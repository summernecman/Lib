package com.summer.desktop.module.app;

//by summer on 2017-06-07.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.lib.base.fragment.BaseUIFrag;

public class AppMainFrag extends BaseUIFrag<AppMainUIBean, AppMainDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUi().initViewPager(activity, getOpes().getDa().getFragments());
    }
}
