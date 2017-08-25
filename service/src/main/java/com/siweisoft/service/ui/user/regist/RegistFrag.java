package com.siweisoft.service.ui.user.regist;

//by summer on 2017-07-10.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.LoginFrag;

import butterknife.OnClick;

public class RegistFrag extends BaseUIFrag<RegistUIOpe, RegistDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().bind.setRegist(getP().getD().getUserBean());
    }

    @OnClick({R.id.onRegist})
    public void onClickEvent(View v) {
        getP().getD().getUserI().regist(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if ((Boolean) o) {
                    FragmentUtil2.getInstance().add(activity, Value.ROOTID, new LoginFrag());
                }
            }
        });
    }

}
