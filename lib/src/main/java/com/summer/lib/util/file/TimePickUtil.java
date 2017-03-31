package com.summer.lib.util.file;

import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * Created by ${viwmox} on 2016-10-14.
 */
public class TimePickUtil {
    private static TimePickUtil instance;

    public static TimePickUtil getInstance() {
        if (instance == null) {
            instance = new TimePickUtil();
        }
        return instance;
    }

    public void showTimePickDialog(Context context, TimePickerDialog.OnTimeSetListener listener) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, listener, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }
}
