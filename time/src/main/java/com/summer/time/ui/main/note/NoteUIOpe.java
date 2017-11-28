package com.summer.time.ui.main.note;

//by summer on 2017-11-24.

import android.content.Context;
import android.view.View;

import com.android.lib.base.ope.BaseUIOpe;
import com.summer.time.databinding.FragNoteBinding;
import com.summer.time.ui.main.thingview.ThingBean;

public class NoteUIOpe extends BaseUIOpe<FragNoteBinding> {

    public NoteUIOpe(Context context) {
        super(context);
    }

    public void switchEnable() {
        if (bind.etTxt.isEnabled()) {
            bind.etTitle.setVisibility(View.GONE);
            bind.etTitle.setEnabled(false);
            bind.etTxt.setEnabled(false);
        } else {
            bind.etTitle.setVisibility(View.VISIBLE);
            bind.etTitle.setEnabled(true);
            bind.etTxt.setEnabled(true);
        }
    }

    public void initTxt(ThingBean thingBean) {
        bind.etTxt.setText(thingBean.getThing());
        bind.etTitle.setText(thingBean.getTitle());
        bind.etTxt.setEnabled(false);
        bind.etTitle.setEnabled(false);
        bind.etTitle.setVisibility(View.GONE);
    }
}
