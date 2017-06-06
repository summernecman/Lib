package com.summer.desktop.module.main;

//by summer on 2017-06-06.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.bean.uibean.MainActUIBean;
import com.summer.desktop.module.noteslist.NotesListFrag;
import com.summer.desktop.util.FragList;
import com.summer.desktop.util.TitleUtil;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.view.bottommenu.BottomItemView;

import java.util.ArrayList;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class MainUIOpe extends BaseUIOpe<MainActUIBean> {


    long aLong = 0;


    public MainUIOpe(Context context) {
        super(context, new MainActUIBean(context, null));
    }

    public void initBottom() {
        getUiBean().getViewpager().setAdapter(new BottomItemView.MenuAdapter(context));
    }

    public void initList(FragmentActivity activity) {
        NotesListFrag noteListssFrag = new NotesListFrag();
        Bundle bundle = new Bundle();
        Note note = new Note(Note.NOTEBOOK, "0");
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);
        note.setObjectId("0");
        bundle.putBoolean("first", true);
        bundle.putSerializable("data", notes);
        noteListssFrag.setArguments(bundle);
        FragList.getInstance().add(activity, noteListssFrag);
    }

    public void onclick(int position, FragmentActivity activity) {
        if ((position - 2) >= 0) {
            if ((position - 2) == getUiBean().getViewpager().getCurrentItem()) {
                if ((System.currentTimeMillis() - aLong) < 1000) {
                    Toast.makeText(getApplicationContext(), "double kill", Toast.LENGTH_SHORT).show();
                    FragList.getInstance().removeTop(activity);
                    aLong = 0;
                }
                aLong = System.currentTimeMillis();
            }
            getUiBean().getViewpager().setCurrentItem((position - 2));
        }
        setTitle();
    }


    public void clear() {
        FragList.getInstance().clear();
    }


    public void setTitle() {
        String ss = "";
        for (int i = 0; i < TitleUtil.getInstance().getName().size(); i++) {
            ss += TitleUtil.getInstance().getName().get(i) + "/";
        }
        getUiBean().getToptitle().setText(ss);
    }
}
