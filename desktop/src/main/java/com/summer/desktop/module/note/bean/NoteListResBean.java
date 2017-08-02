package com.summer.desktop.module.note.bean;

//by summer on 2017-07-28.

import com.android.lib.network.bean.res.BaseResBean;

import java.util.ArrayList;

public class NoteListResBean extends BaseResBean {

    private ArrayList<NoteOrBookBean> data;


    public ArrayList<NoteOrBookBean> getData() {
        return data;
    }

    public void setData(ArrayList<NoteOrBookBean> data) {
        this.data = data;
    }
}
