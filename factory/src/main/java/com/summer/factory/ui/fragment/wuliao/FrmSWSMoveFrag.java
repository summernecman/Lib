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
        getP().getU().initText3(getP().getD().getData(new String[]{"条码号", "库位", "品牌"}));
        getP().getU().initRecycle();
        getP().getU().initBottom5(getP().getD().getData(new String[]{"流程票", "", "清空"}));
        getP().getU().initBottom3(getP().getD().getData(new String[]{"库  位"}));
        getP().getU().initRecycle3(getP().getD().getRecycleData2());
    }
}
