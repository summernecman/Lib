package com.android.lib.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ${viwmox} on 2016-11-24.
 */
public class ScrollbleViewPager extends ViewPager {

    private boolean scrollble = false;

    public ScrollbleViewPager(Context context) {
        super(context);
    }

    public ScrollbleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!scrollble) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!scrollble) {
                    return false;
                }
        }
        return super.onTouchEvent(ev);
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }
}
