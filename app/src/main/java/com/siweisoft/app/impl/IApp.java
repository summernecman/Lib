package com.siweisoft.app.impl;

//by summer on 2017-04-06.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.app.bean.dbbean.AppDBBean;

import java.util.ArrayList;

public interface IApp {
    void getApps(OnFinishListener listener);
    void setApps(ArrayList<AppDBBean> apps);

}
