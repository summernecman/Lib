package com.siweisoft.app.main;

//by summer on 2017-04-06.

import android.content.Context;

import com.siweisoft.app.bean.dbbean.AppDBBean;
import com.siweisoft.app.impl.IApp;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class MainDAOpe extends BaseDAOpe implements IApp{


    public MainDAOpe(Context context) {
        super(context);
    }

    @Override
    public void getApps(OnFinishListener listener) {

    }

    @Override
    public void setApps(ArrayList<AppDBBean> apps) {

    }
}
