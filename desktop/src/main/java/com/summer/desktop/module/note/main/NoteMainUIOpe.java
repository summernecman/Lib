package com.summer.desktop.module.note.main;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.summer.desktop.R;
import com.summer.desktop.databinding.FragNoteMainBinding;
import com.summer.desktop.util.TitleUtil;
import com.summer.lib.base.ope.BaseUIBean;
import com.summer.lib.util.FragmentUtil;

public class NoteMainUIOpe extends BaseUIBean<FragNoteMainBinding> {


    public NoteMainUIOpe(Context context) {
        super(context);
    }

    public void initList(Fragment f, Fragment fragment) {
        TitleUtil.getInstance().getName().clear();
        FragmentUtil.getInstance().add(f, R.id.root_note, fragment);
    }


    public void clear() {
        FragmentUtil.getInstance().clear();
    }

    public void setTitle(String ss) {
        viewDataBinding.tvTitle.setText(ss);
    }
}
