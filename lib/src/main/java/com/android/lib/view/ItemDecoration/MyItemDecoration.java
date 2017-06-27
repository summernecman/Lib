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
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    Paint paint = new Paint();
    private Context context;
    private int w = 0;

    public MyItemDecoration(Context context, int w) {
        super();
        this.context = context;
        paint.setColor(context.getResources().getColor(R.color.color_base_graybg));
        this.w = w;
    }

    public MyItemDecoration(Context context, int w, int color) {
        super();
        this.context = context;
        paint.setColor(color);
        this.w = w;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        final int childSize = parent.getChildCount();

//        for(int i = 0 ; i < childSize -1; i ++){
//            final int left = parent.getPaddingLeft() ;
//            final int right = parent.getMeasuredWidth() - parent.getPaddingRight() ;
//            final View child = parent.getChildAt( i ) ;
//            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
//            final int top = child.getBottom() + layoutParams.bottomMargin ;
//            final int bottom = top+w;
//            c.drawRect(left,top,right,bottom,paint);
//        }
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            c.drawRect(child.getLeft() - child.getPaddingLeft(), child.getTop() - child.getPaddingTop(), child.getRight() + child.getPaddingRight(), child.getTop() - child.getPaddingTop() + w, paint);
            c.drawRect(child.getLeft() - child.getPaddingLeft(), child.getBottom() + child.getPaddingBottom(), child.getRight() + child.getPaddingRight(), child.getBottom() + child.getPaddingBottom() + w, paint);
            c.drawRect(child.getLeft() - child.getPaddingLeft(), child.getTop() - child.getPaddingTop(), child.getLeft() - child.getPaddingLeft() + w, child.getBottom() + child.getPaddingBottom(), paint);
            c.drawRect(child.getRight() + child.getPaddingRight(), child.getTop() - child.getPaddingTop(), child.getRight() + w + child.getPaddingRight(), child.getBottom() + child.getPaddingBottom(), paint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(w, w, w, w);
    }

}
