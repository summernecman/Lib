package com.summer.desktop.bean.dabean;

import com.summer.lib.bean.databean.BaseDABean;

/**
 * Created by summer on 2017/5/25 23:46.
 */

public class ImageNote extends BaseDABean {

    private String src = "";

    private String localSrc;

    private int width;

    private int height;

    public ImageNote(String localSrc, int width, int height) {
        this.localSrc = localSrc;
        this.width = width;
        this.height = height;
    }

    public ImageNote(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getLocalSrc() {
        return localSrc;
    }

    public void setLocalSrc(String localSrc) {
        this.localSrc = localSrc;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
