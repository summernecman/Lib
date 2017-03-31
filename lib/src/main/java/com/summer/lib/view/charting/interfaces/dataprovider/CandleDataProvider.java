package com.summer.lib.view.charting.interfaces.dataprovider;

import com.summer.lib.view.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
