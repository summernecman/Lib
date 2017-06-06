package com.summer.desktop.bean.dabean;

//by summer on 2017-05-25.

import com.summer.lib.bean.databean.BaseDABean;

import java.util.ArrayList;

public class GsonNoteBean extends BaseDABean {

    ArrayList<NoteDetail> data = new ArrayList<>();

    public ArrayList<NoteDetail> getData() {
        return data;
    }

    public void setData(ArrayList<NoteDetail> data) {
        this.data = data;
    }
}
