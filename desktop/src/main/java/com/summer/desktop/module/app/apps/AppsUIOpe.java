package com.summer.desktop.module.app.apps;

//by summer on 2017-06-07.

import android.content.Context;

import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.bean.uibean.AppsFragUIBean;
import com.summer.lib.base.ope.BaseUIOpe;

import java.util.ArrayList;

public class AppsUIOpe extends BaseUIOpe<AppsFragUIBean> {


    public AppsUIOpe(Context context) {
        super(context, new AppsFragUIBean(context, null));
    }

    public void initList(ArrayList<AppDBBean> appDBBeen) {
        getUiBean().init();
        getUiBean().loadData(new AppsAdapter(context, appDBBeen));
        getUiBean().move();
    }


}
