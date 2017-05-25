package com.siweisoft.app.impl;

//by summer on 2017-04-06.

import com.siweisoft.app.bean.dbbean.AppDBBean;
import com.summer.lib.base.interf.OnFinishListener;

import java.util.ArrayList;

public interface IApp {
    void getApps(OnFinishListener listener);
    void setApps(ArrayList<AppDBBean> apps);

}
