package com.android.lib.base.listener;

//by summer on 17-08-21.

import android.view.View;

public interface ViewListener {

    public static final int TYPE_ONCLICK = 0;

    public static final int TYPE_ONLONGCLICK = 1;

    public static final int TYPE_TOUCH = 2;

    public int type = TYPE_ONCLICK;

    public abstract void onInterupt(int type, View v);
}
