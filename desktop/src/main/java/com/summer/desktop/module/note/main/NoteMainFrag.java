package com.summer.desktop.module.note.main;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.view.bottommenu.MessageEvent;

public class NoteMainFrag extends BaseUIFrag<NoteMainUIOpe, NoteMainDAOpe> {

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

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        getOpes().getUiOpe().setTitle();
    }
}
