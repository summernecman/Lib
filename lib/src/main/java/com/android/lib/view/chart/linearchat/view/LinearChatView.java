package com.android.lib.view.chart.linearchat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.math.MathUtil;
import com.android.lib.view.chart.linearchat.bean.databean.Data;
import com.android.lib.view.chart.linearchat.bean.databean.Value;
import com.android.lib.view.chart.linearchat.bean.viewbean.RR;
import com.android.lib.view.chart.linearchat.bean.viewbean.XX;
import com.android.lib.view.chart.linearchat.bean.viewbean.YV;
import com.android.lib.view.chart.linearchat.bean.viewbean.YY;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ${viwmox} on 2016-11-28.
 */
public class LinearChatView extends View {

    Value[] p = new Value[4];
    private Data data;


    public LinearChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearChatView(Context context) {
        super(context);
        init();
    }

    private void init() {


        p = new Value[]{new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0)};

        Data data = new Data();
        ArrayList<Value> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            values.add(new Value(100 * random.nextFloat(), 100 * random.nextFloat()));
        }
        data.setValues(values);
        XX x = new XX();
        x.setLineHight(ValueConstant.DIMEN_1 * 5);
        x.setTxtHight(ValueConstant.DIMEN_1 * 25);
        x.getPaint().setColor(Color.GRAY);
        ArrayList<Float> floats = new ArrayList<>();
        for (int i = 0; i < data.getValues().size(); i++) {
            floats.add(data.getValues().get(i).getX());
        }
        x.setMaxVal(MathUtil.max(floats));
        x.setMinVal(MathUtil.min(floats));


        data.setX(x);

        YY y = new YY();
        y.setTxtHight(ValueConstant.DIMEN_1 * 25);
        y.getPaint().setColor(Color.GRAY);
        y.getPaint().setTextSize(ValueConstant.DIMEN_1 * 16);
        y.setUnitVal(-1);

        data.setY(y);


        setData(data);

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (true) {
            YY yy = data.getY();
            XX xx = data.getX();


            yy.setRectF(new RectF(yy.getTxtHight(), 0, yy.getTxtHight(), getHeight() - xx.getTxtHight()));


            if (yy.getUnitVal() == -1) {
                for (int i = 0; i < data.getValues().size(); i++) {
                    YV yv = new YV();
                    yv.getPaint().setColor(Color.RED);
                    yv.setRectF(new RectF(yy.getTxtHight() + yy.getUnitDis() * i, 0, yy.getTxtHight() + yy.getUnitDis() * i + yv.getPly(), getHeight() - xx.getTxtHight() - xx.getLineHight()));
                    data.getYvs().add(yv);
                }
            }
            xx.setMaxDis(data.getYvs().get(data.getYvs().size() - 1).getRectF().right);
            xx.setRectF(new RectF(yy.getTxtHight(), getHeight() - xx.getLineHight() - xx.getTxtHight(), xx.getMaxDis(), getHeight() - xx.getTxtHight()));

            ArrayList<Float> fy = new ArrayList<>();
            for (int i = 0; i < data.getValues().size(); i++) {
                fy.add(data.getValues().get(i).getY());
            }
            data.setMaxY(MathUtil.max(fy));
            data.setMinY(MathUtil.min(fy));


            int count = (int) (1 + ((data.getMaxY() - data.getMinY()) / data.getValues().size()));
            data.getY().setCount(count);
            data.getY().setUnitDis(data.getYvs().get(0).getRectF().height() / count);
            data.getY().setMaxDis(data.getYvs().get(0).getRectF().height());
            for (int i = 0; i < data.getYvs().size(); i++) {
                RR rr = new RR(data.getYvs().get(i).getRectF().left + data.getYvs().get(i).getPly() / 2,
                        (data.getValues().get(i).getY() * data.getYvs().get(i).getRectF().height()) / (data.getMaxY() - data.getMinY()),
                        data.getYvs().get(i).getRadous());
                rr.getPaint().setColor(Color.BLUE);
                data.getCircles().add(rr);
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipRect(getLeft() + getPaddingLeft(), getTop() + getPaddingTop(), getRight() - getPaddingRight(), getBottom() - getPaddingBottom());
        canvas.save();
        canvas.translate(p[4].getX(), 0);
        drawX(canvas);
        drawY(canvas);
        drawYV(canvas);
        drawYValue(canvas);
        canvas.restore();
        canvas.restore();
    }


    private void drawX(Canvas canvas) {
        canvas.drawRect(data.getX().getRectF(), data.getX().getPaint());


    }

    private void drawY(Canvas canvas) {
        for (int i = 0; i < data.getY().getCount(); i++) {
            canvas.drawText((data.getMaxY() - data.getMinY()) * (data.getY().getCount() - i) + "", 0, data.getY().getUnitDis() * i, data.getY().getPaint());
        }
        canvas.drawRect(data.getY().getRectF(), data.getY().getPaint());


    }

    private void drawYV(Canvas canvas) {
        if (data.getY().getUnitVal() == -1) {
            for (int i = 0; i < data.getYvs().size(); i++) {
                canvas.drawRect(data.getYvs().get(i).getRectF(), data.getYvs().get(i).getPaint());
            }
        }


    }

    private void drawYValue(Canvas canvas) {

        int count = (int) (1 + ((data.getMaxY() - data.getMinY()) / data.getValues().size()));
        data.getY().setUnitDis(data.getYvs().get(0).getRectF().height() / count);
        for (int i = 0; i < data.getYvs().size(); i++) {
            canvas.drawCircle(data.getCircles().get(i).getCx(), data.getCircles().get(i).getCy(), data.getCircles().get(i).getCr(), data.getCircles().get(i).getPaint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                p[0].setX(event.getX());
                p[0].setY(event.getY());
                break;
            case MotionEvent.ACTION_MOVE:

                p[1].setX(event.getX());
                p[1].setY(event.getY());
                float t = p[3].getX() + p[1].getX() - p[0].getX();
                if (-t >= (data.getX().getMaxDis() - getWidth()) || (t) > 0) {
                    break;
                }

                p[4].setX(p[3].getX() + p[1].getX() - p[0].getX());
                p[4].setY(p[3].getY() + p[1].getY() - p[0].getY());

                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                p[2].setX(event.getX());
                p[2].setY(event.getY());

                p[3].setX(p[4].getX());
                p[3].setY(p[4].getY());
                break;
        }
        return true;
    }

    public void setData(Data data) {
        this.data = data;

    }
}
