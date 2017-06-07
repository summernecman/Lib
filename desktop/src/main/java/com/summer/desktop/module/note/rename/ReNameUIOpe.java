package com.summer.desktop.module.note.rename;

//by summer on 2017-06-06.

import android.content.Context;

import com.summer.desktop.bean.uibean.RenameFragUIBean;
import com.summer.lib.base.ope.BaseUIOpe;

public class ReNameUIOpe extends BaseUIOpe<RenameFragUIBean> {

    public ReNameUIOpe(Context context) {
        super(context, new RenameFragUIBean(context, null));
    }
}
