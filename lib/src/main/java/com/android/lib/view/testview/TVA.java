package com.android.lib.view.testview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.android.lib.util.LogUtil;

/**
 * Created by ${viwmox} on 2016-11-25.
 */
public class TVA extends TextView {


    public TVA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.E(getClass().getSimpleName() + ":dispatchTouchEvent--down");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.E(getClass().getSimpleName() + ":dispatchTouchEvent--move");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.E(getClass().getSimpleName() + ":dispatchTouchEvent--up");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.E(getClass().getSimpleName() + "--onTouchEvent--down");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.E(getClass().getSimpleName() + "--onTouchEvent--move");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.E(getClass().getSimpleName() + "--onTouchEvent--up");
                break;
        }
        return true;
    }
}
