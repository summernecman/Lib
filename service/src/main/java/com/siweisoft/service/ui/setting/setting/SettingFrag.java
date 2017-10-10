package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.ui.Constant.Value;

import butterknife.OnClick;

public class SettingFrag extends BaseServerFrag<SettingUIOpe, SettingDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "设置", ""));
    }
    @OnClick({R.id.ll_set})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_set:
                if (Value.room == null) {
                    return;
                }
                EMClient.getInstance().chatroomManager().leaveChatRoom(Value.room.getId());
                getP().getD().userI.loginOut(Value.userBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        EMClient.getInstance().logout(true);
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                        FragmentUtil2.getInstance().clear();
                        FragmentUtil2.getInstance().initClear(activity);
                        ((ServieApp) activity.getApplication()).exit();
                    }
                });
                break;
        }

    }
}
