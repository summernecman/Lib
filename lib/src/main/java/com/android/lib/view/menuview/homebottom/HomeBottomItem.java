package com.android.lib.view.menuview.homebottom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by ${viwmox} on 2017-03-22.
 */

public class HomeBottomItem extends LinearLayout {


    public HomeBottomItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getChildAt(0).setScaleX(0.9f);
                getChildAt(0).setScaleY(0.9f);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getChildAt(0).setScaleX(1f);
                getChildAt(0).setScaleY(1f);
                return true;
        }
        return true;
    }
}
