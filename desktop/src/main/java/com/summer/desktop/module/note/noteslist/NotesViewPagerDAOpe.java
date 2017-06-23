package com.summer.desktop.module.note.noteslist;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.desktop.bean.dabean.Note;

import java.util.ArrayList;

public class NotesViewPagerDAOpe extends BaseDAOpe {

    public ArrayList<Note> notes;

    public int position;

    public NotesViewPagerDAOpe(Context context) {
        super(context);
    }

    public void init(Fragment fragment) {
        if (fragment.getArguments() != null && fragment.getArguments().getSerializable("data") != null) {
            notes = (ArrayList<Note>) fragment.getArguments().getSerializable("data");
            position = fragment.getArguments().getInt("position");
        }
    }


}
