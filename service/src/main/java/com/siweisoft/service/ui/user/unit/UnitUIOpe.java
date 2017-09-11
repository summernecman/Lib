package com.siweisoft.service.ui.user.unit;

//by summer on 17-09-11.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.StringUtil;
import com.siweisoft.service.databinding.FragRenameBinding;
import com.siweisoft.service.netdb.unit.UnitBean;

public class UnitUIOpe extends BaseUIOpe<FragRenameBinding> {


    public UnitUIOpe(Context context) {
        super(context);
    }

    public void initUnit(UnitBean unitBean) {
        if (unitBean == null) {
            return;
        }
        bind.etInput.setText(StringUtil.getStr(unitBean.getUnitname()));
    }
}
