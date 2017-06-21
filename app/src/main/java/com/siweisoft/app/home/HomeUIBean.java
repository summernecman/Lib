package com.siweisoft.app.home;

import android.content.Context;
import android.view.View;

import com.siweisoft.app.R;
import com.siweisoft.app.bean.dbbean.AppDBBean;
import com.siweisoft.app.impl.IApp;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseUIBean;
import com.summer.lib.util.IntentUtil;

import java.util.ArrayList;

public class HomeUIBean extends BaseUIBean<com.siweisoft.app.bean.uibean.HomeUIBean> implements View.OnClickListener, IApp {


    public HomeUIBean(Context context) {
        super(context, new com.siweisoft.app.bean.uibean.HomeUIBean(context, null));
    }

    @Override
    public void getApps(OnFinishListener listener) {

    }

    @Override
    public void setApps(ArrayList<AppDBBean> apps) {
        getUiBean().init();
        HomeAdapter homeAdapter= new HomeAdapter(context,apps);
        getUiBean().load(homeAdapter);
        homeAdapter.setOnClickListener(this);
        getUiBean().move();
    }

    @Override
    public void onClick(View v) {
        AppDBBean appDBBean = (AppDBBean) v.getTag(R.id.data);
        IntentUtil.getInstance().IntentTo(context,appDBBean.getPackageName());
    }
}