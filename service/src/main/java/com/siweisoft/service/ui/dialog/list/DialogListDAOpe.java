package com.siweisoft.service.ui.dialog.list;

//by summer on 17-09-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;

import java.util.List;

public class DialogListDAOpe extends BaseDAOpe {

    private List list;

    public DialogListDAOpe(Context context) {
        super(context);
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
