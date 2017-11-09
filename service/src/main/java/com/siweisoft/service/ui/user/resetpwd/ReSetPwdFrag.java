package com.siweisoft.service.ui.user.resetpwd;

//by summer on 2017-07-10.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnLoadingInterf;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LoadUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.user.login.LoginFrag;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class ReSetPwdFrag extends BaseServerFrag<ReSetPwdUIOpe, ReSetPwdDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().bind.setResetpwd(getP().getD().getUserBean());
    }


    @OnClick({R.id.onRegist, R.id.ftv_back, R.id.tv_getcode})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.onRegist:
                if (!getP().getU().vevify()) {
                    ToastUtil.getInstance().showShort(activity, "信息未填写完整");
                    return;
                }
                LoadUtil.getInstance().onStartLoading(activity, "@#$");
                getP().getD().checkCode(getP().getU().bind.etAccount.getText().toString(), getP().getU().bind.etCode.getText().toString(), new OnFinishListener() {
                    @Override
                    public void onFinish(final Object o) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if ((Boolean) o) {
                                    getP().getD().resetPwd(getP().getD().getUserBean(), new OnFinishListener() {
                                        @Override
                                        public void onFinish(final Object o) {
                                            if (o instanceof Boolean) {
                                                ToastUtil.getInstance().showShort(activity, "重置成功");
                                                UserBean userBean = new UserBean();
                                                userBean.setPhone(getP().getU().bind.etAccount.getText().toString());
                                                userBean.setPwd(getP().getU().bind.etPwd.getText().toString());
                                                MessageEvent m = new MessageEvent(ReSetPwdFrag.class.getName(), LoginFrag.class.getName(), userBean);
                                                EventBus.getDefault().post(m);
                                                FragmentUtil2.getInstance().removeTop(activity, R.id.act_base_root);
                                            } else {
                                                activity.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ToastUtil.getInstance().showShort(activity, StringUtil.getStr(o));
                                                    }
                                                });
                                            }
                                            LoadUtil.getInstance().onStopLoading("@#$");
                                        }
                                    });
                                } else {
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ToastUtil.getInstance().showShort(activity, "短信验证码失败");
                                            LoadUtil.getInstance().onStopLoading("@#$");
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTop(activity, R.id.act_base_root);
                break;
            case R.id.tv_getcode:
                if (!getP().getU().vevifyPhone()) {
                    return;
                }
                getP().getU().bind.tvGetcode.setEnabled(false);
                getP().getD().sendCode(getP().getU().bind.etAccount.getText().toString(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {

                    }
                });
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
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getP().getD().getThreadUtil().stop();
    }
}
