package com.android.lib.view.hellocharts.listener;


import com.android.lib.view.hellocharts.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int bubbleIndex, BubbleValue value);

}
