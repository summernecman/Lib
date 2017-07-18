package com.summer.factory.ui.base;

//by summer on 2017-07-17.

import android.content.Context;
import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIBean;
import com.android.lib.util.LogUtil;
import com.summer.factory.R;
import com.summer.factory.databinding.ActBaseBinding;

public class BaseFactoryUIOpe extends BaseUIBean<ActBaseBinding> {


    public BaseFactoryUIOpe(Context context) {
        super(context);
    }

    public void initRadioButton(final OnFinishListener onFinishListener) {
        viewDataBinding.tabview.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int position = (int) group.findViewById(checkedId).getTag(R.id.position);
                LogUtil.E(position);
                onFinishListener.onFinish(position);
            }
        });
    }
}
