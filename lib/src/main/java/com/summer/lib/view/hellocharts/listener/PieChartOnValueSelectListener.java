package com.summer.lib.view.hellocharts.listener;


import com.summer.lib.view.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int arcIndex, SliceValue value);

}
