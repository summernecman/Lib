package com.summer.desktop.module.day.daymain;

//by summer on 2017-06-13.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.GsonUtil;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.module.day.dayview.MinuteDAOpe;
import com.summer.desktop.module.note.notelist.NoteListDAOpe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DayMainDAOpe extends BaseDAOpe {

    DayDBOpe dayDBOpe;

    MinuteDAOpe minuteDAOpe;

    NoteListDAOpe noteListDAOpe;

    HashMap<String, String> data = new HashMap<>();

    ArrayList<TimeBean> times = new ArrayList<>();

    ArrayList<Note> notes;

    public DayMainDAOpe(Context context) {
        super(context);
        minuteDAOpe = new MinuteDAOpe(context);
        noteListDAOpe = new NoteListDAOpe(context);
        dayDBOpe = new DayDBOpe(context);
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

    public DayDBOpe getDayDBOpe() {
        return dayDBOpe;
    }

    public ArrayList<TimeBean> initTimeBean(ArrayList<TimeBean> t) {
        ArrayList<TimeBean> timeBeen = (ArrayList<TimeBean>) getDayDBOpe().getList();
        getTimes().clear();
        getTimes().addAll(t);
        getTimes().addAll(timeBeen);
        return getTimes();
    }

    public void getTodayNotes(final OnFinishListener listener) {
        noteListDAOpe.getTodayNotes("0",
                new String[]{
                        Calendar.getInstance().get(Calendar.YEAR) + "年",
                        (Calendar.getInstance().get(Calendar.MONTH) + 1) + "月",
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "日"}, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        Object[] objects = (Object[]) o;
                        ArrayList<Note> notes = (ArrayList<Note>) objects[1];
                        if (notes == null) {
                            notes = new ArrayList<Note>();
                        }
                        ArrayList<TimeBean> times = new ArrayList<TimeBean>();
                        for (int i = 0; i < notes.size(); i++) {
                            GsonNoteBean gsonNoteBean = GsonUtil.getInstance().fromJson(notes.get(i).getData(), GsonNoteBean.class);
                            if (gsonNoteBean.getType().equals(GsonNoteBean.TYPE_NOTE_DAY)) {
                                TimeBean timeBean = GsonUtil.getInstance().fromJson(gsonNoteBean.getTimeDetail(), TimeBean.class);
                                timeBean.text = notes.get(i).getName();
                                times.add(timeBean);
                            }
                        }
                        Object[] t = new Object[]{objects[0], times, notes};
                        listener.onFinish(t);
                    }
                });
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void delete(String str, OnFinishListener onFinishListener) {
        for (int i = 0; notes != null && i < notes.size(); i++) {
            GsonNoteBean gsonNoteBean = GsonUtil.getInstance().fromJson(notes.get(i).getData(), GsonNoteBean.class);
            if (gsonNoteBean.getType().equals(GsonNoteBean.TYPE_NOTE_DAY)) {
                TimeBean timeBean = GsonUtil.getInstance().fromJson(gsonNoteBean.getTimeDetail(), TimeBean.class);
                if (timeBean.toString().equals(str)) {
                    noteListDAOpe.deleteNote(notes.get(i).getObjectId(), onFinishListener);
                }
            }
        }

    }
}
