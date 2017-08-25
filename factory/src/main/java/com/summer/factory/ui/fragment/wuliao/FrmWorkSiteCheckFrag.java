package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class FrmWorkSiteCheckFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        FrmWorkSiteCheckFrag wuLiaoFrag = new FrmWorkSiteCheckFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().addWorkSiteCheck(getP().getD().getData(new String[]{"", "", "", ""}));
    }
}
