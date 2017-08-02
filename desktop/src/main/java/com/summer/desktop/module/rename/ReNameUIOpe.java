package com.summer.desktop.module.rename;

//by summer on 2017-06-06.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.system.ClipUitl;
import com.summer.desktop.databinding.FragNoteRenameBinding;

public class ReNameUIOpe extends BaseUIOpe<FragNoteRenameBinding> {

    public ReNameUIOpe(Context context) {
        super(context);
        FillClipTxtTo();
    }

    public void FillClipTxtTo() {
        bind.txt.setText(ClipUitl.getInsance().getClipTxt(context));
    }
}
