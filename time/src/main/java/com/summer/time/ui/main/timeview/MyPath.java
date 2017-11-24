package com.summer.time.ui.main.timeview;

//by summer on 2017-11-24.

import android.graphics.Path;

public class MyPath extends Path {

    private boolean used = true;

    public MyPath(boolean used) {
        this.used = used;
    }

    public MyPath() {
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
