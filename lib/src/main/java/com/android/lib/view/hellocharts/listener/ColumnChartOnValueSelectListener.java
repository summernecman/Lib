package com.android.lib.view.hellocharts.listener;


import com.android.lib.view.hellocharts.model.SubcolumnValue;

public interface ColumnChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

}
