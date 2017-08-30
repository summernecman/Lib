package com.siweisoft.service.ui.user.rename;

//by summer on 17-08-30.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;

import butterknife.OnClick;

public class RenameFrag extends BaseUIFrag<RenameUIOpe, RenameDAOpe> {

    @Override
    public void doThing() {
        getP().getU().initInfo(getP().getD().getUserBean());
    }

    @OnClick({R.id.button})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.button:
                getP().getD().userI.setName(getP().getD().getUserBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        UserBean bean = (UserBean) o;
                        Value.userBean.setName(bean.getName());
                    }
                });
                break;
        }
    }
}
