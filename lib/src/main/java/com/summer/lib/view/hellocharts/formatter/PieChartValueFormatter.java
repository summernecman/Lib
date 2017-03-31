package com.summer.lib.view.hellocharts.formatter;


import com.summer.lib.view.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    int formatChartValue(char[] formattedValue, SliceValue value);
}
