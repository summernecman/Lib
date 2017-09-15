package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.NullUtil;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.MainAct;

import butterknife.OnClick;

public class LoginFrag extends BaseServerFrag<LoginUIOpe, LoginDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().bind.setLogin(getP().getD().getUserBean());
        getP().getU().initImage(getP().getD().getImageUril());
    }

    @OnClick({R.id.button})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (NullUtil.isStrEmpty(getP().getD().getUserBean().getPhone())) {
                    return;
                }
                getP().getD().login(getP().getD().getUserBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        BaseResBean res = (BaseResBean) o;
                        if (!res.isException()) {
                            EMClient.getInstance().chatManager().loadAllConversations();
                            EMClient.getInstance().groupManager().loadAllGroups();
                            Value.userBean = (UserBean) res.getData();
                            Intent intent = new Intent(activity, MainAct.class);
                            activity.startActivity(intent);
                        } else {
                            getP().getU().showErrorMsg(res.getErrorMessage());
                        }
                    }
                });
                break;
        }
    }
}
