package com.summer.desktop.bean.uibean;

//by summer on 2017-06-13.

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.desktop.module.day.dayview.DayView;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class DayMainUIBean extends BaseUIBean {


    @BindView(R.id.dayview)
    DayView dayview;

    public DayMainUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_day_main);
    }


    public void initRecylce(RecyclerView.Adapter adapter) {

    }

    public DayView getDayview() {
        return dayview;
    }
}
