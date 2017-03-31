package com.summer.lib.view.hellocharts.listener;


import com.summer.lib.view.hellocharts.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int bubbleIndex, BubbleValue value);

}
