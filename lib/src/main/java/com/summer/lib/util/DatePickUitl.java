package com.summer.lib.util;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * Created by ${viwmox} on 2016-05-12.
 */
public class DatePickUitl {

    private static DatePickUitl instance;

    public static DatePickUitl getInstance() {
        if (instance == null) {
            instance = new DatePickUitl();
        }
        return instance;
    }

    public void showDatePickDialog(Context context, DatePickerDialog.OnDateSetListener listener) {
        new DatePickerDialog(context, listener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
    }
}
