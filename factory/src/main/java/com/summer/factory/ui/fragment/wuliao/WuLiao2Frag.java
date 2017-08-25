package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class WuLiao2Frag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        WuLiao2Frag wuLiaoFrag = new WuLiao2Frag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().initTitleTxtEdit(getP().getD().getTextEditData2());
        getP().getU().initText2(getP().getD().getRecycleTitleData2());
        getP().getU().initRecycle();
        getP().getU().initBottom(getP().getD().getBottomData1());
        getP().getU().initRecycle2(getP().getD().getRecycleData2());
    }
}
