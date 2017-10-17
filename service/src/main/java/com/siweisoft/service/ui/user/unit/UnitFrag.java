package com.siweisoft.service.ui.user.unit;

//by summer on 17-09-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

import butterknife.OnClick;

public class UnitFrag extends BaseServerFrag<UnitUIOpe, UnitDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "更改单位", ""));
        getP().getU().initUnit(Value.getUserInfo().getUnit());
    }

    @OnClick({R.id.button})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.button:
                Value.getUserInfo().getUnit().setUnitname(getP().getU().bind.etInput.getText().toString());
                getP().getD().updateUnitInfo(Value.getUserInfo(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        Value.saveUserInfo((UserBean) o);
                        FragmentUtil2.getInstance().removeTop(activity, Value.ROOTID_THREE);
                    }
                });
                break;
        }
    }
}
