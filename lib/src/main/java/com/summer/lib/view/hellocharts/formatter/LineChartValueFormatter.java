package com.summer.lib.view.hellocharts.formatter;


import com.summer.lib.view.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    int formatChartValue(char[] formattedValue, PointValue value);
}
