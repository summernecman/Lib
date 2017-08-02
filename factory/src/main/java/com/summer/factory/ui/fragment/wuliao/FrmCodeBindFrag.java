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
        P().U().initTitleTxtEditTxt(P().D().getData(new String[]{"条码", "", "清空"}));
        P().U().initText1(P().D().getData(new String[]{"绑定信息"}));
        P().U().initRecycle();
        P().U().initBottom3(P().D().getData(new String[]{"信息"}));
        P().U().initRecycle1(P().D().getRecycleData2());
    }
}
