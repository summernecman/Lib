package com.summer.time.ui.main.thingview;

//by summer on 2017-11-28.

import com.android.lib.bean.BaseBean;
import com.summer.time.ui.main.timeview.TimeAreaBean;

public class ThingBean extends BaseBean {

    private String thing;

    private int height;

    private TimeAreaBean timeArea;

    private int pos;

    private String title;

    public ThingBean() {
    }

    public ThingBean(String thing, int height) {
        this.thing = thing;
        this.height = height;
    }

    public ThingBean(String title, String thing, TimeAreaBean timeArea, int pos, int height) {
        this.thing = thing;
        this.height = height;
        this.timeArea = timeArea;
        this.pos = pos;
        this.title = title;
    }

    public String getThing() {
        return thing;
    }


    public void setThing(String thing) {
        this.thing = thing;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TimeAreaBean getTimeArea() {
        return timeArea;
    }

    public void setTimeArea(TimeAreaBean timeArea) {
        this.timeArea = timeArea;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "ThingBean{" +
                "thing='" + thing + '\'' +
                ", height=" + height +
                ", timeArea=" + timeArea +
                ", pos=" + pos +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
