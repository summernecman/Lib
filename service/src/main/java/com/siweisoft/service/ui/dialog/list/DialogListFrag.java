package com.siweisoft.service.ui.dialog.list;

//by summer on 17-09-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;

public class DialogListFrag extends BaseServerFrag<DialogListUIOpe, DialogListDAOpe> implements ViewListener {

    OnFinishListener onFinishListener;

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "搜索", "", ""));
        getP().getD().setList(getArguments().getStringArrayList(ValueConstant.DATA_DATA));
        getP().getU().initList(getP().getD().getList(), this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    @Override
    public void onInterupt(int type, View v) {
        if (onFinishListener != null) {
            onFinishListener.onFinish(v.getTag(R.id.data));
        }
    }
}
