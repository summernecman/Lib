package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class MenuFragUIBean extends BaseUIBean {


    @BindView(R.id.text)
    TextView text;

    public MenuFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_note_menu);
    }
}
