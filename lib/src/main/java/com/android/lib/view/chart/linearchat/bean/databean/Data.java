package com.android.lib.view.chart.linearchat.bean.databean;

import com.android.lib.view.chart.linearchat.bean.viewbean.RR;
import com.android.lib.view.chart.linearchat.bean.viewbean.XX;
import com.android.lib.view.chart.linearchat.bean.viewbean.YV;
import com.android.lib.view.chart.linearchat.bean.viewbean.YY;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-11-28.
 */
public class Data implements Serializable {


    private XX x;

    private YY y;

    private ArrayList<YV> yvs;

    private ArrayList<Value> values;

    private ArrayList<RR> circles;

    private float maxY;

    private float minY;


    public ArrayList<Value> getValues() {
        return values;
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }

    public XX getX() {
        return x;
    }

    public void setX(XX x) {
        this.x = x;
    }

    public YY getY() {
        return y;
    }

    public void setY(YY y) {
        this.y = y;
    }

    public ArrayList<YV> getYvs() {
        if (yvs == null) {
            yvs = new ArrayList<>();
        }
        return yvs;
    }

    public void setYvs(ArrayList<YV> yvs) {
        this.yvs = yvs;
    }

    public float getMinY() {
        return minY;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }

    public ArrayList<RR> getCircles() {
        if (circles == null) {
            circles = new ArrayList<>();
        }
        return circles;
    }

    public void setCircles(ArrayList<RR> circles) {
        this.circles = circles;
    }
}
