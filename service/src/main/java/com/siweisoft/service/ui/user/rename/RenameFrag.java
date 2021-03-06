package com.siweisoft.service.ui.user.rename;

//by summer on 17-08-30.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;

import butterknife.OnClick;

public class RenameFrag extends BaseServerFrag<RenameUIOpe, RenameDAOpe> {

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
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                    }
                });
                break;
        }
    }
}
