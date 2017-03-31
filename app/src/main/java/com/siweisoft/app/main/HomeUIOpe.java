package com.siweisoft.app.main;

import android.content.Context;

import com.siweisoft.app.bean.uibean.HomeUIBean;
import com.summer.lib.base.ope.BaseUIOpe;

public  class HomeUIOpe extends BaseUIOpe<HomeUIBean> {


    public HomeUIOpe(Context context) {
        super(context, new HomeUIBean(context,null));
    }


    public void a(int[] ints){
        getUiBean().init();
        getUiBean().load(new HomeAdapter(context,ints));
    }

}