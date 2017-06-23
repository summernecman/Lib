package com.android.lib.bean.databean;

/**
 * Created by ${viwmox} on 2016-09-30.
 */
public class LocationBean {

    private float x;

    private float y;

    public LocationBean() {
    }

    public LocationBean(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
