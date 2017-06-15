package com.summer.desktop.bean.dabean;

//by summer on 2017-06-13.

public class TimeBean {

    public int sh;

    public int sm;

    public int eh;

    public int em;

    public String text = "新建时段";


    public TimeBean(int sh, int sm, int eh, int em) {
        this.sh = sh;
        this.sm = sm;
        this.eh = eh;
        this.em = em;
    }

    public TimeBean() {

    }

    @Override
    public String toString() {
        return sh + ":" + sm + "--" + eh + ":" + em + "---" + text;
    }
}
