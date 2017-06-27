package com.summer.desktop.module.day.dayview;

//by summer on 2017-06-15.

import com.android.lib.bean.databean.BaseDABean;

public class MinuteBean extends BaseDABean {

    MinuteRect minuteRect;

    private int Color = android.graphics.Color.WHITE;

    private int textColor = android.graphics.Color.BLACK;

    private String text;

    private int textSize;

    private boolean done = false;

    public MinuteRect getMinuteRect() {
        return minuteRect;
    }

    public void setMinuteRect(MinuteRect minuteRect) {
        this.minuteRect = minuteRect;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
