package com.summer.desktop.module.home.home;

//by summer on 2017-07-28.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.notemain.NoteMainFrag;

import java.util.ArrayList;

public class HomeDAOpe extends BaseDAOpe {


    public HomeDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        NoteMainFrag noteMainFrag = new NoteMainFrag();
        noteMainFrag.setArguments(new Bundle());
        ArrayList<NoteOrBookBean> noteOrBooks = new ArrayList<>();
        NoteOrBookBean noteOrBookBean = new NoteOrBookBean();
        noteOrBookBean.setParentId(0);
        noteOrBookBean.setType(NoteOrBookBean.TYPE_NOTEBOOK);
        noteOrBooks.add(noteOrBookBean);
        noteMainFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, noteOrBooks);
        noteMainFrag.getArguments().putInt(ValueConstant.DATA_POSITION, 0);
        fragments.add(noteMainFrag);
        return fragments;
    }
}
