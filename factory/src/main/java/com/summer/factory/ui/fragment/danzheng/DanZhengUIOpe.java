package com.summer.factory.ui.fragment.danzheng;

//by summer on 2017-07-20.

import android.content.Context;

import com.android.lib.base.ope.BaseUIBean;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragDanzhengBinding;

public class DanZhengUIOpe extends BaseUIBean<FragDanzhengBinding> {


    public DanZhengUIOpe(Context context) {
        super(context);
    }

    public void initData(LayoutDABean bean) {
        viewDataBinding.setDanzheng(bean);
    }
}
