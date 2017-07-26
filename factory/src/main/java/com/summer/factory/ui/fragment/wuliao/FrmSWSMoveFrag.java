package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class FrmSWSMoveFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        FrmSWSMoveFrag wuLiaoFrag = new FrmSWSMoveFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getOpes().getUi().initText3(getOpes().getDa().getData(new String[]{"条码号", "库位", "品牌"}));
        getOpes().getUi().initRecycle();
        getOpes().getUi().initBottom5(getOpes().getDa().getData(new String[]{"流程票", "", "清空"}));
        getOpes().getUi().initBottom3(getOpes().getDa().getData(new String[]{"库  位"}));
        getOpes().getUi().initRecycle3(getOpes().getDa().getRecycleData2());
    }
}
