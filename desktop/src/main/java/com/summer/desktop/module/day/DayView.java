package com.summer.desktop.module.day;

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
import com.summer.lib.bean.databean.LocationBean;
import com.summer.lib.util.LogUtil;
import com.summer.lib.util.NullUtil;
import com.summer.lib.util.RandomUtil;
import com.summer.lib.util.ScreenUtil;

import java.util.ArrayList;

public class DayView extends View {

    //移动的阈值
    private static final int TOUCH_SLOP = 20;
    ArrayList<ArrayList<MinuteBean>> rects = new ArrayList<>();
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
        //h = ((ScreenUtil.h - 50 * ValueConstant.DIMEN_1 - ScreenUtil.sbh - (hnum + 2)) / hnum);
        View view = (View) getParent();
        h = (view.getHeight() - (hnum + 2) * border) / (hnum + 1);
        //w = (view.getWidth()-(wnum+2)*border)/(wnum+1);
        if (rects.size() == 0) {
            for (int i = 0; i < (wnum + 1); i++) {
                ArrayList<MinuteBean> rect = new ArrayList<>();
                for (int j = 0; j < (hnum + 1); j++) {
                    MinuteRect minuteRect = new MinuteRect(border * (i + 1) + w * i, border * (j + 1) + h * j, border * (i + 1) + w * i + w, border * (j + 1) + h * j + h, w, h);
                    MinuteBean minuteBean = new MinuteBean();
                    minuteBean.setMinuteRect(minuteRect);
                    rect.add(minuteBean);
                }
                rects.add(rect);
            }
        }
        initData();
    }

    public void initData() {
        nowh = minuteDAOpe.nowH();
        nowm = minuteDAOpe.nowM();
        int x = (nowh - 5) < 0 ? (nowh - 5) + wnum : (nowh - 5);
        int y = nowm;

        for (int i = 0; i < rects.size(); i++) {
            for (int j = 0; j < rects.get(i).size(); j++) {
                rects.get(i).get(j).setColor(Color.WHITE);
                rects.get(i).get(j).setTextSize((int) (w / 2));
                //纵坐标0
                if (j == 0 && i != 0) {
                    if ((6 - 1 + i) % 24 == nowh) {
                        rects.get(i).get(j).setColor(getResources().getColor(com.summer.lib.R.color.pink));
                    }
                    rects.get(i).get(j).setTextColor(Color.BLACK);
                    rects.get(i).get(j).setText("" + (6 - 1 + i) % 24);
                } else if (i == 0 && j != 0) {
                    //横坐标0
                    if (j == nowm) {
                        rects.get(i).get(j).setColor(getResources().getColor(com.summer.lib.R.color.pink));
                    }
                    rects.get(i).get(j).setText("" + (j));
                } else {
                    if ((i * hnum + j) < (x * hnum + y)) {
                        rects.get(i).get(j).setColor(Color.GRAY);
                    } else if ((i * hnum + j) == (x * hnum + y)) {
                        rects.get(i).get(j).setColor(getResources().getColor(com.summer.lib.R.color.pink));
                    }
                }
            }
        }

        for (int a = 0; a < times.size(); a++) {
            int color = Color.rgb(RandomUtil.getInstance().nextInt(255), RandomUtil.getInstance().nextInt(255), RandomUtil.getInstance().nextInt(255));
            for (int i = 0; i < rects.size(); i++) {
                for (int j = 0; j < rects.get(i).size(); j++) {
                    if (i != 0 && j != 0) {
                        int sx = (times.get(a).sh - 5) < 0 ? (times.get(a).sh - 5) + wnum : (times.get(a).sh - 5);
                        int sy = times.get(a).sm;
                        int ex = (times.get(a).eh - 5) < 0 ? (times.get(a).eh - 5) + wnum : (times.get(a).eh - 5);
                        int ey = times.get(a).em;
                        if ((i * hnum + j) >= ((sx * hnum + sy)) && (i * hnum + j) <= ((ex * hnum + ey))) {
                            rects.get(i).get(j).setColor(color);
                        }
                    }
                }
            }
            LocationBean locationBean = countCenterXY(times.get(a), rects.get(0).get(0).getTextSize());
        }

        for (int i = 0; i < rects.size(); i++) {
            for (int j = 0; j < rects.get(i).size(); j++) {
                if (i != 0 && j != 0 && ((i * hnum + j) == (x * hnum + y))) {
                    rects.get(i).get(j).setColor(getResources().getColor(com.summer.lib.R.color.pink));
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.E("canvas");
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG));
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawColor(Color.DKGRAY);
        paint.setTextSize(rects.get(0).get(0).getMinuteRect().w / 2);
        for (int i = 0; i < rects.size(); i++) {
            for (int j = 0; j < rects.get(i).size(); j++) {
                paint.setColor(rects.get(i).get(j).getColor());
                canvas.drawRect(new RectF(rects.get(i).get(j).getMinuteRect().left, rects.get(i).get(j).getMinuteRect().top, rects.get(i).get(j).getMinuteRect().right, rects.get(i).get(j).getMinuteRect().bottom), paint);
                if (!NullUtil.isStrEmpty(rects.get(i).get(j).getText())) {
                    paint.setColor(rects.get(i).get(j).getTextColor());
                    canvas.drawText(rects.get(i).get(j).getText(), rects.get(i).get(j).getMinuteRect().left + rects.get(i).get(j).getMinuteRect().w / 3, rects.get(i).get(j).getMinuteRect().bottom - rects.get(i).get(j).getMinuteRect().h / 3, paint);
                }
            }
        }

        for (int i = 0; i < times.size(); i++) {
            paint.setTextSize(rects.get(0).get(0).getTextSize());
            paint.setColor(rects.get(0).get(0).getTextColor());
            LocationBean locationBean = countCenterXY(times.get(i), rects.get(0).get(0).getTextSize());
            canvas.drawText(times.get(i).text, locationBean.getX(), locationBean.getY(), paint);
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
        initData();
        invalidate();
    }

    //  //根据给出的时分算出所在区间并删除
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
        initData();
        invalidate();
    }

    //根据给出的时分算出所在区间中心坐标
    public LocationBean countCenterXY(TimeBean timeBean, int textsize) {
        TimeBean bean = new TimeBean();
        bean.sh = timeBean.sh;
        bean.eh = timeBean.eh;
        if (timeBean.sh != timeBean.eh) {
            bean.sm = 0;
            bean.em = 60;
        } else {
            bean.sm = timeBean.sm;
            bean.em = timeBean.em;
        }

        int sx = (bean.sh - 5) < 0 ? (bean.sh - 5) + wnum : (bean.sh - 5);
        int sy = bean.sm + 1;
        int ex = (bean.eh - 5) < 0 ? (bean.eh - 5) + wnum + 1 : (bean.eh - 5) + 1;
        int ey = bean.em + 1;

        LocationBean sl = new LocationBean(sx * w, sy * h);
        LocationBean s2 = new LocationBean(ex * w, ey * h);
        LogUtil.E(sx + "-" + sy + "-" + ex + "-" + ey);
        LocationBean s3 = new LocationBean((sl.getX() + s2.getX()) / 2 - timeBean.text.length() * textsize / 2, (sl.getY() + s2.getY()) / 2 + 2 * textsize / 3);
        return s3;
    }

    //根据给出的时分算出所在区间
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

    //根据坐标算出小时分钟
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

    public void refresh() {
        initData();
        invalidate();
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
