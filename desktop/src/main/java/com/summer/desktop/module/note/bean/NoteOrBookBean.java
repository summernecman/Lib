package com.summer.desktop.module.note.bean;

import com.android.lib.bean.BaseBean;
import com.summer.desktop.module.note.note.bean.NoteBean;

/**
 * Created by SWSD on 2017-07-28.
 */
public class NoteOrBookBean extends BaseBean {

    public static final int TYPE_NOTE = 1;
    public static final int TYPE_NOTEBOOK = 0;
    private Integer objectId;
    private Integer parentId;
    private int type;
    private String name;
    private NoteBean data;
    private String createdAt;
    private String updatedAt;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NoteBean getData() {
        return data;
    }

    public void setData(NoteBean data) {
        this.data = data;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
