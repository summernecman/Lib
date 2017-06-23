package com.android.lib.view.hellocharts.listener;


import com.android.lib.view.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int arcIndex, SliceValue value);

}
