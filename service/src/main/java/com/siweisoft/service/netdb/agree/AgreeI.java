package com.siweisoft.service.netdb.agree;

//by summer on 17-09-12.

import com.android.lib.base.interf.OnFinishListener;

public interface AgreeI {

    public void addAgree(AgreeBean agree, OnFinishListener onFinishListener);

    public void cancleAgree(AgreeBean agree, OnFinishListener onFinishListener);

    public void getAgreeNum(AgreeBean agree, OnFinishListener onFinishListener);

    public void isAgreeNum(AgreeBean agree, OnFinishListener onFinishListener);

    public void clickAgree(AgreeBean agree, OnFinishListener onFinishListener);
}
