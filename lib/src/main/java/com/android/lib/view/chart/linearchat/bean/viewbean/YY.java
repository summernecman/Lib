package com.android.lib.view.chart.linearchat.bean.viewbean;


import com.android.lib.constant.ValueConstant;

/**
 * Created by ${viwmox} on 2016-11-28.
 */
public class YY extends ChartItemBean {


    private float unitVal;


    private float unitDis = ValueConstant.DIMEN_1 * 50;

    private float maxDis;

    private float minDis;

    private float txtHight;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getMaxDis() {
        return maxDis;
    }

    public void setMaxDis(float maxDis) {
        this.maxDis = maxDis;
    }

    public float getMinDis() {
        return minDis;
    }

    public void setMinDis(float minDis) {
        this.minDis = minDis;
    }

    public float getUnitDis() {
        return unitDis;
    }

    public void setUnitDis(float unitDis) {
        this.unitDis = unitDis;
    }

    public float getUnitVal() {
        return unitVal;
    }

    public void setUnitVal(float unitVal) {
        this.unitVal = unitVal;
    }


    public float getTxtHight() {
        return txtHight;
    }

    public void setTxtHight(float txtHight) {
        this.txtHight = txtHight;
    }
}
