package com.siweisoft.app;

//by summer on 2017-03-31.

import android.os.Bundle;

import com.siweisoft.app.base.AppActivity;
import com.siweisoft.app.bean.uibean.MainUIBean;
import com.siweisoft.app.main.MainFrag;

public class MainActivity extends AppActivity<MainUIBean> {

    @Override
    public MainUIBean createUI() {
        return new MainUIBean(activity,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_base_root,new MainFrag()).commit();
    }
}
