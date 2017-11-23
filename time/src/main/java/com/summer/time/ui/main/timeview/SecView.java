package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.android.lib.util.LogUtil;

import java.util.ArrayList;


public class SecView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder.Callback callback;

    private Canvas canvas;

    private boolean isrun;

    private boolean stop;

    private SurfaceHolder holder;

    ArrayList<DrawI> drawIS = new ArrayList<>();


    public SecView(Context context) {
        super(context);
    }

    public SecView(Context context, AttributeSet attrs) {
        super(context, attrs);
        callback = this;
        holder = getHolder();
        holder.removeCallback(this);
        holder.addCallback(this);
        setZOrderMediaOverlay(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isrun = true;
        stop = false;
        //drawIS.add(new InnerBackRender(getContext()));
        drawIS.add(new SecRender(getContext()));
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
                    update();
                }
                holder.unlockCanvasAndPost(canvas);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void myDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < drawIS.size(); i++) {
            drawIS.get(i).onTimeDraw(canvas);
        }
    }

    private void update() {
        ((SecRender) drawIS.get(0)).sec();
    }

}
