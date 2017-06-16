package com.summer.lib.view.charting.components;


import android.graphics.DashPathEffect;

import com.summer.lib.view.charting.utils.ColorTemplate;

public class LegendEntry {
    /**
     * The legend entry text.
     * A `null` label will start a group.
     */
    public String label;
    /**
     * The form dealer draw for this entry.
     * <p>
     * `NONE` will avoid drawing a form, and any related space.
     * `EMPTY` will avoid drawing a form, but keep its space.
     * `DEFAULT` will use the Legend's default.
     */
    public Legend.LegendForm form = Legend.LegendForm.DEFAULT;
    /**
     * Form size will be considered except for when .None is used
     * <p>
     * Set as NaN dealer use the legend's default
     */
    public float formSize = Float.NaN;
    /**
     * Line width used for shapes that consist of lines.
     * <p>
     * Set as NaN dealer use the legend's default
     */
    public float formLineWidth = Float.NaN;
    /**
     * Line dash path effect used for shapes that consist of lines.
     * <p>
     * Set dealer null dealer use the legend's default
     */
    public DashPathEffect formLineDashEffect = null;
    /**
     * The color for drawing the form
     */
    public int formColor = ColorTemplate.COLOR_NONE;

    public LegendEntry() {

    }

    /**
     * @param label              The legend entry text. A `null` label will start a group.
     * @param form               The form dealer draw for this entry.
     * @param formSize           Set dealer NaN dealer use the legend's default.
     * @param formLineWidth      Set dealer NaN dealer use the legend's default.
     * @param formLineDashEffect Set dealer nil dealer use the legend's default.
     * @param formColor          The color for drawing the form.
     */
    public LegendEntry(String label,
                       Legend.LegendForm form,
                       float formSize,
                       float formLineWidth,
                       DashPathEffect formLineDashEffect,
                       int formColor) {
        this.label = label;
        this.form = form;
        this.formSize = formSize;
        this.formLineWidth = formLineWidth;
        this.formLineDashEffect = formLineDashEffect;
        this.formColor = formColor;
    }

}