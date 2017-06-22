package com.summer.desktop.module.day;

//by summer on 2017-06-14.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.lib.bean.databean.LocationBean;
import com.summer.lib.bean.databean.XYBean;
import com.summer.lib.constant.color.ColorConstant;
import com.summer.lib.util.LogUtil;
import com.summer.lib.util.NullUtil;

import java.util.ArrayList;

public class DayView2 extends View implements View.OnLongClickListener {

    //移动的阈值
    private static final int TOUCH_SLOP = 5;
    ArrayList<ArrayList<MinuteBean>> rects = new ArrayList<>();
    int nowh = -1;
    int nowm = -1;
    int border = 1;
    int wnum = 60;
    int hnum = 24;
    ArrayList<TimeBean> times = new ArrayList<>();
    MinuteDAOpe minuteDAOpe;
    float w, h;
    OnlongClickWithHM longClickListener;
    XYBean xyBean = new XYBean();
    private float mLastMotionX, mLastMotionY;

    public DayView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        minuteDAOpe = new MinuteDAOpe(context);
        setOnLongClickListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        View view = (View) getParent();
        w = (view.getWidth()) / (wnum + 1);
        h = (view.getHeight()) / (hnum + 1);
        if (rects.size() == 0) {
            for (int i = 0; i < (hnum + 1); i++) {
                ArrayList<MinuteBean> rect = new ArrayList<>();
                for (int j = 0; j < (wnum + 1); j++) {
                    MinuteRect minuteRect = new MinuteRect(border * (j + 1) + w * j, border * (i + 1) + h * i, border * (j + 1) + w * j + w, border * (i + 1) + h * i + h, w, h);
                    MinuteBean minuteBean = new MinuteBean();
                    minuteBean.setMinuteRect(minuteRect);
                    rect.add(minuteBean);
                }
                rects.add(rect);
            }
        }
        initData();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void initData() {
        nowh = minuteDAOpe.nowH();
        nowm = minuteDAOpe.nowM();
        int x = (nowh - 5) < 0 ? (nowh - 5) + hnum : (nowh - 5);
        int y = nowm;

        for (int i = 0; i < rects.size(); i++) {
            for (int j = 0; j < rects.get(i).size(); j++) {
                rects.get(i).get(j).setColor(Color.WHITE);
                rects.get(i).get(j).setTextSize((int) (w));
                //black
                if (j == 0 && i != 0) {
                    if ((6 - 1 + i) % hnum == nowh) {
                        rects.get(i).get(j).setColor(Color.BLACK);
                    }
                    rects.get(i).get(j).setText("" + (6 - 1 + i) % hnum);
                } else if (i == 0 && j != 0) {
                    //横坐标0
                    if (j == nowm) {
                        rects.get(i).get(j).setColor(Color.BLACK);
                    }
                    rects.get(i).get(j).setText("" + (j));
                } else {
                    //已经过去的时间
                    if ((i * hnum + j) < (x * hnum + y) && i != 0 && j != 0) {
                        rects.get(i).get(j).setDone(true);
                        rects.get(i).get(j).setColor(Color.WHITE);
                    }
                }
            }
        }

        for (int a = 0; a < times.size(); a++) {
            //int color = Color.rgb(RandomUtil.getInstance().nextInt(255), RandomUtil.getInstance().nextInt(255), RandomUtil.getInstance().nextInt(255));
            int color = getResources().getColor(ColorConstant.colors[a % ColorConstant.colors.length]);
            for (int i = 0; i < rects.size(); i++) {
                for (int j = 0; j < rects.get(i).size(); j++) {
                    if (i != 0 && j != 0) {
                        int sx = (times.get(a).sh - 5) < 0 ? (times.get(a).sh - 5) + hnum : (times.get(a).sh - 5);
                        int sy = times.get(a).sm;
                        int ex = (times.get(a).eh - 5) < 0 ? (times.get(a).eh - 5) + hnum : (times.get(a).eh - 5);
                        int ey = times.get(a).em;
                        if ((sx * wnum + sy) < (ex * wnum + ey)) {
                            if ((i * wnum + j) >= ((sx * wnum + sy)) && (i * wnum + j) <= ((ex * wnum + ey))) {
                                rects.get(i).get(j).setColor(color);
                            }
                        } else {
                            if ((i * wnum + j) <= ((ex * wnum + ey)) || (i * wnum + j) >= (sx * wnum + sy)) {
                                rects.get(i).get(j).setColor(color);
                            }
                        }
                    }
                }
            }
            LocationBean locationBean = countCenterXY(times.get(a), rects.get(0).get(0).getTextSize());
        }

        for (int i = 0; i < rects.size(); i++) {
            for (int j = 0; j < rects.get(i).size(); j++) {
                if (i == x && j == y) {
                    rects.get(i).get(j).setColor(Color.BLACK);
                }
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG));
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
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
                if (rects.get(i).get(j).isDone()) {
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(2);
                    //float[] floats = rects.get(i).get(j).getMinuteRect().getCenter();
                    canvas.drawLine(rects.get(i).get(j).getMinuteRect().left, rects.get(i).get(j).getMinuteRect().top, rects.get(i).get(j).getMinuteRect().right, rects.get(i).get(j).getMinuteRect().bottom, paint);
                    canvas.drawLine(rects.get(i).get(j).getMinuteRect().right, rects.get(i).get(j).getMinuteRect().top, rects.get(i).get(j).getMinuteRect().left, rects.get(i).get(j).getMinuteRect().bottom, paint);
                    //canvas.drawCircle(floats[0],floats[1],w/5,paint);
                }
            }
        }
        for (int i = 0; i < times.size(); i++) {
            paint.setTextSize(rects.get(0).get(0).getTextSize());
            paint.setColor(rects.get(0).get(0).getTextColor());
            LocationBean locationBean = countCenterXY(times.get(i), rects.get(0).get(0).getTextSize());
            canvas.drawText(times.get(i).text, locationBean.getX() + w, locationBean.getY() + h, paint);
        }

    }

    public void initTouch(int x, int y) {
        xyBean.x = x;
        xyBean.y = y;
        for (int i = 0; i < rects.size(); i++) {
            for (int j = 0; j < rects.get(i).size(); j++) {
                if (i == xyBean.x && j == xyBean.y) {
                    rects.get(i).get(j).setColor(Color.RED);
                }
            }
        }
        invalidate();
    }

    public void setNowTime(int h, int m) {
        nowh = h;
        nowm = m;
    }


    //  //根据给出的时分算出所在区间并删除
    public void delete(int h, int m) {
        LogUtil.E(h + "--" + m);
        h = (h - 5) < 0 ? (h - 5) + hnum : (h - 5);
        for (int i = 0; i < times.size(); i++) {
            int sx = (times.get(i).sh - 5) < 0 ? (times.get(i).sh - 5) + hnum : (times.get(i).sh - 5);
            int sy = times.get(i).sm;
            int ex = (times.get(i).eh - 5) < 0 ? (times.get(i).eh - 5) + hnum : (times.get(i).eh - 5);
            int ey = times.get(i).em;
            if ((h * wnum + m) >= ((sx * wnum + sy)) && (h * wnum + m) <= ((ex * wnum + ey))) {
                times.remove(i);
                i--;
            }
        }
        initData();
        invalidate();
    }

    //根据给出的时分算出所在区间中心坐标
    public LocationBean countCenterXY(TimeBean timeBean, int textsize) {

        int sx = (timeBean.sh - 5) < 0 ? (timeBean.sh - 5) + hnum : (timeBean.sh - 5);
        int sy = timeBean.sm;
        int ex = (timeBean.eh - 5) < 0 ? (timeBean.eh - 5) + hnum + 1 : (timeBean.eh - 5) + 1;
        int ey = timeBean.em;

        LocationBean sl = new LocationBean((sy) * w, (sx) * h);
        return sl;
    }

    //根据给出的时分算出所在区间
    public String getArea(int h, int m) {
        h = (h - 5) < 0 ? (h - 5) + hnum : (h - 5);
        for (int i = 0; i < times.size(); i++) {
            int sx = (times.get(i).sh - 5) < 0 ? (times.get(i).sh - 5) + hnum : (times.get(i).sh - 5);
            int sy = times.get(i).sm;
            int ex = (times.get(i).eh - 5) < 0 ? (times.get(i).eh - 5) + hnum : (times.get(i).eh - 5);
            int ey = times.get(i).em;
            if (((sx * wnum + sy)) <= ((ex * wnum + ey))) {
                if ((h * wnum + m) >= ((sx * wnum + sy)) && (h * wnum + m) <= ((ex * wnum + ey))) {
                    return times.get(i).toString();
                }
            } else {
                if ((h * wnum + m) >= ((sx * wnum + sy)) || (h * wnum + m) <= ((ex * wnum + ey))) {
                    return times.get(i).toString();
                }
            }

        }
        return "";
    }

    //根据坐标算出小时分钟
    public int[] countHM(float x, float y) {
        int b = (int) (x / w);
        int a = (int) (5 + y / h);
        if (a >= (wnum + 1)) {
            a = a - (wnum + 1);
        }
        LogUtil.E(x + "::" + y);
        return new int[]{a, b};
    }

    public int[] countIJ(float x, float y) {
        int b = (int) (x / w);
        int a = (int) (y / h);
        return new int[]{a, b};
    }

    public void setLongClickListener(OnlongClickWithHM longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void refresh() {
        initData();
        invalidate();
    }

    public ArrayList<TimeBean> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<TimeBean> times) {
        this.times = times;
        initData();
        invalidate();
    }

    @Override
    public boolean onLongClick(View v) {

        int[] a = countHM(mLastMotionX, mLastMotionY);
        int[] c = countIJ(mLastMotionX, mLastMotionY);
        LogUtil.E(mLastMotionX + ":" + mLastMotionY + a[0] + ":" + a[1] + "---" + c[0] + ":" + c[1]);
        initTouch(c[0], c[1]);
        if (longClickListener != null) {
            longClickListener.onLongClick(v, a[0], a[1]);
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = event.getX();
                mLastMotionY = event.getY();
                LogUtil.E(mLastMotionX + ":" + mLastMotionY);
                super.dispatchTouchEvent(event);
                return true;
        }
        return super.dispatchTouchEvent(event);
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
