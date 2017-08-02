package com.summer.desktop.module.note.note.bean;

//by summer on 2017-07-31.

import com.android.lib.bean.BaseBean;

public class NoteItemBean extends BaseBean {

    public static final int TYPE_TXT = 0;

    public static final int TYPE_IMAGE = 1;

    public static final int TYPE_LINK = 2;

    public static final int TYPE_FILE = 3;

    private int type;

    private String data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
