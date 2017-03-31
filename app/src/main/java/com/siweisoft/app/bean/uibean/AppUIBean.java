package com.siweisoft.app.bean.uibean;

//by summer on 2017-03-31.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.siweisoft.app.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class AppUIBean extends BaseUIBean {


    @BindView(R.id.appicon)
    ImageView appicon;

    public AppUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.list_main);
    }

    public ImageView getAppicon() {
        return appicon;
    }
}
