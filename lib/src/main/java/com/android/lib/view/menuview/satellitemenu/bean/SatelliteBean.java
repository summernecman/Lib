package com.android.lib.view.menuview.satellitemenu.bean;

/**
 * Created by ${viwmox} on 2016-11-07.
 */
public class SatelliteBean {

    int startx;

    int endx;

    int starty;

    int endy;

    public SatelliteBean(int startx, int starty, int endx, int endy) {
        this.endx = endx;
        this.endy = endy;
        this.startx = startx;
        this.starty = starty;
    }

    public int getEndx() {
        return endx;
    }

    public void setEndx(int endx) {
        this.endx = endx;
    }

    public int getEndy() {
        return endy;
    }

    public void setEndy(int endy) {
        this.endy = endy;
    }

    public int getStartx() {
        return startx;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public int getStarty() {
        return starty;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }
}
