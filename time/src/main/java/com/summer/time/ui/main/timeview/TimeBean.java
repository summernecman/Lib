package com.summer.time.ui.main.timeview;

//by summer on 2017-11-27.

public class TimeBean {

    public int hour;

    public int secend;

    public int minute;

    public TimeBean() {
    }

    public TimeBean(int hour, int secend, int minute) {
        this.hour = hour;
        this.secend = secend;
        this.minute = minute;
    }

    public TimeBean(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
}
