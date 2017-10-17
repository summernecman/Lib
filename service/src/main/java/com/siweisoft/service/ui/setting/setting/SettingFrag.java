package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.view.View;

import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
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
                if (Value.getRoom() == null) {
                    return;
                }
                LogUtil.E(1);
                EMClient.getInstance().chatroomManager().leaveChatRoom(Value.getRoom().getId());
                LogUtil.E(2);
                EMClient.getInstance().logout(true);
                LogUtil.E(3);
                //FragmentUtil2.getInstance().initClear(activity);
                LogUtil.E(4);
                FragmentUtil2.getInstance().clear();
                LogUtil.E(5);
                ((ServieApp) activity.getApplication()).exit();
                LogUtil.E(6);
                break;
        }

    }
}
