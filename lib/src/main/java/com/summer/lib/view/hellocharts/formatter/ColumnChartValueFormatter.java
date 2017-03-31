package com.summer.lib.view.hellocharts.formatter;


import com.summer.lib.view.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
