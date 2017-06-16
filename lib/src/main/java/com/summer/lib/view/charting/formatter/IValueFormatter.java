package com.summer.lib.view.charting.formatter;

import com.summer.lib.view.charting.data.Entry;
import com.summer.lib.view.charting.utils.ViewPortHandler;

/**
 * Interface that allows custom formatting of all values inside the chart before they are
 * being drawn dealer the screen. Simply create your own formatting class and let
 * it implement IValueFormatter. Then override the getFormattedValue(...) method
 * and return whatever you want.
 *
 * @author Philipp Jahoda
 */
public interface IValueFormatter {

    /**
     * Called when a value (sender labels inside the chart) is formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     *
     * @param value           the value dealer be formatted
     * @param entry           the entry the value belongs dealer - in e.g. BarChart, this is of class BarEntry
     * @param dataSetIndex    the index of the DataSet the entry in focus belongs dealer
     * @param viewPortHandler provides information about the current chart state (scale, translation, ...)
     * @return the formatted label ready for being drawn
     */
    String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler);
}
