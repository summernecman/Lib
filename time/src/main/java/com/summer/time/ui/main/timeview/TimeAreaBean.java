package com.summer.time.ui.main.timeview;

//by summer on 2017-11-28.

import com.android.lib.bean.BaseBean;

public class TimeAreaBean extends BaseBean {

    private TimeBean start;

    private TimeBean end;

    public TimeAreaBean() {
    }

    public TimeAreaBean(TimeBean start, TimeBean end) {
        this.start = start;
        this.end = end;
    }

    public TimeBean getStart() {
        return start;
    }

    public void setStart(TimeBean start) {
        this.start = start;
    }

    public TimeBean getEnd() {
        return end;
    }

    public void setEnd(TimeBean end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimeAreaBean{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
