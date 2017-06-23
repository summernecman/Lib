package com.android.lib.view.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by SWSD on 2016-04-19.
 */
public class AppScrollView extends ScrollView {
    public AppScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppScrollView(Context context) {
        super(context);
    }

    public AppScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
//    @Override
//    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
//        return true;
//    }
//    @Override
//    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
//
//        return 0;
//    }
}
