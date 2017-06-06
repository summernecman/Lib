package com.summer.desktop.bean.dabean;

//by summer on 2017-05-25.

import com.summer.lib.bean.databean.BaseDABean;

public class NoteBean extends BaseDABean {


    public static final String TXT = "txt";
    public static final String IMAGE = "image";


    private String tagName;

    private String txt;

    private String html;

    public NoteBean(String tagName, String txt, String html) {
        this.tagName = tagName;
        this.txt = txt;
        this.html = html;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
