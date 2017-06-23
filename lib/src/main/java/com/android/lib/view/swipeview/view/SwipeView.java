package com.android.lib.view.swipeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.android.lib.base.interf.view.OnAppItemClickListener;
import com.android.lib.util.LogUtil;
import com.android.lib.view.chart.linearchat.bean.databean.Value;
import com.android.lib.view.pinnedheaderexpandablelistview.expandable.ui.PinnedHeaderExpandableListView;
import com.summer.lib.R;

/**
 * Created by ${viwmox} on 2016-11-30.
 */
public class SwipeView extends LinearLayout {


    public static final int status_start = 0;
    public static final int status_update = 1;
    public static final int status_end = 2;
    public static int status = status_end;
    public static int STATUS_DOWN = 11;
    public static int STATUS_MOVE = 12;
    public static int STATUS_UP = 13;
    public static int touchtatus = STATUS_DOWN;
    public static double agree = Math.tan(Math.toRadians(60));
    Context context;
    Value[] values = new Value[]{new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0)};
    View childView;
    View finishView;
    OnAppItemClickListener onAppClickListener;

    public SwipeView(Context context) {
        super(context);
        init(context);
    }

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        childView = getChildAt(0);
        finishView = getChildAt(1);

    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (child == finishView && finishView.getHeight() != childView.getHeight()) {
            finishView.getLayoutParams().height = childView.getHeight();
            finishView.requestLayout();
            values[5].setX(-finishView.getLayoutParams().width);
            values[5].setY(values[5].getX() / 2);
        }
        if (child == childView && finishView.getHeight() != childView.getHeight()) {
            finishView.getLayoutParams().height = childView.getHeight();
            finishView.requestLayout();
            values[5].setX(-finishView.getLayoutParams().width);
            values[5].setY(values[5].getX() / 2);
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                childView.dispatchTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                childView.dispatchTouchEvent(event);
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public void createAnimatorTranslationX(final View v, final float w, final View fl) {
        LogUtil.E(w);
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(v);
        viewPropertyAnimatorCompat.setDuration(250);
        viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompat.translationX(w);
        viewPropertyAnimatorCompat.start();
        viewPropertyAnimatorCompat.setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(View view) {
                status = status_update;
                float xx = ViewCompat.getTranslationX(v);
                ViewCompat.setTranslationX(fl, xx);
            }


        });

        viewPropertyAnimatorCompat.setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                status = status_start;
            }

            @Override
            public void onAnimationEnd(View view) {
                status = status_end;
            }

            @Override
            public void onAnimationCancel(View view) {
                status = status_end;
            }
        });
    }

    public void endAnimatorTranslationX() {
//        if(status!=status_end){
//            return;
//        }
        values[4].setX(0);
        values[4].setY(0);
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(childView);
        viewPropertyAnimatorCompat.setDuration(250);
        viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompat.translationX(0);
        viewPropertyAnimatorCompat.start();
        viewPropertyAnimatorCompat.setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(View view) {
                status = status_update;
                float xx = ViewCompat.getTranslationX(childView);
                ViewCompat.setTranslationX(getFinishView(), xx);
            }


        });

        viewPropertyAnimatorCompat.setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                status = status_start;
            }

            @Override
            public void onAnimationEnd(View view) {
                status = status_end;
            }

            @Override
            public void onAnimationCancel(View view) {
                status = status_end;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchtatus = STATUS_DOWN;
                values[0].setX(event.getX());
                values[0].setY(event.getY());
                break;
            case MotionEvent.ACTION_MOVE:

                values[1].setX(event.getX());
                values[1].setY(event.getY());
                values[3].setX(values[4].getX() + (values[1].getX() - values[0].getX()) / 2);
                values[3].setY(values[4].getY() + (values[1].getY() - values[0].getY()) / 2);
                if (childView == null) {
                    break;
                }
                if (Math.abs(values[1].getY() - values[0].getY()) / Math.abs(values[1].getX() - values[0].getX()) < agree) {
                    requestDisallowInterceptTouchEvent(true);
                    if (getParent() instanceof PinnedHeaderExpandableListView) {
                        PinnedHeaderExpandableListView p = (PinnedHeaderExpandableListView) getParent();
                        for (int i = 0; i < p.getChildCount(); i++) {
                            p.getChildAt(i).setTag(R.id.position, i);
                            if (p.getChildAt(i) != this && p.getChildAt(i) instanceof SwipeView) {
                                SwipeView s = (SwipeView) p.getChildAt(i);
                                LogUtil.E(i + "--" + status + "---" + ViewCompat.getTranslationX(s));
                                s.endAnimatorTranslationX();
                            }
                        }
                    }
                    ViewCompat.setTranslationX(childView, Math.max(values[5].getX(), values[3].getX()));
                    ViewCompat.setTranslationX(finishView, Math.max(values[5].getX(), values[3].getX()));
                    //status = status_update;
                } else {
                    requestDisallowInterceptTouchEvent(false);
                }
                touchtatus = STATUS_MOVE;
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touchtatus = STATUS_UP;
                requestDisallowInterceptTouchEvent(false);
                values[2].setX(event.getX());
                values[2].setY(event.getY());
                if (values[2].getX() == values[0].getX() && values[2].getY() == values[0].getY()) {
                    if (onAppClickListener != null) {
                        if (values[2].getX() > finishView.getLeft() + ViewCompat.getTranslationX(finishView) && values[2].getX() < finishView.getRight() + ViewCompat.getTranslationX(finishView)) {
                            onAppClickListener.onAppItemClick(finishView, 1);
                        } else {
                            onAppClickListener.onAppItemClick(childView, 0);
                        }
                    }
                }
                createAnimatorTranslationX(childView, getValue(values[3].getX()), finishView);
                values[4].setX(getValue(values[3].getX()));
                values[4].setY(0);
                break;
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < values.length - 1; i++) {
            values[i].setY(0);
            values[i].setX(0);
        }
        if (childView != null) {
            ViewCompat.setTranslationX(childView, 0);
            ViewCompat.setTranslationX(finishView, 0);
        }

    }


    public float getValue(float v) {
        if (v > values[5].getY()) {
            return 0;
        } else {
            return values[5].getX();
        }
    }


    public void setOnAppClickListener(OnAppItemClickListener onAppClickListener) {
        this.onAppClickListener = onAppClickListener;
    }

    public View getChildView() {
        return childView;
    }

    public View getFinishView() {
        return finishView;
    }
}
