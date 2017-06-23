package com.android.lib.view.hellocharts.formatter;


import com.android.lib.view.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    int formatChartValue(char[] formattedValue, PointValue value);
}
