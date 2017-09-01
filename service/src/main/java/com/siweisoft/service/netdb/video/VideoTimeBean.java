package com.siweisoft.service.netdb.video;

//by summer on 17-09-01.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class VideoTimeBean extends BaseBean {

    int timeout;

    int timein;

    long timehours;

    @Bindable
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Bindable
    public int getTimein() {
        return timein;
    }

    public void setTimein(int timein) {
        this.timein = timein;
    }

    @Bindable
    public long getTimehours() {
        return timehours;
    }

    public void setTimehours(long timehours) {
        this.timehours = timehours;
    }
}
