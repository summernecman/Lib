package com.android.lib.view.hellocharts.provider;

import com.android.lib.view.hellocharts.model.LineChartData;

public interface LineChartDataProvider {

    LineChartData getLineChartData();

    void setLineChartData(LineChartData data);

}
