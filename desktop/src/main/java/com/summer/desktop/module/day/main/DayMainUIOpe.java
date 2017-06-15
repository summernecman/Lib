package com.summer.desktop.module.day.main;

//by summer on 2017-06-13.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.bean.uibean.DayMainUIBean;
import com.summer.desktop.module.day.dayview.DayView;
import com.summer.desktop.module.home.main.HomeActivity;
import com.summer.desktop.module.note.noteslist.NotesListFrag;
import com.summer.desktop.util.FragList;
import com.summer.lib.base.ope.BaseUIOpe;

import java.util.ArrayList;

public class DayMainUIOpe extends BaseUIOpe<DayMainUIBean> {

    DayMainAdapter dayMainAdapter;


    public DayMainUIOpe(Context context) {
        super(context, new DayMainUIBean(context, null));
    }

    public void initList(DayView.OnlongClickWithHM listener) {
//        dayMainAdapter = new DayMainAdapter(context);
//        getUiBean().initRecylce(dayMainAdapter);
//        dayMainAdapter.setOnLongClickListener(listener);
        getUiBean().getDayview().setLongClickListener(listener);

    }

    public void addTimes(ArrayList<TimeBean> times) {
//        dayMainAdapter.setTimes(times);
//        dayMainAdapter.notifyDataSetChanged();
        getUiBean().getDayview().setTimes(times);
    }

    public void deleteTime(int h, int m) {
        getUiBean().getDayview().delete(h, m);
    }

    public void goToNote(FragmentActivity activity, String parentid) {

        NotesListFrag noteListssFrag = new NotesListFrag();
        Bundle bundle = new Bundle();
        Note note = new Note(Note.NOTEBOOK, parentid);
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);
        note.setObjectId(parentid);
        bundle.putBoolean("first", true);
        bundle.putSerializable("data", notes);
        noteListssFrag.setArguments(bundle);
        FragList.getInstance().add(activity, noteListssFrag);
        if (activity instanceof HomeActivity) {
            HomeActivity homeActivity = (HomeActivity) activity;
            homeActivity.getOpes().getUiOpe().getUiBean().getHomeViewpager().setCurrentItem(2);
        }
    }
}
