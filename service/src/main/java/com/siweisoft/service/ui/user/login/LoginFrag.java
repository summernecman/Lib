package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.FragmentUtil;
import com.android.lib.util.NullUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.regist.RegistFrag;

import butterknife.OnClick;

public class LoginFrag extends BaseUIFrag<LoginUIBean, LoginDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUi().viewDataBinding.setUserinfo(getOpes().getDa().getUserInfo());
    }

    @OnClick({R.id.button, R.id.forgetpwd, R.id.regist})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (NullUtil.isStrEmpty(getOpes().getDa().getUserInfo().name.get())) {
                    return;
                }
                getOpes().getDa().login(getOpes().getDa().getUserInfo());
                break;
            case R.id.forgetpwd:

                break;
            case R.id.regist:
                FragmentUtil.getInstance().add(activity, Value.ROOTID, new RegistFrag());
                break;
        }
    }
}
