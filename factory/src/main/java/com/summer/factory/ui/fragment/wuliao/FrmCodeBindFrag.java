package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class FrmCodeBindFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        FrmCodeBindFrag wuLiaoFrag = new FrmCodeBindFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().initTitleTxtEditTxt(getP().getD().getData(new String[]{"条码", "", "清空"}));
        getP().getU().initText1(getP().getD().getData(new String[]{"绑定信息"}));
        getP().getU().initRecycle();
        getP().getU().initBottom3(getP().getD().getData(new String[]{"信息"}));
        getP().getU().initRecycle1(getP().getD().getRecycleData2());
    }
}
