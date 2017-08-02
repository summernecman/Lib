package com.summer.desktop.module.rename;

//by summer on 2017-06-06.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;

public class RenameDAOpe extends BaseDAOpe {

    Name name = new Name();

    public RenameDAOpe(Context context) {
        super(context);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
