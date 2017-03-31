package com.summer.lib.view.hellocharts.formatter;


import com.summer.lib.view.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    int formatChartValue(char[] formattedValue, BubbleValue value);
}
