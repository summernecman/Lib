package com.android.lib.view.other;

import android.support.v4.view.ViewPager;
import android.view.View;

public class FlipPagerTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        if (position <= 0 && position >= -1) {
            page.setPivotX(page.getMeasuredWidth());
        } else if (position <= 1 && position >= -1) {
            page.setPivotX(0);
        }
        //page.setPivotY(page.getMeasuredHeight() * 0.5f);
        page.setRotationY(position * 90f);
    }

}