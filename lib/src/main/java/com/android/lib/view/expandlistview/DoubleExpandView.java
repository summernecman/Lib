package com.android.lib.view.expandlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ExpandableListView;


/**
 * Created by ${viwmox} on 2016-11-09.
 */
public class DoubleExpandView extends ExpandableListView {

    public DoubleExpandView(Context context) {
        super(context);
    }

    public DoubleExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.clipRect(0, 0, getWidth(), 100);
        canvas.drawColor(Color.RED);

    }
}
