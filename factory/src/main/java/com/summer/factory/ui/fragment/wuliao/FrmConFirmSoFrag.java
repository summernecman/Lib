package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.value.FactoryValue;

import java.util.ArrayList;

public class FrmConFirmSoFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        FrmConFirmSoFrag wuLiaoFrag = new FrmConFirmSoFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        P().U().initTitleTxtEdit(P().D().getData(new String[]{"销货单", ""}));
        P().U().initTitleTxt(P().D().getData(new String[]{"大、小包装/合格证"}));
        P().U().initText3(P().D().getData(new String[]{"品号", "单据数量", "拣货"}));
        P().U().initRecycle();
        ArrayList<LayoutDABean> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            data.add(P().D().getData(new String[]{"品号", "单据数量", "拣货"}));
        }
        P().U().initRecycle3(data);


    }
}
