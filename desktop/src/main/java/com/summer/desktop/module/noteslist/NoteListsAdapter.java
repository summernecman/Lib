package com.summer.desktop.module.noteslist;

//by summer on 2017-05-23.

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.module.notedetail.NoteDetailFrag;
import com.summer.desktop.module.notelist.NoteListFrag;

import java.util.ArrayList;

public class NoteListsAdapter extends FragmentStatePagerAdapter {

    ArrayList<Note> notes;

    public NoteListsAdapter(FragmentManager fm, ArrayList<Note> notes) {
        super(fm);
        this.notes = notes;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (notes.get(position).getType()) {
            case Note.NOTE:
                fragment = new NoteDetailFrag();
                bundle.putSerializable("data", notes.get(position));
                fragment.setArguments(bundle);
                break;
            case Note.NOTEBOOK:
                fragment = new NoteListFrag();
                bundle.putSerializable("data", notes.get(position));
                fragment.setArguments(bundle);
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return notes == null ? 0 : notes.size();
    }
}
