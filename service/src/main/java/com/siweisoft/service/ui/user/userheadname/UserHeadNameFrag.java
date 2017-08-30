package com.siweisoft.service.ui.user.userheadname;

//by summer on 17-08-30.

import android.content.Intent;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.IntentUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.ui.user.rename.RenameFrag;

import butterknife.OnClick;

public class UserHeadNameFrag extends BaseUIFrag<UserHeadNameUIOpe, UserHeadNameDAOpe> {


    @Override
    public void doThing() {
        getP().getU().initInfo();
    }

    @OnClick({R.id.ll_head, R.id.ll_name})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.ll_head:
                IntentUtil.getInstance().photoShowFromphone(fragment, ValueConstant.CODE_REQUSET3);
                break;
            case R.id.ll_name:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new RenameFrag());
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String url = data.getDataString();
        UserBean userBean = new UserBean();
        userBean.setHeadurl(url);
        getP().getD().setHead(userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                UserBean userBean = (UserBean) o;
                Value.userBean.setHeadurl(userBean.getHeadurl());
                getP().getU().initInfo();
            }
        });

    }
}
