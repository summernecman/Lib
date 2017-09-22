package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.UrlConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.SPUtil;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.MainAct;
import com.siweisoft.service.ui.user.regist.RegistFrag;

import butterknife.OnClick;

public class LoginFrag extends BaseServerFrag<LoginUIOpe, LoginDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().bind.setLogin(getP().getD().getUserBean());
        getP().getU().initImage(getP().getD().getImageUril());
        getP().getU().bind.etServer.setText(SPUtil.getInstance().init(activity).getStr(Value.DATA_INTENT2));
        if (EMClient.getInstance().isLoggedInBefore()) {
            EMClient.getInstance().chatManager().loadAllConversations();
            EMClient.getInstance().groupManager().loadAllGroups();
            Value.userBean = GsonUtil.getInstance().fromJson(SPUtil.getInstance().init(activity).getStr(Value.DATA_DATA), UserBean.class);
            if (Value.userBean == null || NullUtil.isStrEmpty(Value.userBean.getPhone())) {
                return;
            }
            Intent intent = new Intent(activity, MainAct.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    @OnClick({R.id.button, R.id.tv_regist})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (NullUtil.isStrEmpty(getP().getD().getUserBean().getPhone())) {
                    return;
                }

                UrlConstant.NETSTART = getP().getU().bind.etServer.getText().toString();
                getP().getD().login(getP().getD().getUserBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        BaseResBean res = (BaseResBean) o;
                        if (!res.isException()) {
                            EMClient.getInstance().chatManager().loadAllConversations();
                            EMClient.getInstance().groupManager().loadAllGroups();
                            Value.userBean = (UserBean) res.getData();
                            SPUtil.getInstance().init(activity).saveStr(Value.DATA_DATA, GsonUtil.getInstance().toJson(Value.userBean));
                            SPUtil.getInstance().init(activity).saveStr(Value.DATA_INTENT2, UrlConstant.NETSTART);
                            Intent intent = new Intent(activity, MainAct.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {
                            getP().getU().showErrorMsg(res.getErrorMessage());
                        }
                    }
                });
                break;
            case R.id.tv_regist:
                FragmentUtil2.getInstance().add(activity, R.id.act_base_root, new RegistFrag());
                break;
        }
    }
}
