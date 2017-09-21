package com.siweisoft.service.ui.user.login;

//by summer on 17-08-24.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;

public class LoginAct extends BaseUIActivity<BaseUIOpe, BaseDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtil2.getInstance().addNoAnim(activity, R.id.act_base_root, new LoginFrag());
//        FragmentUtil2.getInstance().add(activity, R.id.act_base_root,new WelcomeFrag());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
