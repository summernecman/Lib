package com.android.lib.view.ItemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.R;

/**
 * Created by ${viwmox} on 2016-08-30.
 */
public class MyItemDecoration2 extends RecyclerView.ItemDecoration {

    Paint paint = new Paint();
    private Context context;
    private int w = 0;

    public MyItemDecoration2(Context context, int w) {
        super();
        this.context = context;
        paint.setColor(context.getResources().getColor(R.color.color_base_graybg));
        this.w = w;
    }

    public MyItemDecoration2(Context context, int w, int color) {
        super();
        this.context = context;
        paint.setColor(color);
        this.w = w;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        final int childSize = parent.getChildCount();

        for (int i = 0; i < childSize; i++) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + w;
            //c.drawRect(child.getLeft() - w, child.getTop(), child.getLeft(), child.getBottom() + w, paint);
            if (i > 0) {
                c.drawRect(child.getLeft(), child.getTop() - w, child.getRight(), child.getTop(), paint);
            }
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, w, 0, 0);
    }

}
