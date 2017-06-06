package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hitomi.cmlibrary.CircleMenu;
import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class BoomFragUIBean extends BaseUIBean {


    @BindView(R.id.circle_menu)
    CircleMenu circleMenu;
    @BindView(R.id.boomfrag)
    RelativeLayout boomfrag;

    public BoomFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_boom);
    }

    public RelativeLayout getBoomfrag() {
        return boomfrag;
    }

    public CircleMenu getCircleMenu() {
        return circleMenu;
    }
}
