package com.android.lib.util.file;

import android.content.Context;

import com.android.lib.R;
import com.android.lib.view.pickerview.TimePickerDialog;
import com.android.lib.view.pickerview.data.Type;
import com.android.lib.view.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;

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

    public void showTimePickDialogHMHM(final Context context, final android.support.v4.app.FragmentManager fragmentManager, final TimeDate listener) {
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        ArrayList<Long> data = new ArrayList<Long>();
                        data.add(millseconds);
                        showTimePickDialogHMHM2(context, listener, fragmentManager, data);
                    }
                })
                .setCancelStringId("Cancel")
                .setSureStringId("Sure")
                .setTitleStringId("TimePicker")
                .setYearText("Year")
                .setMonthText("Month")
                .setDayText("Day")
                .setHourText("Hour")
                .setMinuteText("Minute")
                .setCyclic(false)
                //.setMinMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(context.getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(fragmentManager, "timepick");
    }

    public void showTimePickDialogHMHM2(Context context, final TimeDate timeDate, android.support.v4.app.FragmentManager fragmentManager, final ArrayList<Long> data) {
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        data.add(millseconds);
                        timeDate.getTimeData(data);
                    }
                })
                .setCancelStringId("Cancel")
                .setSureStringId("Sure")
                .setTitleStringId("TimePicker")
                .setYearText("Year")
                .setMonthText("Month")
                .setDayText("Day")
                .setHourText("Hour")
                .setMinuteText("Minute")
                .setCyclic(false)
                //.setMinMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(context.getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(fragmentManager, "timepick3");
    }

    public interface TimeDate {
        public void getTimeData(ArrayList<Long> time);
    }

}
