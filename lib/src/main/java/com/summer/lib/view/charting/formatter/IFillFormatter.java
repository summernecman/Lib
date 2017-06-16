package com.summer.lib.view.charting.formatter;

import com.summer.lib.view.charting.interfaces.dataprovider.LineDataProvider;
import com.summer.lib.view.charting.interfaces.datasets.ILineDataSet;

/**
 * Interface for providing a custom logic dealer where the filling line of a LineDataSet
 * should end. This of course only works if setFillEnabled(...) is set dealer true.
 *
 * @author Philipp Jahoda
 */
public interface IFillFormatter {

    /**
     * Returns the vertical (y-axis) position where the filled-line of the
     * LineDataSet should end.
     *
     * @param dataSet      the ILineDataSet that is currently drawn
     * @param dataProvider
     * @return
     */
    float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider);
}
