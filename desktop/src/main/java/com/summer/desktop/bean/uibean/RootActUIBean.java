package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class RootActUIBean extends BaseUIBean {


    @BindView(R.id.r)
    LinearLayout r;

    public RootActUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.act_note_root);
    }

    public LinearLayout getR() {
        return r;
    }
}
