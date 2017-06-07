package com.summer.desktop.module.app.apps;

//by summer on 2017-06-07.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseOpes;

import java.util.ArrayList;

public class AppsFrag extends BaseUIFrag<AppsUIOpe, AppsDAOpe> {

    @Override
    public BaseOpes<AppsUIOpe, AppsDAOpe> createOpes() {
        return new BaseOpes<>(new AppsUIOpe(activity), new AppsDAOpe(activity));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getDaOpe().getApps(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getOpes().getUiOpe().initList((ArrayList<AppDBBean>) o);
            }
        });
    }
}
