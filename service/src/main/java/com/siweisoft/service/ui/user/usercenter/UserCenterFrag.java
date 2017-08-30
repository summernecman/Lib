package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.setting.aboutus.AboutUsFrag;
import com.siweisoft.service.ui.setting.feedback.FeedBackFrag;
import com.siweisoft.service.ui.setting.frag_account.AccountFrag;
import com.siweisoft.service.ui.setting.frag_collect.CollecFrag;
import com.siweisoft.service.ui.setting.remarklist.RemarkListFrag;
import com.siweisoft.service.ui.user.userheadname.UserHeadNameFrag;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import butterknife.OnClick;

public class UserCenterFrag extends BaseUIFrag<UserCenterUIOpe, UserCenterDAOpe> {
    @Override
    public void doThing() {

        getP().getU().initTips(getP().getD().userInfoDAOpe.getData());
    }

    @Override
    public void onStart() {
        super.onStart();
        getP().getU().initHead();
    }


    @OnClick({R.id.logout, R.id.ll_head, R.id.ll_remark, R.id.ll_collect, R.id.ll_account, R.id.ll_feedback, R.id.aboutus})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new UserHeadNameFrag());
                break;
            case R.id.logout:
                getP().getD().getUserI().loginOut(Value.userBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                        if (FragmentUtil2.fragMap.get(Value.getNowRoot()).size() == 0) {
                            ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
                            ChatInit.getInstance().doLoginOut();
                            ((ServieApp) activity.getApplication()).exit();
                        }
                    }
                });
                break;
            case R.id.ll_remark:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new RemarkListFrag());
                break;
            case R.id.ll_collect:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new CollecFrag());
                break;
            case R.id.ll_account:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new AccountFrag());
                break;
            case R.id.ll_feedback:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new FeedBackFrag());
                break;
            case R.id.aboutus:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new AboutUsFrag());
                break;
        }
    }

}
