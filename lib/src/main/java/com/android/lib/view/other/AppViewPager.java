package com.android.lib.view.other;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.lang.reflect.Field;

/**
 * Created by ${viwmox} on 2016-04-28.
 */
public class AppViewPager extends ViewPager {

    public AppViewPager(Context context) {
        super(context);
        init();
    }

    public AppViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setPageTransformer(true, new FlipPagerTransformer());

        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(),
                    null);
            field.set(this, scroller);
            scroller.setmDuration(1000);
        } catch (Exception e) {

        }

    }

    @Override
    public void setPageTransformer(boolean reverseDrawingOrder, PageTransformer transformer) {
        super.setPageTransformer(reverseDrawingOrder, transformer);
    }
}
