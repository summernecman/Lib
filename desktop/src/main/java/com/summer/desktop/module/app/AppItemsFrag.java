package com.summer.desktop.module.app;

//by summer on 2017-06-07.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.util.IntentUtil;
import com.summer.lib.util.NullUtil;
import com.summer.lib.view.bottommenu.MessageEvent;

import java.util.ArrayList;

public class AppItemsFrag extends BaseUIFrag<AppItemsUIBean, AppsDAOpe> {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getDa().getApps(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getOpes().getUi().initList((ArrayList<AppDBBean>) o);
            }
        });
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        if (!AppItemsUIBean.class.getName().equals(event.sender)) {
            return;
        }
        AppDBBean appDBBean = (AppDBBean) event.data;
        if (appDBBean.getAppName().equals("刷新") && NullUtil.isStrEmpty(appDBBean.getPackageName())) {
            getOpes().getDa().clearData();
            getOpes().getDa().getApps(new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    getOpes().getUi().initList((ArrayList<AppDBBean>) o);
                }
            });
            return;
        }
        IntentUtil.getInstance().IntentTo(activity, appDBBean.getPackageName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getOpes().getDa().saveSort();
    }

}
