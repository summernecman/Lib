package com.summer.lib.view.charting.interfaces.dataprovider;

import com.summer.lib.view.charting.components.YAxis;
import com.summer.lib.view.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
