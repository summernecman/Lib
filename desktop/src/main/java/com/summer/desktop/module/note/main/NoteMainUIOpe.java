package com.summer.desktop.module.note.main;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseUIBean;
import com.android.lib.constant.color.ColorConstant;
import com.android.lib.util.FragmentUtil;
import com.summer.desktop.R;
import com.summer.desktop.databinding.FragNoteMainBinding;
import com.summer.desktop.util.TitleUtil;

public class NoteMainUIOpe extends BaseUIBean<FragNoteMainBinding> {


    public NoteMainUIOpe(Context context) {
        super(context);
        viewDataBinding.tvTitle.setBackgroundColor(context.getResources().getColor(ColorConstant.COLOR_STATUS));
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
