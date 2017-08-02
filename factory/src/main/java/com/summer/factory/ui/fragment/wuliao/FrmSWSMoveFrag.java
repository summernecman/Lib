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
        P().U().initText3(P().D().getData(new String[]{"条码号", "库位", "品牌"}));
        P().U().initRecycle();
        P().U().initBottom5(P().D().getData(new String[]{"流程票", "", "清空"}));
        P().U().initBottom3(P().D().getData(new String[]{"库  位"}));
        P().U().initRecycle3(P().D().getRecycleData2());
    }
}
