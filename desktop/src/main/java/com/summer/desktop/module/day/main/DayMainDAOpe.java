package com.summer.desktop.module.day.main;

//by summer on 2017-06-13.

import android.content.Context;

import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.module.note.notelist.NoteListDAOpe;
import com.summer.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;
import java.util.HashMap;

public class DayMainDAOpe extends BaseDAOpe {

    MinuteDAOpe minuteDAOpe;

    NoteListDAOpe noteListDAOpe;

    HashMap<String, String> data = new HashMap<>();

    ArrayList<TimeBean> times = new ArrayList<>();

    public DayMainDAOpe(Context context) {
        super(context);
        minuteDAOpe = new MinuteDAOpe(context);
        noteListDAOpe = new NoteListDAOpe(context);
    }

    public MinuteDAOpe getMinuteDAOpe() {
        return minuteDAOpe;
    }

    public ArrayList<TimeBean> getTimes() {
        return times;
    }

    public NoteListDAOpe getNoteListDAOpe() {
        return noteListDAOpe;
    }

    public HashMap<String, String> getData() {
        return data;
    }
}
