package com.summer.lib.base.ope;

import android.content.Context;

import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.ButterKnife;

/**
 * Created by ${viwmox} on 2016-06-19.
 */
public class BaseUIOpe<A extends BaseUIBean> extends BaseOpe {

    protected  A uiBean;

    protected Context context;

    public BaseUIOpe(Context context, A uiBean) {
        this.context = context;
        this.uiBean=uiBean;
        if (uiBean == null ||uiBean.itemView==null) {
            return;
        }
        ButterKnife.bind(this, uiBean.itemView);
    }

    public A getUiBean() {
        return uiBean;
    }
}
