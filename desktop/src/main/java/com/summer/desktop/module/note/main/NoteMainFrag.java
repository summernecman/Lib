package com.summer.desktop.module.note.main;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.view.bottommenu.MessageEvent;
import com.summer.desktop.util.TitleUtil;

public class NoteMainFrag extends BaseUIFrag<NoteMainUIOpe, NoteMainDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUi().initList(fragment, getOpes().getDa().getFragment());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getOpes().getUi().clear();
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        getOpes().getUi().setTitle(TitleUtil.getInstance().getStr());
    }
}
