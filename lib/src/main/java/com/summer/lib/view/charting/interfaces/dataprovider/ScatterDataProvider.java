package com.summer.lib.view.charting.interfaces.dataprovider;

import com.summer.lib.view.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
