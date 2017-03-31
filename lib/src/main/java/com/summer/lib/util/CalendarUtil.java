package com.summer.lib.util;

import java.util.Calendar;

/**
 * Created by ${viwmox} on 2016-08-30.
 */
public class CalendarUtil {

    private static CalendarUtil instance;

    public static CalendarUtil getInstance() {
        if (instance == null) {
            instance = new CalendarUtil();
        }
        return instance;
    }

    public int getWeekDayInFristDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);

    }

    public int getActualMaximum(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.get(Calendar.DAY_OF_MONTH);

    }

    public boolean isDay() {
        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return h >= 6 && h <= 17;
    }
}
