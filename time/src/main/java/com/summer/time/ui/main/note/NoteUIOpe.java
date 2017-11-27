package com.summer.time.ui.main.note;

//by summer on 2017-11-24.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.summer.time.databinding.FragNoteBinding;

public class NoteUIOpe extends BaseUIOpe<FragNoteBinding> {

    public NoteUIOpe(Context context) {
        super(context);
    }

    public void switchEnable() {
        if (bind.etTxt.isEnabled()) {
            bind.etTxt.setEnabled(false);
        } else {
            bind.etTxt.setEnabled(true);
        }
    }
}
