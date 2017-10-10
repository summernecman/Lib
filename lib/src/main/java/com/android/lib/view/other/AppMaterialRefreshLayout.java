package com.android.lib.view.other;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.bean.databean.LocationBean;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;


/**
 * Created by ${viwmox} on 2016-05-11.
 */
public class AppMaterialRefreshLayout extends MaterialRefreshLayout {

    Runnable runnable;

    Handler handler = new Handler();

    OnFinishListener onFinishListener;
    LocationBean locationBean = new LocationBean();

    public AppMaterialRefreshLayout(Context context) {
        super(context);
        init();
    }

    public AppMaterialRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppMaterialRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(AppMaterialRefreshLayout.this);
                }
            }
        };
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                locationBean.setX(ev.getX());
                locationBean.setY(ev.getY());
                handler.postDelayed(runnable, 1000);
                //LogUtil.E(getClass().getSimpleName() + "dispatchTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(locationBean.getX() - ev.getX()) > 20 || Math.abs(locationBean.getY() - ev.getY()) > 20) {
                    handler.removeCallbacks(runnable);
                }
                //LogUtil.E(getClass().getSimpleName() + "dispatchTouchEvent", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                handler.removeCallbacks(runnable);
                //LogUtil.E(getClass().getSimpleName() + "dispatchTouchEvent", "ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //LogUtil.E(getClass().getSimpleName() + "onInterceptTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                //LogUtil.E(getClass().getSimpleName() + "onInterceptTouchEvent", "ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //LogUtil.E(getClass().getSimpleName() + "onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                //LogUtil.E(getClass().getSimpleName() + "onTouchEvent", "ACTION_UP");
                break;
        }
        return super.onTouchEvent(ev);
    }


    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
