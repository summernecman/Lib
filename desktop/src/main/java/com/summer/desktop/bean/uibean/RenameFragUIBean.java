package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class RenameFragUIBean extends BaseUIBean {


    @BindView(R.id.txt)
    EditText txt;
    @BindView(R.id.delete)
    ImageView delete;

    public RenameFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_note_rename);
    }

    public EditText getTxt() {
        return txt;
    }

    public ImageView getDelete() {
        return delete;
    }
}
