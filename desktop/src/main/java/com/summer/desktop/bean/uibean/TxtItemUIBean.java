package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.view.textview.YRTextView;

import butterknife.BindView;

public class TxtItemUIBean extends BaseUIBean {

    @BindView(R.id.txt)
    YRTextView txt;

    public TxtItemUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_note_txt);
    }

    public YRTextView getTxt() {
        return txt;
    }
}
