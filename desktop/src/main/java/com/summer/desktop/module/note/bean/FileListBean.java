package com.summer.desktop.module.note.bean;

//by summer on 17-08-21.

import com.android.lib.network.bean.res.BaseResBean;

import java.util.ArrayList;

public class FileListBean extends BaseResBean {

    private ArrayList<String> data;

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
