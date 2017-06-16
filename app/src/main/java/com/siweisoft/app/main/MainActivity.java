package com.siweisoft.app.main;

//by summer on 2017-03-31.

import android.app.WallpaperManager;
import android.os.Build;
import android.os.Bundle;

import com.siweisoft.app.R;
import com.siweisoft.app.base.AppActivity;
import com.siweisoft.app.home.HomeFrag;

public class MainActivity extends AppActivity<MainUIOpe,MainDAOpe>{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ACT_ROOT_VIEW.setBackground(WallpaperManager.getInstance(activity).getDrawable());
        }else{
            ACT_ROOT_VIEW.setBackgroundDrawable(WallpaperManager.getInstance(activity).getDrawable());
        }
        getSupportFragmentManager().beginTransaction().add(R.id.act_base_root, new HomeFrag()).commit();

    }
}
