package com.summer.desktop.module.day.dayview;

//by summer on 2017-06-13.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;

import java.util.Calendar;

public class MinuteDAOpe extends BaseDAOpe {


    public MinuteDAOpe(Context context) {
        super(context);
    }

    public int nowTimeToPosition(int Start) {
        return (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - Start) * 60 + Calendar.getInstance().get(Calendar.MINUTE);
    }

    public int nowH() {
        return (Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }

    public int nowM() {
        return (Calendar.getInstance().get(Calendar.MINUTE));
    }

}
