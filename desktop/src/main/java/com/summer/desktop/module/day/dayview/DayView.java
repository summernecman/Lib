package com.summer.desktop.module.day.dayview;

//by summer on 2017-06-14.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.module.day.main.MinuteDAOpe;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.LogUtil;
import com.summer.lib.util.ScreenUtil;

import java.util.ArrayList;

public class DayView extends View {

    //移动的阈值
    private static final int TOUCH_SLOP = 20;
    ArrayList<ArrayList<MinuteRect>> rects = new ArrayList<>();
    int nowh = -1;
    int nowm = -1;
    int border = 1;
    int wnum = 24;
    int hnum = 60;
    ArrayList<TimeBean> times = new ArrayList<>();
    MinuteDAOpe minuteDAOpe;
    float w, h;
    OnlongClickWithHM longClickListener;
    private int mLastMotionX, mLastMotionY;
    //是否移动了
    private boolean isMoved;
    //长按的runnable
    private MyRunnable runnable;

    public DayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        minuteDAOpe = new MinuteDAOpe(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        w = (((ScreenUtil.w - border) / (wnum + 1)) - border);
        h = ((ScreenUtil.h - 50 * ValueConstant.DIMEN_1 - ScreenUtil.sbh - (hnum + 2)) / hnum);
        if (rects.size() == 0) {
            for (int i = 0; i < (wnum + 1); i++) {
                ArrayList<MinuteRect> rect = new ArrayList<>();
                for (int j = 0; j < (hnum + 1); j++) {
                    MinuteRect minuteRect = new MinuteRect(border * (i + 1) + w * i, border * (j + 1) + h * j, border * (i + 1) + w * i + w, border * (j + 1) + h * j + h, w, h);
                    rect.add(minuteRect);
                }
                rects.add(rect);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG));
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawColor(Color.DKGRAY);
        paint.setTextSize(rects.get(0).get(0).w / 2);
        nowh = minuteDAOpe.nowH();
        nowm = minuteDAOpe.nowM();
        for (int i = 0; i < rects.size(); i++) {
            for (int j = 0; j < rects.get(i).size(); j++) {
                if (j == 0 && i != 0) {
                    if ((6 - 1 + i) % 24 == nowh) {
                        paint.setColor(getResources().getColor(com.summer.lib.R.color.pink));
                    } else {
                        paint.setColor(Color.WHITE);
                    }
                    canvas.drawRect(new RectF(rects.get(i).get(j).left, rects.get(i).get(j).top, rects.get(i).get(j).right, rects.get(i).get(j).bottom), paint);
                    paint.setColor(Color.BLACK);
                    canvas.drawText("" + (6 - 1 + i) % 24, rects.get(i).get(j).left + rects.get(i).get(j).w / 3, rects.get(i).get(j).bottom - rects.get(i).get(j).h / 3, paint);
                } else if (i == 0 && j != 0) {
                    if (j == nowm) {
                        paint.setColor(getResources().getColor(com.summer.lib.R.color.pink));
                    } else {
                        paint.setColor(Color.WHITE);
                    }
                    canvas.drawRect(new RectF(rects.get(i).get(j).left, rects.get(i).get(j).top, rects.get(i).get(j).right, rects.get(i).get(j).bottom), paint);
                    paint.setColor(Color.BLACK);
                    canvas.drawText("" + (j), rects.get(i).get(j).left + rects.get(i).get(j).w / 3, rects.get(i).get(j).bottom - rects.get(i).get(j).h / 3, paint);
                } else if (i == 0 && j == 0) {
                    paint.setColor(Color.WHITE);
                    canvas.drawRect(new RectF(rects.get(i).get(j).left, rects.get(i).get(j).top, rects.get(i).get(j).right, rects.get(i).get(j).bottom), paint);
                } else {
                    int x = (nowh - 5) < 0 ? (nowh - 5) + wnum : (nowh - 5);
                    int y = nowm;
                    if ((i * hnum + j) < (x * hnum + y)) {
                        paint.setColor(Color.GRAY);
                    } else if ((i * hnum + j) == (x * hnum + y)) {
                        paint.setColor(getResources().getColor(com.summer.lib.R.color.pink));
                    } else {
                        paint.setColor(Color.WHITE);
                    }
                    for (int a = 0; a < times.size(); a++) {
                        int sx = (times.get(a).sh - 5) < 0 ? (times.get(a).sh - 5) + wnum : (times.get(a).sh - 5);
                        int sy = times.get(a).sm;
                        int ex = (times.get(a).eh - 5) < 0 ? (times.get(a).eh - 5) + wnum : (times.get(a).eh - 5);
                        int ey = times.get(a).em;
                        if ((i * hnum + j) >= ((sx * hnum + sy)) && (i * hnum + j) <= ((ex * hnum + ey))) {
                            paint.setColor(Color.BLUE);
                        }
                    }
                    canvas.drawRect(new RectF(rects.get(i).get(j).left, rects.get(i).get(j).top, rects.get(i).get(j).right, rects.get(i).get(j).bottom), paint);
                }
            }
        }
    }

