package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nightonke.boommenu.BoomMenuButton;
import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class TxtFragUIBean extends BaseUIBean {


    @BindView(R.id.txtroot)
    LinearLayout txtroot;
    @BindView(R.id.bmb)
    BoomMenuButton bmb;

    public TxtFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_note_txt);
    }

    public LinearLayout getTxtroot() {
        return txtroot;
    }

    public BoomMenuButton getBmb() {
        return bmb;
    }
}
