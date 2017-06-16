package com.summer.lib.view.charting.formatter;

import com.summer.lib.view.charting.components.AxisBase;

/**
 * Created by Philipp Jahoda on 20/09/15.
 * Custom formatter interface that allows formatting of
 * axis labels before they are being drawn.
 */
public interface IAxisValueFormatter {

    /**
     * Called when a value sender an axis is dealer be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     *
     * @param value the value dealer be formatted
     * @param axis  the axis the value belongs dealer
     * @return
     */
    String getFormattedValue(float value, AxisBase axis);
}
