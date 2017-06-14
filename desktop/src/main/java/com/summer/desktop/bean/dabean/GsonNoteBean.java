package com.summer.desktop.bean.dabean;

//by summer on 2017-05-25.

import com.summer.lib.bean.databean.BaseDABean;

import java.util.ArrayList;

public class GsonNoteBean extends BaseDABean {

    public static final String TYPE_NOTE = "type_note";
    public static final String TYPE_GALLERY = "type_gallery";
    ArrayList<NoteDetail> data = new ArrayList<>();
    private String type = TYPE_NOTE;

    public ArrayList<NoteDetail> getData() {
        return data;
    }

    public void setData(ArrayList<NoteDetail> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
