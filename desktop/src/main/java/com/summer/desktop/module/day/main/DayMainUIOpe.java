package com.summer.desktop.module.day.main;

//by summer on 2017-06-13.

import android.content.Context;
import android.view.View;

import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.bean.uibean.DayMainUIBean;
import com.summer.lib.base.ope.BaseUIOpe;

import java.util.ArrayList;

public class DayMainUIOpe extends BaseUIOpe<DayMainUIBean> {

    DayMainAdapter dayMainAdapter;

    public DayMainUIOpe(Context context) {
        super(context, new DayMainUIBean(context, null));
    }

    public void initList(View.OnLongClickListener listener) {
        dayMainAdapter = new DayMainAdapter(context);
        getUiBean().initRecylce(dayMainAdapter);
        dayMainAdapter.setOnLongClickListener(listener);
    }

    public void addTimes(ArrayList<TimeBean> times) {
        dayMainAdapter.setTimes(times);
        dayMainAdapter.notifyDataSetChanged();
    }
}
