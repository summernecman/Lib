package com.summer.lib.view.charting.interfaces.dataprovider;

import com.summer.lib.view.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
