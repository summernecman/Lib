package com.android.lib.view.hellocharts.formatter;


import com.android.lib.view.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
