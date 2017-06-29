package com.summer.desktop.module.note.noteslist;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.desktop.util.TitleUtil;

public class NoteViewPagerFrag extends BaseUIFrag<NoteViewPagerUIOpe, NotesViewPagerDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getDa().init(fragment);
        if (getOpes().getDa().notes.size() <= getOpes().getDa().position) {
            return;
        }
        TitleUtil.getInstance().add(getOpes().getDa().notes.get(getOpes().getDa().position).getName());
        getOpes().getUi().initpager(this, getOpes().getDa().notes, getOpes().getDa().position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getOpes().getDa().notes == null || getOpes().getDa().notes.size() <= getOpes().getDa().position) {
            return;
        }
        TitleUtil.getInstance().remove(getOpes().getDa().notes.get(getOpes().getDa().position).getName());
    }
}
