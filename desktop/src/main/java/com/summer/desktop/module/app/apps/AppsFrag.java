package com.summer.desktop.module.app.apps;

//by summer on 2017-06-07.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.desktop.bean.dabean.Msg;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.util.IntentUtil;
import com.summer.lib.util.NullUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deal(Msg msg) {
        if (!msg.type.equals(AppsAdapter.class.getName())) {
            return;
        }
        AppDBBean appDBBean = (AppDBBean) msg.msg;
        if (appDBBean.getAppName().equals("刷新") && NullUtil.isStrEmpty(appDBBean.getPackageName())) {
            getOpes().getDaOpe().clearData();
            getOpes().getDaOpe().getApps(new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    getOpes().getUiOpe().initList((ArrayList<AppDBBean>) o);
                }
            });
            return;
        }
        IntentUtil.getInstance().IntentTo(activity, appDBBean.getPackageName());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getOpes().getDaOpe().saveSort();
    }


}
