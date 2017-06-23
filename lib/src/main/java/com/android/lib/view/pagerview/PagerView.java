package com.android.lib.view.pagerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2017-03-21.
 */

public class PagerView extends ViewGroup {

    public static final int DOWN = 0;
    public static final int MOVE = 1;
    public static final int UP = 2;
    public static final int DIS = 3;
    public static final int LEFT = 4;
    public static final int XX = 5;
    P[] values = new P[]{new P(0f, 0f), new P(0f, 0f), new P(0f, 0f), new P(0f, 0f), new P(0f, 0f), new P(0f, 0f)};
    ArrayList<RectF> rectFs = new ArrayList<>();
    int w;

    public PagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        w = getWidth() / getChildCount();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(w * i, 0, w * (i + 1), getHeight());
            rectFs.add(new RectF(getChildAt(i).getLeft(), getChildAt(i).getTop(), getChildAt(i).getRight(), getChildAt(i).getBottom()));
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                values[DOWN].x = event.getX();
                values[DOWN].y = event.getY();
                values[MOVE].x = event.getX();
                values[MOVE].y = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                values[DIS].x = event.getX() - values[MOVE].x;
                values[DIS].y = event.getY() - values[MOVE].y;

                moveAll();
                values[MOVE].x = event.getX();
                values[MOVE].y = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                anim();
                values[UP].x = event.getX();
                values[UP].y = event.getY();
                values[DIS].x = 0;
                values[DIS].y = 0;
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void move(View v, float d) {

        ViewCompat.setTranslationX(v, d);
    }

    public void moveAll() {
        for (int i = 0; i < getChildCount(); i++) {
            rectFs.get(i).left += values[DIS].x;

            int x = (int) (rectFs.get(i).left + getChildAt(i).getWidth() / 2);
            if ((Math.abs(x) % (getWidth() * 2)) <= getWidth()) {
                int l = (int) (Math.abs(x) % (getWidth() * 2)) - getChildAt(i).getWidth() / 2;
                getChildAt(i).layout(l, 0, (int) (l + w), getChildAt(i).getBottom());
            }
            if ((Math.abs(x) % (getWidth() * 2)) >= getWidth()) {

                int l = (int) ((2 * getWidth()) - (Math.abs(x) % (getWidth() * 2))) - getChildAt(i).getWidth() / 2;
                getChildAt(i).layout(l, 0, (int) (l + w), getChildAt(i).getBottom());
            }
            getChildAt(i).setScaleX((float) (0.5f + 2 * Math.abs(0.5f * Math.sin(Math.PI * ((getChildAt(i).getLeft() + w / 2f) / getWidth())))));
            getChildAt(i).setScaleY((float) (0.5f + 2 * Math.abs(0.5f * Math.sin(Math.PI * ((getChildAt(i).getLeft() + w / 2f) / getWidth())))));

        }
    }

    public void moveAll2() {
        for (int i = 0; i < getChildCount(); i++) {
            rectFs.get(i).left += values[DIS].x;

            int x = (int) (rectFs.get(i).left + getChildAt(i).getWidth() / 2);
            int l = (int) (Math.abs(x) % (getWidth())) - getChildAt(i).getWidth() / 2;
            getChildAt(i).layout(l, 0, (int) (l + w), getChildAt(i).getBottom());
            getChildAt(i).setScaleX((float) (0.5f + 2 * Math.abs(0.5f * Math.sin(Math.PI * ((getChildAt(i).getLeft() + w / 2f) / getWidth())))));
            getChildAt(i).setScaleY((float) (0.5f + 2 * Math.abs(0.5f * Math.sin(Math.PI * ((getChildAt(i).getLeft() + w / 2f) / getWidth())))));

        }
    }

    public void anim() {
        float i = (w - rectFs.get(0).left % getWidth()) > rectFs.get(0).left % getWidth() ? (w - rectFs.get(0).left % getWidth()) : rectFs.get(0).left % getWidth();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0, i);
        valueAnimator.setDuration(2000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            float f = 0;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                values[DIS].x = (float) animation.getAnimatedValue() - f;
                moveAll();
                f = (float) animation.getAnimatedValue();

            }
        });
        valueAnimator.start();
    }

    public static class P {
        public float x;

        public float y;

        public P(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
