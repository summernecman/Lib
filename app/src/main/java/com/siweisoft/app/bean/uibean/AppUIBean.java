package com.siweisoft.app.bean.uibean;

//by summer on 2017-03-31.

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siweisoft.app.R;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.util.ScreenUtil;

import butterknife.BindView;

public class AppUIBean extends BaseUIBean {


    @BindView(R.id.appicon)
    ImageView appicon;
    @BindView(R.id.approot)
    View approot;
    @BindView(R.id.tv_appname)
    TextView tvAppname;

    public AppUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.list_main);
//        appicon.getLayoutParams().width = ScreenUtil.getInstance().getScreenSize(context)[0] / 4;
//        appicon.getLayoutParams().height = appicon.getLayoutParams().width;
//        approot.requestLayout();
    }


    public ImageView getAppicon() {
        return appicon;
    }

    public TextView getTvAppname() {
        return tvAppname;
    }
}
