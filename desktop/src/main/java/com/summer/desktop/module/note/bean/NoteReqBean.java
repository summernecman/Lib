package com.summer.desktop.module.note.bean;

//by summer on 2017-07-28.

import com.android.lib.network.bean.req.BaseReqBean;

public class NoteReqBean extends BaseReqBean {

    private Integer objectId;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }
}
