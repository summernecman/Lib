package com.siweisoft.service.ui.video.seach;

//by summer on 17-09-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;

public class SeachFrag extends BaseServerFrag<SeachUIOpe, SeachDAOpe> {

    OnFinishListener onFinishListener;

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "搜索", "", "确定"));
        getP().getD().setSeachBean((SeachBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().getSeachBean().setCan(false);
        getP().getU().initList(getP().getD().getSeachBean().getData());
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:
                if (onFinishListener != null) {
                    getP().getD().getSeachBean().setCan(true);
                    if (getP().getD().isClear(getP().getD().getSeachBean())) {
                        getP().getD().getSeachBean().setCan(false);
                    }
                    getP().getD().getSeachBean().setTxt(getP().getU().bind.etInput.getText().toString());
                    onFinishListener.onFinish(getP().getD().getSeachBean());
                }
                break;
        }
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

}
