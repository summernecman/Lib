package com.summer.desktop.module.note.main;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.desktop.module.home.main.IPostion;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NoteMainFrag extends BaseUIFrag<NoteMainUIOpe, NoteMainDAOpe> {
    @Override
    public BaseOpes<NoteMainUIOpe, NoteMainDAOpe> createOpes() {
        return new BaseOpes<>(new NoteMainUIOpe(activity), new NoteMainDAOpe(activity));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUiOpe().initList(activity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getOpes().getUiOpe().clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMesage(MessageEvent event) {
        getOpes().getUiOpe().onclick(event.position, getActivity(), (IPostion) getActivity());
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
