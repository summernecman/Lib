package com.summer.desktop.module.app.main;

//by summer on 2017-06-07.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.ope.BaseOpes;

public class AppMainFrag extends BaseUIFrag<AppMainUIOpe, AppMainDAOpe> {
    @Override
    public BaseOpes<AppMainUIOpe, AppMainDAOpe> createOpes() {
        return new BaseOpes<>(new AppMainUIOpe(activity), new AppMainDAOpe(activity));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUiOpe().initViewPager(activity);
    }
}
