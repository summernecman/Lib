package com.summer.lib.base.ope;

import android.content.Context;

import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.ButterKnife;

/**
 * ui处理操作者 处理对象 uibean fragment view
 */
public class BaseUIOpe<A extends BaseUIBean> implements BaseOpe {

    /**
     * ui布局类
     */
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
