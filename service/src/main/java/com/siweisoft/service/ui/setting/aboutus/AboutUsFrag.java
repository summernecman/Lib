package com.siweisoft.service.ui.setting.aboutus;

//by summer on 17-08-28.

import android.view.View;

import com.android.lib.util.PackageUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;

import butterknife.OnClick;

public class AboutUsFrag extends BaseServerFrag<AboutUsUIOpe, AboutUsDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "关于我们", ""));
        getP().getU().initVersion(PackageUtil.getAppVersionName(activity));
    }

    @OnClick({R.id.ll_remarkme, R.id.ll_question, R.id.ll_function})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            default:
                ToastUtil.getInstance().showShort(activity, "功能还在完善中");
                break;
        }
    }
}
