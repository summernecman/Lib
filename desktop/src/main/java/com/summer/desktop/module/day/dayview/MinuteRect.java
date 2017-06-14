package com.summer.desktop.module.day.dayview;

//by summer on 2017-06-14.

public class MinuteRect {

    public float left;

    public float top;

    public float right;

    public float bottom;

    public float h;

    public float w;

    public MinuteRect() {
    }

    public MinuteRect(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }


    public MinuteRect(float left, float top, float right, float bottom, float w, float h) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.h = h;
        this.w = w;
    }
}
