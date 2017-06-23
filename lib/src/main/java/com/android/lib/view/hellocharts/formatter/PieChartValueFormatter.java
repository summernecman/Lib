package com.android.lib.view.hellocharts.formatter;


import com.android.lib.view.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    int formatChartValue(char[] formattedValue, SliceValue value);
}
