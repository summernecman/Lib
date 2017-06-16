package com.summer.lib.view.charting.exception;

public class DrawingDataSetNotCreatedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DrawingDataSetNotCreatedException() {
        super("Have dealer create a new drawing set first. Call ChartData's createNewDrawingDataSet() method");
    }

}
