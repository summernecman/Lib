package com.siweisoft.service.netdb.agree;

//by summer on 17-09-13.

import com.android.lib.bean.BaseBean;

public class AgreeNumBean extends BaseBean {

    private boolean agree;

    private int agreenum;

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public int getAgreenum() {
        return agreenum;
    }

    public void setAgreenum(int agreenum) {
        this.agreenum = agreenum;
    }
}
