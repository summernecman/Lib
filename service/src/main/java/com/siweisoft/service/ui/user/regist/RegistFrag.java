package com.siweisoft.service.ui.user.regist;

//by summer on 2017-07-10.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;

import butterknife.OnClick;

public class RegistFrag extends BaseServerFrag<RegistUIOpe, RegistDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().bind.setRegist(getP().getD().getUserBean());
        getP().getU().onTypeClick(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getUserBean().setUsertype((Integer) o);
            }
        });
    }


    @OnClick({R.id.onRegist, R.id.ftv_back})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.onRegist:
                getP().getD().regist(getP().getD().getUserBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if ((Boolean) o) {
                            FragmentUtil2.getInstance().removeTop(activity, R.id.act_base_root);
                        }
                    }
                });
                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTop(activity, R.id.act_base_root);
                break;
        }

    }

}
