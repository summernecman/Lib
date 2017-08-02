package com.summer.desktop.module.note.notemain;

//by summer on 2017-07-28.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.notelist.NoteListFrag;

import java.util.ArrayList;

public class NoteMainDAOpe extends BaseDAOpe {

    NoteOrBookBean noteOrBookBean;


    public NoteMainDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        NoteListFrag noteListFrag = new NoteListFrag();
        noteListFrag.setArguments(new Bundle());
        noteListFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, noteOrBookBean);
        fragments.add(noteListFrag);
        return fragments;
    }

    public NoteOrBookBean getNoteOrBookBean() {
        return noteOrBookBean;
    }

    public void setNoteOrBookBean(NoteOrBookBean noteOrBookBean) {
        this.noteOrBookBean = noteOrBookBean;
    }
}
