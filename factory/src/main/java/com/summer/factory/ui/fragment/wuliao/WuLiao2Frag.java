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
        getOpes().getUi().initTitleTxtEdit(getOpes().getDa().getTextEditData2());
        getOpes().getUi().initText2(getOpes().getDa().getRecycleTitleData2());
        getOpes().getUi().initRecycle();
        getOpes().getUi().initBottom(getOpes().getDa().getBottomData1());
        getOpes().getUi().initRecycle2(getOpes().getDa().getRecycleData2());
    }
}