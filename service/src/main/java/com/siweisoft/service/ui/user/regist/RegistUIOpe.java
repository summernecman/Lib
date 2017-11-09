package com.siweisoft.service.ui.user.regist;

//by summer on 2017-07-10.

import android.content.Context;
import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.NullUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.databinding.FragRegistBinding;

public class RegistUIOpe extends BaseUIOpe<FragRegistBinding> {


    public RegistUIOpe(Context context) {
        super(context);
        bind.tophead.setTitle2(new TitleBean("返回", "注册", ""));
    }

    public void onTypeClick(final OnFinishListener onFinishListener) {
        bind.radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_server:
                        onFinishListener.onFinish(0);
                        break;
                    case R.id.rb_customer:
                        onFinishListener.onFinish(2);
                        break;
                    case R.id.rb_engineer:
                        onFinishListener.onFinish(1);
                        break;
                }
            }
        });
    }

    public boolean vevify() {
        return !NullUtil.isStrEmpty(bind.etAccount.getText().toString()) && !NullUtil.isStrEmpty(bind.etCode.getText().toString()) && !NullUtil.isStrEmpty(bind.etPwd.getText().toString());
    }


    public boolean vevifyPhone() {
        return !NullUtil.isStrEmpty(bind.etAccount.getText().toString());
    }
}
