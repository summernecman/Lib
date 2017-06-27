package com.android.lib.view.line.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.lib.R;


/**
 * Created by ${viwmox} on 2016-06-02.
 */
public class VerticalDashView extends View {

    int dashColor;

    float dashGap;

    float dashWidth;

    Paint paint = new Paint();

    public VerticalDashView(Context context) {
        super(context);
    }

    public VerticalDashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DashView);
        dashColor = typedArray.getResourceId(R.styleable.DashView_dashcolor, R.color.color_base_bottommenu);
        dashGap = typedArray.getDimension(R.styleable.DashView_dashgap, 3f);
        dashWidth = typedArray.getDimension(R.styleable.DashView_hashwidth, 3f);
        paint.setColor(dashColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(getHeight());
        for (int i = 0; i < getHeight() / (dashGap + dashWidth); i++) {
            canvas.drawLine(0, 0 + (dashGap + dashWidth) * i, 0, dashWidth + (dashGap + dashWidth) * i, paint);
        }
    }
}
