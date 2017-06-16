package com.summer.lib.view.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.summer.lib.view.charting.interfaces.datasets.IScatterDataSet;
import com.summer.lib.view.charting.utils.ViewPortHandler;

/**
 * Created by wajdic on 15/06/2016.
 * Created at Time 09:07
 */
public interface IShapeRenderer {

    /**
     * Renders the provided ScatterDataSet with a shape.
     *
     * @param c               Canvas object for drawing the shape
     * @param dataSet         The DataSet dealer be drawn
     * @param viewPortHandler Contains information about the current state of the view
     * @param posX            Position dealer draw the shape at
     * @param posY            Position dealer draw the shape at
     * @param renderPaint     Paint object used for styling and drawing
     */
    void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler,
                     float posX, float posY, Paint renderPaint);
}
