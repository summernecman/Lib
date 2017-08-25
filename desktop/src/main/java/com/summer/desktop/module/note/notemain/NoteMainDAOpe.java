package com.summer.desktop.module.note.notemain;

//by summer on 2017-07-28.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.note.NoteFrag;
import com.summer.desktop.module.note.notelist.NoteListFrag;

import java.util.ArrayList;

public class NoteMainDAOpe extends BaseDAOpe {


    ArrayList<NoteOrBookBean> noteOrBooks = new ArrayList<>();

    int position;


    public NoteMainDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < noteOrBooks.size(); i++) {
            switch (noteOrBooks.get(i).getType()) {
                case NoteOrBookBean.TYPE_NOTE:
                    NoteFrag noteFrag = new NoteFrag();
                    noteFrag.setArguments(new Bundle());
                    noteFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, noteOrBooks);
                    noteFrag.getArguments().putInt(ValueConstant.DATA_POSITION, i);
                    fragments.add(noteFrag);
                    break;
                case NoteOrBookBean.TYPE_NOTEBOOK:
                    NoteListFrag noteListFrag = new NoteListFrag();
                    noteListFrag.setArguments(new Bundle());
                    noteListFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, noteOrBooks);
                    noteListFrag.getArguments().putInt(ValueConstant.DATA_POSITION, i);
                    fragments.add(noteListFrag);
                    break;
            }
        }
        return fragments;
    }

    public ArrayList<NoteOrBookBean> getNoteOrBooks() {
        return noteOrBooks;
    }

    public void setNoteOrBooks(ArrayList<NoteOrBookBean> noteOrBooks) {
        this.noteOrBooks = noteOrBooks;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
