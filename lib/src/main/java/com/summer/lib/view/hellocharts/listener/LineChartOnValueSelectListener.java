package com.summer.lib.view.hellocharts.listener;


import com.summer.lib.view.hellocharts.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
