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
        getOpes().getUi().initTitleTxtSpiCheckTxt(getOpes().getDa().getTextEditData3());
        getOpes().getUi().initText3(getOpes().getDa().getRecycleTitleData32());
        getOpes().getUi().initRecycle();
        getOpes().getUi().initBottom(getOpes().getDa().getBottomData1());
        getOpes().getUi().initRecycle3(getOpes().getDa().getRecycleData3());
    }
}
