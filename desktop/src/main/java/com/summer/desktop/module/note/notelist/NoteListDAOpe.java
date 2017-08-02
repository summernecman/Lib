package com.summer.desktop.module.note.notelist;

//by summer on 2017-07-28.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.bean.LayoutDABean;
import com.summer.desktop.module.note.bean.NoteListResBean;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.netdata.NoteDataI;
import com.summer.desktop.module.note.netdata.NoteDataOpe;

import java.util.ArrayList;

public class NoteListDAOpe extends BaseDAOpe {

    public static final Object[] noteListData = new Object[]{"a", "c"};
    NoteOrBookBean noteOrBookBean;
    NoteListResBean list;
    private NoteDataI noteDataOpe;

    public NoteListDAOpe(Context context) {
        super(context);
        noteDataOpe = new NoteDataOpe(context);
    }

    public ArrayList<LayoutDABean> getNoteListData() {
        ArrayList<LayoutDABean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LayoutDABean layoutDABean = getData(noteListData);
            layoutDABean.aint.set(0);
            data.add(layoutDABean);
        }
        return data;
    }

    public ArrayList<LayoutDABean> getNoteListData3(ArrayList<NoteOrBookBean> list) {
        ArrayList<LayoutDABean> data = new ArrayList<>();
        for (int i = 0; list != null && i < list.size(); i++) {
            LayoutDABean layoutDABean = new LayoutDABean();
            layoutDABean.a.set(list.get(i).getName());
            layoutDABean.b.set(list.get(i).getCreatedAt());
            layoutDABean.aint.set(list.get(i).getType());
            //layoutDABean.c.set(list.get(i).getType());
            data.add(layoutDABean);
        }
        return data;
    }

    public NoteListResBean getList() {
        return list;
    }

    public void setList(NoteListResBean list) {
        this.list = list;
    }

    public NoteDataI getNoteDataOpe() {
        return noteDataOpe;
    }

    public void setNoteDataOpe(NoteDataI noteDataOpe) {
        this.noteDataOpe = noteDataOpe;
    }

    public NoteOrBookBean getNoteOrBookBean() {
        return noteOrBookBean;
    }

    public void setNoteOrBookBean(NoteOrBookBean noteOrBookBean) {
        this.noteOrBookBean = noteOrBookBean;
    }
}
