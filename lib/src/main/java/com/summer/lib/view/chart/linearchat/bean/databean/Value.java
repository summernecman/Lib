package com.summer.lib.view.chart.linearchat.bean.databean;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2016-11-28.
 */
public class Value implements Serializable {

    private float x;

    private float y;

    public Value(float x, float y) {
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