    public void setNowTime(int h, int m) {
        nowh = h;
        nowm = m;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
                isMoved = false;
                int[] a = countHM(x, y);
                runnable = new MyRunnable(a[0], a[1]) {
                    @Override
                    public void run() {
                        if (longClickListener != null) {
                            int[] a = new int[2];
                            getLocationOnScreen(a);
                            if (a[0] == 0) {
                                longClickListener.onLongClick(DayView.this, h, m);
                            }
                        }
                    }
                };
                postDelayed(runnable, ViewConfiguration.getLongPressTimeout());
                break;
            case MotionEvent.ACTION_MOVE:
                if (isMoved) break;
                if (Math.abs(mLastMotionX - x) > TOUCH_SLOP
                        || Math.abs(mLastMotionY - y) > TOUCH_SLOP) {
                    //移动超过阈值，则表示移动了
                    isMoved = true;
                    removeCallbacks(runnable);
                }
                break;
            case MotionEvent.ACTION_UP:
                //释放了
                removeCallbacks(runnable);
                break;
        }
        return true;
    }

    public void setTimes(ArrayList<TimeBean> times) {
        this.times = times;
    }

    public void delete(int h, int m) {
        LogUtil.E(h + "--" + m);
        h = (h - 5) < 0 ? (h - 5) + wnum : (h - 5);
        for (int i = 0; i < times.size(); i++) {
            int sx = (times.get(i).sh - 5) < 0 ? (times.get(i).sh - 5) + wnum : (times.get(i).sh - 5);
            int sy = times.get(i).sm;
            int ex = (times.get(i).eh - 5) < 0 ? (times.get(i).eh - 5) + wnum : (times.get(i).eh - 5);
            int ey = times.get(i).em;
            if ((h * hnum + m) >= ((sx * hnum + sy)) && (h * hnum + m) <= ((ex * hnum + ey))) {
                times.remove(i);
                i--;
            }
        }
        invalidate();
    }

    public String getArea(int h, int m) {
        h = (h - 5) < 0 ? (h - 5) + wnum : (h - 5);
        for (int i = 0; i < times.size(); i++) {
            int sx = (times.get(i).sh - 5) < 0 ? (times.get(i).sh - 5) + wnum : (times.get(i).sh - 5);
            int sy = times.get(i).sm;
            int ex = (times.get(i).eh - 5) < 0 ? (times.get(i).eh - 5) + wnum : (times.get(i).eh - 5);
            int ey = times.get(i).em;
            if ((h * hnum + m) >= ((sx * hnum + sy)) && (h * hnum + m) <= ((ex * hnum + ey))) {
                return times.get(i).toString();
            }
        }
        return "";
    }

    public int[] countHM(int x, int y) {
        int a = (int) (5 + x / w);
        int b = (int) (y / h);
        if (a >= (wnum + 1)) {
            a = a - (wnum + 1);
        }
        LogUtil.E(x + "::" + y);
        return new int[]{a, b};
    }


    public void setLongClickListener(OnlongClickWithHM longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface OnlongClickWithHM {
        public void onLongClick(View v, int h, int m);
    }

    public class MyRunnable implements Runnable {

        public int h;
        public int m;

        public MyRunnable(int h, int m) {
            this.h = h;
            this.m = m;
        }

        @Override
        public void run() {

        }
    }
}
