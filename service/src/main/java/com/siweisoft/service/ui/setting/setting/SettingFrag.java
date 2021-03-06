package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import butterknife.OnClick;

public class SettingFrag extends BaseServerFrag<SettingUIOpe, SettingDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "设置", ""));
    }
    @OnClick({R.id.ll_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_set:
                getP().getD().userI.loginOut(Value.userBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                        ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
                        ChatInit.getInstance().doLoginOut();
                        ((ServieApp) activity.getApplication()).exit();
                    }
                });
                break;
        }

    }
}
