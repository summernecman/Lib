package com.summer.desktop.module.note.note.bean;

//by summer on 2017-07-31.

import com.android.lib.bean.BaseBean;

public class NoteImageItemBean extends BaseBean {

    private String url;

    private String src;

    private int w;

    private int h;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
