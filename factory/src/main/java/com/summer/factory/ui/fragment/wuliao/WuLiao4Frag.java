package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class WuLiao4Frag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        WuLiao4Frag wuLiaoFrag = new WuLiao4Frag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().initTitleTxtSpiCheckTxt(getP().getD().getTextEditData3());
        getP().getU().initText3(getP().getD().getRecycleTitleData32());
        getP().getU().initRecycle();
        getP().getU().initBottom(getP().getD().getBottomData1());
        getP().getU().initRecycle3(getP().getD().getRecycleData3());
    }
}
