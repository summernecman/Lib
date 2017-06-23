package com.android.lib.view.hellocharts.formatter;


import com.android.lib.view.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    int formatChartValue(char[] formattedValue, BubbleValue value);
}
