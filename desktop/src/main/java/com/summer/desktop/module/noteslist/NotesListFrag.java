package com.summer.desktop.module.noteslist;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.desktop.util.TitleUtil;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.ope.BaseOpes;

public class NotesListFrag extends BaseUIFrag<NotesListUIOpe, NotesListDAOpe> {
    @Override
    public BaseOpes<NotesListUIOpe, NotesListDAOpe> createOpes() {
        return new BaseOpes<>(new NotesListUIOpe(activity), new NotesListDAOpe(activity));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUiOpe().initpager(fragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TitleUtil.getInstance().getName().remove(TitleUtil.getInstance().getName().get(TitleUtil.getInstance().getName().size() - 1));
    }
}
