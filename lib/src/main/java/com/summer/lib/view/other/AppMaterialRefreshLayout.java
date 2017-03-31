package com.summer.lib.view.other;

import android.content.Context;
import android.util.AttributeSet;

import com.summer.lib.view.refreshlayout.MaterialRefreshLayout;


/**
 * Created by ${viwmox} on 2016-05-11.
 */
public class AppMaterialRefreshLayout extends MaterialRefreshLayout {
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

    }
}
