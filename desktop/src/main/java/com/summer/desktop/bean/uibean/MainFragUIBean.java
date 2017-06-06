package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class MainFragUIBean extends BaseUIBean {


    @BindView(R.id.container)
    ViewPager container;

    public MainFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_main);
    }

    public ViewPager getContainer() {
        return container;
    }

    public void setContainer(ViewPager container) {
        this.container = container;
    }
}
