package com.summer.desktop.module.note.main;

//by summer on 2017-06-06.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.bean.uibean.MainActUIBean;
import com.summer.desktop.module.home.main.IPostion;
import com.summer.desktop.module.note.noteslist.NotesListFrag;
import com.summer.desktop.util.TitleUtil;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.util.FragmentUtil;
import com.summer.lib.util.LogUtil;

import java.util.ArrayList;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class NoteMainUIOpe extends BaseUIOpe<MainActUIBean> {


    long aLong = 0;


    public NoteMainUIOpe(Context context) {
        super(context, new MainActUIBean(context, null));
    }

    public void initList(FragmentActivity activity) {
        TitleUtil.getInstance().getName().clear();
        LogUtil.E("notemainfrag");
        NotesListFrag noteListssFrag = new NotesListFrag();
        Bundle bundle = new Bundle();
        Note note = new Note(Note.NOTEBOOK, "0");
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);
        note.setObjectId("0");
        bundle.putBoolean("first", true);
        bundle.putSerializable("data", notes);
        noteListssFrag.setArguments(bundle);
        FragmentUtil.getInstance().add(activity, noteListssFrag);
    }

    public void onclick(FragmentActivity activity, IPostion iPostion) {

        if ((System.currentTimeMillis() - aLong) < 1000) {
            if (iPostion.getNowPostion() % 2 == 1) {
                Toast.makeText(getApplicationContext(), "double kill", Toast.LENGTH_SHORT).show();
                FragmentUtil.getInstance().removeTop(activity);
            }
            aLong = 0;
        }
        aLong = System.currentTimeMillis();
    }

    public void remove(FragmentActivity activity, IPostion iPostion) {
        if (iPostion.getNowPostion() % 2 == 1) {
            Toast.makeText(getApplicationContext(), "double kill", Toast.LENGTH_SHORT).show();
            FragmentUtil.getInstance().removeTop(activity);
        }
    }


    public void clear() {
        FragmentUtil.getInstance().clear();
    }


    public void setTitle() {
        String ss = "";
        for (int i = 0; i < TitleUtil.getInstance().getName().size(); i++) {
            ss += TitleUtil.getInstance().getName().get(i) + "/";
        }
        getUiBean().getToptitle().setText(ss);
    }
}
