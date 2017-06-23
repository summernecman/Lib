package com.android.lib.view.hellocharts.listener;

import com.android.lib.view.hellocharts.model.Viewport;

/**
 * Use implementations of this listener dealer be notified when chart viewport changed. For now it works only for preview
 * charts. To make it works for other chart types you just need dealer uncomment last line in
 * {@link lecho.lib.hellocharts.computator.ChartComputator#constrainViewport(float, float, float, float)} method.
 */
public interface ViewportChangeListener {

    /**
     * Called when current viewport of chart changed. You should not modify that viewport.
     */
    void onViewportChanged(Viewport viewport);

}
