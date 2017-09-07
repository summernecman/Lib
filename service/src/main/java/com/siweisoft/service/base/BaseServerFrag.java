package com.siweisoft.service.base;

//by summer on 17-09-04.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.ui.main.MainAct;

public class BaseServerFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseUIFrag<A, B> {

    TitleBean titleBean;

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof MainAct) {
            MainAct mainAct = (MainAct) getActivity();
            mainAct.getP().getU().initTitle();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActivity() instanceof MainAct) {
            MainAct mainAct = (MainAct) getActivity();
            mainAct.getP().getU().initTitle();
        }
    }

    public TitleBean getTitleBean() {
        return titleBean;
    }

    public void setTitleBean(TitleBean titleBean) {
        this.titleBean = titleBean;
        MainAct act = (MainAct) activity;
        act.getP().getU().initTitle();
    }
}
