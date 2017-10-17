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
import com.android.lib.util.NullUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.ToastUtil;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.MainAct;
import com.siweisoft.service.ui.user.regist.RegistFrag;
import com.siweisoft.service.ui.user.resetpwd.ReSetPwdFrag;

import butterknife.OnClick;

public class LoginFrag extends BaseServerFrag<LoginUIOpe, LoginDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        FragmentUtil2.getInstance().print();

        super.onViewCreated(view, savedInstanceState);
        getP().getU().bind.setLogin(getP().getD().getUserBean());
        getP().getU().initImage(getP().getD().getImageUril());
        getP().getU().bind.etServer.setText(SPUtil.getInstance().init(activity).getStr(Value.DATA_INTENT2));
        if (EMClient.getInstance().isLoggedInBefore()) {
            Value.initNetUrl(activity, SPUtil.getInstance().init(activity).getStr("url-1"));
            EMClient.getInstance().chatManager().loadAllConversations();
            EMClient.getInstance().groupManager().loadAllGroups();
            if (Value.getUserInfo() == null || NullUtil.isStrEmpty(Value.getUserInfo().getPhone())) {
                return;
            }
            Intent intent = new Intent(activity, MainAct.class);
            activity.startActivity(intent);
            activity.finish();
        }
        getP().getU().initIp();
        Value.initNetUrl(activity, getP().getU().bind.etServer.getText().toString());
    }

    @OnClick({R.id.button, R.id.tv_regist, R.id.tv_reset})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (NullUtil.isStrEmpty(getP().getD().getUserBean().getPhone())) {
                    return;
                }
                Value.initNetUrl(activity, getP().getU().bind.etServer.getText().toString());
                getP().getD().login(getP().getD().getUserBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        final BaseResBean res = (BaseResBean) o;
                        if (!res.isException()) {
                            EMClient.getInstance().chatManager().loadAllConversations();
                            EMClient.getInstance().groupManager().loadAllGroups();
                            Value.saveUserInfo((UserBean) res.getData());
                            SPUtil.getInstance().init(activity).saveStr(Value.DATA_INTENT2, UrlConstant.NETSTART);
                            Intent intent = new Intent(activity, MainAct.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.getInstance().showShort(activity, res.getErrorMessage());
                                }
                            });
                        }
                    }
                });
                break;
            case R.id.tv_regist:
                FragmentUtil2.getInstance().add(activity, R.id.act_base_root, new RegistFrag());
                break;
            case R.id.tv_reset:
                FragmentUtil2.getInstance().add(activity, R.id.act_base_root, new ReSetPwdFrag());
                break;
        }
    }
}
