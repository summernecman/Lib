package com.siweisoft.app.main;

//by summer on 2017-03-31.

import android.app.WallpaperManager;
import android.os.Build;
import android.os.Bundle;

import com.siweisoft.app.R;
import com.siweisoft.app.base.AppActivity;
import com.siweisoft.app.home.HomeFrag;
import com.summer.lib.base.ope.BaseOpes;

public class MainActivity extends AppActivity<MainUIOpe,MainDAOpe>{

    @Override
    public BaseOpes<MainUIOpe, MainDAOpe> createOpes() {
        return new BaseOpes<>(new MainUIOpe(activity),new MainDAOpe(activity));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ROOTVG.setBackground(WallpaperManager.getInstance(activity).getDrawable());
        }else{
            ROOTVG.setBackgroundDrawable(WallpaperManager.getInstance(activity).getDrawable());
        }
        getSupportFragmentManager().beginTransaction().add(R.id.ll_base_root,new HomeFrag()).commit();
    }
}
