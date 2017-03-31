package com.siweisoft.app.base;

//by summer on 2017-03-31.

import android.app.WallpaperManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.summer.lib.base.activity.BaseUIActivity;
import com.summer.lib.bean.uibean.BaseUIBean;

public abstract class AppActivity<A extends BaseUIBean> extends BaseUIActivity<A>{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ROOTVG.setBackground(WallpaperManager.getInstance(activity).getDrawable());
        }else{
            ROOTVG.setBackgroundDrawable(WallpaperManager.getInstance(activity).getDrawable());
        }
    }
}
