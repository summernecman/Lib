package com.siweisoft.service.ui.user.regist;

//by summer on 2017-07-10.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnLoadingInterf;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
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


    @OnClick({R.id.onRegist, R.id.ftv_back, R.id.tv_getcode})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.onRegist:
                if (!getP().getU().vevify()) {
                    ToastUtil.getInstance().showShort(activity, "信息未填写完整");
                    return;
                }
                getP().getD().checkCode(getP().getU().bind.etAccount.getText().toString(), getP().getU().bind.etCode.getText().toString(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if ((Boolean) o) {
                            getP().getD().regist(getP().getD().getUserBean(), new OnFinishListener() {
                                @Override
                                public void onFinish(Object o) {
                                    if (o instanceof Boolean) {
                                        ToastUtil.getInstance().showShort(activity, "注册成功");
                                        FragmentUtil2.getInstance().removeTop(activity, R.id.act_base_root);
                                    } else {
                                        ToastUtil.getInstance().showShort(activity, StringUtil.getStr(o));
                                    }
                                }
                            });
                        } else {
                            ToastUtil.getInstance().showShort(activity, "短信验证码失败");
                        }
                    }
                });
                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTop(activity, R.id.act_base_root);
                break;
            case R.id.tv_getcode:
                getP().getU().bind.tvGetcode.setEnabled(false);
                getP().getD().getThreadUtil().run(1000, new OnLoadingInterf() {
                    @Override
                    public Void onStarLoading(Object o) {
                        getP().getU().bind.tvGetcode.setText((60 - (int) o) + "s");
                        if ((60 - (int) o) <= 0) {
                            getP().getU().bind.tvGetcode.setEnabled(true);
                            getP().getD().getThreadUtil().stop();
                            getP().getU().bind.tvGetcode.setText("重新获取验证码");
                        }
                        return null;
                    }

                    @Override
                    public Void onStopLoading(Object o) {
                        return null;
                    }
                });
                getP().getD().sendCode(getP().getU().bind.etAccount.getText().toString(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {

                    }
                });
                break;
        }

    }

}
