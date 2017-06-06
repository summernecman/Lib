package com.summer.desktop.module.main;

//by summer on 2017-06-06.

import android.os.Bundle;

import com.summer.lib.base.activity.BaseUIActivity;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseUIActivity<MainUIOpe, MainDAOpe> {
    @Override
    public BaseOpes<MainUIOpe, MainDAOpe> createOpes() {
        return new BaseOpes<>(new MainUIOpe(activity), new MainDAOpe(activity));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOpes().getUiOpe().initBottom();
        getOpes().getUiOpe().initList(activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getOpes().getUiOpe().clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMesage(MessageEvent event) {
        getOpes().getUiOpe().onclick(event.position, this);
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
}
