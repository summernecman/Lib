package com.android.lib.view.base;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.android.lib.util.LogUtil;
import com.android.lib.view.pagerview.PagerView;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2017-03-22.
 */

public class BottomFinishView extends RelativeLayout {

    public static final int DOWN = 0;
    public static final int MOVE = 1;
    public static final int UP = 2;
    public static final int DIS = 3;
    public static final int LEFT = 4;
    public static final int XX = 5;
    long[] times = new long[]{0, 0};
    Finish finish;
    PagerView.P[] values = new PagerView.P[]{new PagerView.P(0f, 0f), new PagerView.P(0f, 0f), new PagerView.P(0f, 0f), new PagerView.P(0f, 0f), new PagerView.P(0f, 0f), new PagerView.P(0f, 0f)};
    ArrayList<RectF> rectFs = new ArrayList<>();

    public BottomFinishView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.E("down");
                values[DOWN].x = event.getX();
                values[DOWN].y = event.getY();
                times[0] = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.E("move");
                values[MOVE].x = event.getX();
                values[MOVE].y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                LogUtil.E("up");
                times[1] = System.currentTimeMillis();
                values[UP].x = event.getX();
                values[UP].y = event.getY();
                values[DIS].x = values[UP].x - values[DOWN].x;
                values[DIS].y = values[UP].y - values[DOWN].y;
                if ((values[DIS].y >= getWidth() / 4) && (values[DIS].y / values[DIS].x) < 1 && times[1] - times[0] <= 1000) {
                    if (finish != null) {
                        finish.finishUI();
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }


    public void setFinish(Finish finish) {
        this.finish = finish;
    }

    public static interface Finish {
        public void finishUI();
    }
}
