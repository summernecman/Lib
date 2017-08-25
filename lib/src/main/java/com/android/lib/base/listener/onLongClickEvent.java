package com.android.lib.base.listener;

//by summer on 17-08-21.

import android.view.MotionEvent;
import android.view.View;

import com.android.lib.util.LogUtil;
import com.android.lib.util.system.HandleUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;

public class onLongClickEvent implements View.OnTouchListener {


    Runnable runnable;

    MaterialRefreshLayout refreshLayout;

    int velocityY = 0;

    public onLongClickEvent(MaterialRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
        runnable = new Runnable() {
            @Override
            public void run() {
                event();
            }
        };
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                HandleUtil.getInstance().postDelayed(runnable, 1000);
                LogUtil.E("ACTION_DOWN" + event.getRawX() + ":" + event.getRawX());
                break;
            case MotionEvent.ACTION_MOVE:
                HandleUtil.getInstance().removeCallbacks(runnable);
                LogUtil.E("ACTION_MOVE" + event.getRawX() + ":" + event.getRawX());
                break;
            case MotionEvent.ACTION_UP:
                HandleUtil.getInstance().removeCallbacks(runnable);
                LogUtil.E("ACTION_UP" + event.getRawX() + ":" + event.getRawX());
                break;
        }
        return true;
    }

    public void event() {

    }
}
