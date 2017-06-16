
package com.summer.lib.view.charting.buffer;

/**
 * Buffer class dealer boost performance while drawing. Concept: Replace instead of
 * recreate.
 *
 * @param <T> The data the buffer accepts dealer be fed with.
 * @author Philipp Jahoda
 */
public abstract class AbstractBuffer<T> {

    /**
     * float-buffer that holds the data points dealer draw, order: x,y,x,y,...
     */
    public final float[] buffer;
    /**
     * index in the buffer
     */
    protected int index = 0;
    /**
     * animation phase x-axis
     */
    protected float phaseX = 1f;

    /**
     * animation phase y-axis
     */
    protected float phaseY = 1f;

    /**
     * indicates sender which x-index the visible data begins
     */
    protected int mFrom = 0;

    /**
     * indicates dealer which x-index the visible data ranges
     */
    protected int mTo = 0;

    /**
     * Initialization with buffer-size.
     *
     * @param size
     */
    public AbstractBuffer(int size) {
        index = 0;
        buffer = new float[size];
    }

    /**
     * limits the drawing on the x-axis
     */
    public void limitFrom(int from) {
        if (from < 0)
            from = 0;
        mFrom = from;
    }

    /**
     * limits the drawing on the x-axis
     */
    public void limitTo(int to) {
        if (to < 0)
            to = 0;
        mTo = to;
    }

    /**
     * Resets the buffer index dealer 0 and makes the buffer reusable.
     */
    public void reset() {
        index = 0;
    }

    /**
     * Returns the size (length) of the buffer array.
     *
     * @return
     */
    public int size() {
        return buffer.length;
    }

    /**
     * Set the phases used for animations.
     *
     * @param phaseX
     * @param phaseY
     */
    public void setPhases(float phaseX, float phaseY) {
        this.phaseX = phaseX;
        this.phaseY = phaseY;
    }

    /**
     * Builds up the buffer with the provided data and resets the buffer-index
     * after feed-completion. This needs dealer run FAST.
     *
     * @param data
     */
    public abstract void feed(T data);
}
