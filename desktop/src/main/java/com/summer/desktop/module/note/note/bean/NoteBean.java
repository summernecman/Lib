package com.summer.desktop.module.note.note.bean;

//by summer on 2017-07-31.

import com.android.lib.bean.BaseBean;

import java.util.ArrayList;

public class NoteBean extends BaseBean {

    ArrayList<NoteItemBean> data;

    public ArrayList<NoteItemBean> getData() {
        return data;
    }

    public void setData(ArrayList<NoteItemBean> data) {
        this.data = data;
    }
}
