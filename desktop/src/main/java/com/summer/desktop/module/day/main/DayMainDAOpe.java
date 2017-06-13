package com.summer.desktop.module.day.main;

//by summer on 2017-06-13.

import android.content.Context;

import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class DayMainDAOpe extends BaseDAOpe {

    MinuteDAOpe minuteDAOpe;

    ArrayList<TimeBean> times = new ArrayList<>();

    public DayMainDAOpe(Context context) {
        super(context);
        minuteDAOpe = new MinuteDAOpe(context);
    }

    public MinuteDAOpe getMinuteDAOpe() {
        return minuteDAOpe;
    }

    public ArrayList<TimeBean> getTimes() {
        return times;
    }
}
