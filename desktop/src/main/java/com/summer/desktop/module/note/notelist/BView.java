package com.summer.desktop.module.note.notelist;

//by summer on 17-08-22.

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.android.lib.util.LogUtil;

public class BView extends RelativeLayout {


    public BView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.E(getClass().getSimpleName() + "dispatchTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.E(getClass().getSimpleName() + "dispatchTouchEvent", "ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.E(getClass().getSimpleName() + "onInterceptTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.E(getClass().getSimpleName() + "onInterceptTouchEvent", "ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.E(getClass().getSimpleName() + "onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.E(getClass().getSimpleName() + "onTouchEvent", "ACTION_UP");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
