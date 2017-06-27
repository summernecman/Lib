package com.summer.desktop.bean.dabean;

import com.android.lib.bean.databean.BaseDABean;

/**
 * Created by summer on 2017/5/26 0:50.
 */

public class Note extends BaseDABean {

    public static final String NOTE = "note";

    public static final String NOTEBOOK = "notebook";

    private String parentId;

    private String type;

    private String name;

    private String data;

    public Note() {
    }

    public Note(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
