package com.summer.desktop.module.note.main;

//by summer on 2017-06-06.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.module.note.noteslist.NoteViewPagerFrag;

import java.util.ArrayList;

public class NoteMainDAOpe extends BaseDAOpe {


    public NoteMainDAOpe(Context context) {
        super(context);
    }

    public Fragment getFragment() {
        NoteViewPagerFrag noteListssFrag = new NoteViewPagerFrag();
        Bundle bundle = new Bundle();
        Note note = new Note(Note.NOTEBOOK, "0");
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);
        note.setObjectId("0");
        bundle.putBoolean("first", true);
        bundle.putSerializable("data", notes);
        noteListssFrag.setArguments(bundle);
        return noteListssFrag;
    }
}
