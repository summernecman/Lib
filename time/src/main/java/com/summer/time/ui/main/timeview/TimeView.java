package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.android.lib.util.LogUtil;

import java.util.ArrayList;


public class TimeView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder.Callback callback;

    private Canvas canvas;

    private boolean isrun;

    private boolean stop;

    private SurfaceHolder holder;

    ArrayList<DrawI> drawIS = new ArrayList<>();


    public TimeView(Context context) {
        super(context);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        callback = this;
        holder = getHolder();
        holder.removeCallback(this);
        holder.addCallback(this);
        //setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isrun = true;
        stop = false;
        drawIS.add(new BackRender(getContext()));
        drawIS.add(new HoursBackRender(getContext()));
        drawIS.add(new HoursAreaRender(getContext()));
        drawIS.add(new OneHoursAreaRender(getContext()));
        drawIS.add(new ThingHoursAreaRender(getContext()));
        drawIS.add(new MinBackRender(getContext()));
        drawIS.add(new MinAreaRender(getContext()));
        drawIS.add(new InnerBackRender(getContext()));
        drawIS.add(new ThingMinAreaRender(getContext()));

        //drawIS.add(new CenterRender(getContext()));
        drawIS.add(new HourRender(getContext()));
        drawIS.add(new MinRender(getContext()));
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isrun = false;
        LogUtil.E("surfaceDestroyed" + System.currentTimeMillis());
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE) {
            stop = false;
        } else {
            stop = true;
        }
    }

    @Override
    public void run() {
        while (isrun) {
            if (!stop) {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    myDraw(canvas);
                }
                holder.unlockCanvasAndPost(canvas);
            }
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void myDraw(Canvas canvas) {
        //canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < drawIS.size(); i++) {
            drawIS.get(i).onTimeDraw(canvas);
        }
    }

    public void UpdateThingArea(int sh, int sm, int eh, int em) {
        if (drawIS.size() < 4) {
            return;
        }
        ThingHoursAreaRender t = (ThingHoursAreaRender) drawIS.get(4);
        t.setTime(sh, sm, eh, em);

        ThingMinAreaRender m = (ThingMinAreaRender) drawIS.get(8);
        m.setTime(sh, sm, eh, em);
        stop = true;
        canvas = holder.lockCanvas();
        if (canvas != null) {
            myDraw(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
        stop = false;
    }

}