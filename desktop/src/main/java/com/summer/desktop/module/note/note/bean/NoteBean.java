package com.summer.desktop.module.note.note.bean;

//by summer on 2017-07-31.

import com.android.lib.bean.BaseBean;

import java.util.ArrayList;

public class NoteBean extends BaseBean {

    public static final int NOTE_TYPE_NORMAL = 0;
    public static final int NOTE_TYPE_GALLERY = 1;
    public static final int NOTE_TYPE_NETPAGER = 2;
    public int type;
    ArrayList<NoteItemBean> data;

    public ArrayList<NoteItemBean> getData() {
        return data;
    }

    public void setData(ArrayList<NoteItemBean> data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
