package com.android.lib.view.coverview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.lib.R;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.SPUtil;
import com.android.lib.util.ScreenUtil;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ${viwmox} on 2016-09-13.
 */
public class CoverView extends View {


    int bottomHeight = 0;

    Bitmap snowBitmap;

    Random random = new Random();

    Paint paint = new Paint();

    Paint textPaint = new Paint();

    float x = 0, y = 0;

    int index = 0;


    Bitmap snowBitmap1;
    ExecutorService executorService;


    float a = 0, b = 0;

    Context context;

    int[] ints;
    int i = 0;

    public CoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        bottomHeight = (int) (context.getResources().getDimension(R.dimen.dimen_1) * 43);
        snowBitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        executorService = Executors.newFixedThreadPool(5);
        textPaint.setTextSize(20 * context.getResources().getDimension(R.dimen.dimen_1));
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setSubpixelText(true);
        ints = ScreenUtil.getInstance().getScreenSize(context);
        a = ints[0];
        b = random.nextInt(ints[1] - 300);
        index = SPUtil.getInstance().init(context).getInt(ValueConstant.INDEX_DANMU);
        if (index >= ValueConstant.strings.size()) {
            index = 0;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        x = random.nextFloat() * getWidth();
        y = random.nextFloat() * getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //dothing(canvas);
        danmu(canvas);
    }

    private void danmu(Canvas canvas) {

        canvas.drawText(ValueConstant.strings.get(index), a, b, textPaint);
    }

    public void refreshText() {
        a -= 1;
        if (a < -ValueConstant.strings.get(index).length() * textPaint.getTextSize()) {
            index++;
            index = index % ValueConstant.strings.size();
            a = ints[0];
            b = random.nextInt(ints[1] - 300);
            SPUtil.getInstance().init(context).saveInt(ValueConstant.INDEX_DANMU, index);
        }
    }

    private void dothing(Canvas canvas) {
        if (snowBitmap == null) {
            snowBitmap = Bitmap.createScaledBitmap(snowBitmap1, getWidth() / 10, getWidth() / 10, true);
        }
        if (canvas == null) {
            return;
        }
//            double x0 = Math.toRadians((-1)*10);
//            double y0= 200*(0.4*Math.sin(x0)+0.5)+getHeight()-(180)-bottomHeight;
//            Path path =new Path();
//
//            path.moveTo(0,getHeight()-bottomHeight);
//            path.lineTo((float)x0,(float)y0);
//            for(int i=0;i<=36;i++){
//                double x1 = Math.toRadians(i*10);
//                double n = Math.toRadians(num);
//                double y1= 50*(1*Math.sin(x1+n)-1)+getHeight()-bottomHeight;
//                path.lineTo((float) (x1*getWidth()/(2*Math.PI)),(float)y1);
//                x0=x1;
//                y0=y1;
//            }
//            path.lineTo(getWidth(),getHeight()-bottomHeight);
//            path.close();
//            canvas.clipPath(path);
        paint.setColor(Color.parseColor("#2196f3"));

        // canvas.drawCircle(x,y,20*i,paint);
//        canvas.drawBitmap(snowBitmap,x,y,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;

        }

        x = event.getX();
        y = event.getY();
//        new Mythread().start();
//        postInvalidate();
        return false;
    }

    class Mythread extends Thread {
        @Override
        public void run() {
            for (int m = 0; m <= 7 * 2; m++) {
                if (m <= 7) {
                    i = m;
                } else {
                    i = 14 - m;
                }
                postInvalidate();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
