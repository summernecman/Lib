package com.summer.desktop.bean.dabean;

//by summer on 2017-05-26.

import com.summer.lib.bean.databean.BaseDABean;

public class LinkNote extends BaseDABean {

    private String link;

    public LinkNote() {
    }

    public LinkNote(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
