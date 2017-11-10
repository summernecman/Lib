package com.android.lib.view.weekview.dayview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.lib.R;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;
import com.android.lib.view.weekview.dayview.bean.PlanTipBean;
import com.android.lib.view.weekview.dayview.bean.TimeLableBean;
import com.android.lib.view.weekview.dayview.interf.OnTipClickListener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ${viwmox} on 2016-11-04.
 */
public class DayView extends View {


    public static final int PLAN_TIP_MARGIN_LEFT = ValueConstant.DIMEN_1 * 5;
    Context context;
    Paint paint = new Paint();
    Paint bgPaint = new Paint();
    Paint planTipPaint = new Paint();
    Paint txtPaint = new TextPaint();
    String[] txt = new String[]{"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "1", "2", "3", "4", "5"};
    TimeLableBean timeLableBean;
    ArrayList<PlanTipBean> planTipBeen = new ArrayList<>();
    OnTipClickListener onClickListener;
    private ArrayList<Rect> planTipRect = new ArrayList<>();

    public DayView(Context context) {
        super(context);
        init(context);
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(ValueConstant.DIMEN_1);
        paint.setStyle(Paint.Style.STROKE);


        timeLableBean = new TimeLableBean();
        timeLableBean.textColor = Color.BLACK;
        timeLableBean.textSize = ValueConstant.DIMEN_1 * 15;
        timeLableBean.txt = txt;
        timeLableBean.width = ValueConstant.DIMEN_1 * 20;
        timeLableBean.height = ValueConstant.DIMEN_1 * 120 * 24;

        bgPaint.setColor(getResources().getColor(R.color.color_base_graybg));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                setMeasuredDimension(ScreenUtil.getInstance().getScreenSize(context)[0], timeLableBean.height);
                break;
            case MeasureSpec.EXACTLY:
                setMeasuredDimension(w, timeLableBean.height);
                break;
            case MeasureSpec.UNSPECIFIED:
                setMeasuredDimension(ScreenUtil.getInstance().getScreenSize(context)[0], timeLableBean.height);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        drawTimeLine(canvas);
        drawTimeLable(canvas);
        drawPlanTip(canvas);

    }


    public void drawTimeLine(Canvas canvas) {
        canvas.drawRect(0,
                0,
                getWidth(), getTimeY(timeLableBean.getEachHeigh(),
                        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                        Calendar.getInstance().get(Calendar.MINUTE)),
                bgPaint);
    }

    public void drawTimeLable(Canvas canvas) {
        canvas.drawRect(0, 0, timeLableBean.width, timeLableBean.height, paint);
        LogUtil.E(timeLableBean);
        txtPaint.setTextSize(timeLableBean.textSize);
        txtPaint.setColor(timeLableBean.textColor);
        txtPaint.setAntiAlias(true);

        for (int i = 0; i < timeLableBean.txt.length; i++) {
            Rect rect = new Rect();
            txtPaint.getTextBounds(timeLableBean.txt[i].toCharArray(), 0, timeLableBean.txt[i].length(), rect);
            int n = getWidth() / ValueConstant.DIMEN_1 * 10;
            txtPaint.setAlpha(100);
            canvas.drawLine(0, timeLableBean.getEachHeigh() * i, getWidth(), timeLableBean.getEachHeigh() * i, txtPaint);
            txtPaint.setAlpha(255);
            canvas.drawText(timeLableBean.txt[i],
                    (timeLableBean.width - rect.width()) / 2,
                    (i * (timeLableBean.getEachHeigh())) + (timeLableBean.getEachHeigh() - timeLableBean.textSize) / 2 + timeLableBean.textSize,
                    txtPaint);
        }
    }


    public void drawPlanTip(Canvas canvas) {
        int w = 0;
        for (int i = 0; i < planTipBeen.size(); i++) {
            planTipPaint.setStyle(Paint.Style.FILL);
            if (!planTipBeen.get(i).isSelect()) {
                planTipPaint.setColor(planTipBeen.get(i).getBgColor());
            } else {
                planTipPaint.setColor(Color.GRAY);
            }
            canvas.drawRect(planTipRect.get(i),
                    planTipPaint);
            w += planTipBeen.get(i).getWidth();
        }
        w = 0;
        for (int i = 0; i < planTipBeen.size(); i++) {
            planTipPaint.setColor(planTipBeen.get(i).getBorderColor());
            planTipPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(planTipRect.get(i),
                    planTipPaint);
            if (planTipBeen.get(i).isSelect()) {
                canvas.drawLine(timeLableBean.width,
                        planTipRect.get(i).top,
                        planTipRect.get(i).left,
                        planTipRect.get(i).top,
                        txtPaint);

                canvas.drawLine(timeLableBean.width,
                        planTipRect.get(i).bottom,
                        planTipRect.get(i).left,
                        planTipRect.get(i).bottom,
                        txtPaint);

                canvas.drawText(planTipBeen.get(i).getStarMinite() + "分",
                        timeLableBean.width,
                        planTipRect.get(i).top,
                        txtPaint
                );
                canvas.drawText(planTipBeen.get(i).getEndMinite() + "分",
                        timeLableBean.width,
                        planTipRect.get(i).bottom,
                        txtPaint);
            }
            w += planTipBeen.get(i).getWidth();
        }


        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Integer> integer1 = new ArrayList<>();

        for (int i = 0; i < planTipBeen.size(); i++) {
            int ww = 0;
            for (int j = 0; j < planTipBeen.get(i).getTxt().length(); j++) {
                Rect rect = new Rect();
                txtPaint.getTextBounds(planTipBeen.get(i).getTxt().toCharArray(), j, 1, rect);
                if (ww == 0) {
                    integer1.add(rect.height());
                }
                ww += rect.height();

            }
            integers.add(ww);
        }
        w = 0;
        for (int i = 0; i < planTipBeen.size(); i++) {
            canvas.save();
            canvas.translate(0, planTipBeen.get(i).getHeight(integer1.get(i) + timeLableBean.getEachHeigh()) / 2 - integers.get(i) / 2);
            int ww = 0;
            for (int j = 0; j < planTipBeen.get(i).getTxt().length(); j++) {
                Rect rect = new Rect();
                txtPaint.getTextBounds(planTipBeen.get(i).getTxt().toCharArray(), j, 1, rect);
                canvas.drawText(String.valueOf(planTipBeen.get(i).getTxt().charAt(j)),
                        timeLableBean.width + PLAN_TIP_MARGIN_LEFT + w + planTipBeen.get(i).getWidth() / 2 - rect.width() / 2,
                        planTipBeen.get(i).getStartY(timeLableBean.getEachHeigh()) + ww,
                        txtPaint
                );
                ww += rect.height();
            }
            w += planTipBeen.get(i).getWidth();
            canvas.restore();
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < planTipRect.size(); i++) {
                    if (event.getX() > planTipRect.get(i).left && event.getX() < planTipRect.get(i).right) {
                        if (event.getY() > planTipRect.get(i).top && event.getY() < planTipRect.get(i).bottom) {
                            planTipBeen.get(i).setSelect(true);
                            postInvalidate();
                            break;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < planTipRect.size(); i++) {
                    if (event.getX() > planTipRect.get(i).left && event.getX() < planTipRect.get(i).right) {
                        if (event.getY() > planTipRect.get(i).top && event.getY() < planTipRect.get(i).bottom) {
                            if (onClickListener != null) {
                                onClickListener.onTipClick(this, planTipBeen.get(i));
                            }
                            break;
                        }
                    }
                    planTipBeen.get(i).setSelect(false);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                for (int i = 0; i < planTipRect.size(); i++) {
                    planTipBeen.get(i).setSelect(false);
                }
                postInvalidate();
                break;
        }

        return true;

    }

    public void setPlanTipBeen(ArrayList<PlanTipBean> p) {
        this.planTipBeen.clear();
        if (p != null) {
            this.planTipBeen.addAll(p);
        }
        planTipRect.clear();
        int w = 0;
        for (int i = 0; i < planTipBeen.size(); i++) {
            planTipPaint.setStyle(Paint.Style.FILL);
            planTipPaint.setColor(planTipBeen.get(i).getBgColor());
            planTipRect.add(new Rect(timeLableBean.width + PLAN_TIP_MARGIN_LEFT + w,
                    planTipBeen.get(i).getStartY(timeLableBean.getEachHeigh()),
                    timeLableBean.width + PLAN_TIP_MARGIN_LEFT + w + planTipBeen.get(i).getWidth(),
                    planTipBeen.get(i).getEndY(timeLableBean.getEachHeigh())));
            w += planTipBeen.get(i).getWidth();
        }
    }

    public void setOnClickListener(OnTipClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public TimeLableBean getTimeLableBean() {
        return timeLableBean;
    }

    public int getTimeY(int eachY, int starHour, int starMinite) {
        if (starHour >= 6) {
            return (int) (((float) ((starHour - 6) * 60 + starMinite) * eachY) / 60);
        } else {
            return (int) (((float) ((starHour + 19) * 60 + starMinite) * eachY) / 60);
        }

    }
}
