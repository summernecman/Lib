package com.summer.lib.base.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.summer.lib.R;
import com.summer.lib.base.interf.uiinterf.IUICreate;
import com.summer.lib.base.ope.BaseDAOpe;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.constant.color.ColorConstant;
import com.summer.lib.util.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by summer on 2016/4/16 0016 11:51.
 */
public abstract class BaseUIActivity<A extends BaseUIOpe,B extends BaseDAOpe> extends BaseActivity {

    /**
     * 添加内容界面的容器
     */
    protected ViewGroup ROOTVG;

    protected Gson gson = new Gson();

    BaseOpes<A,B> opes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFullScreen(isFullScreen());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.layout_baseui_withouttitle);
        StatusBarUtil.getInstance().setStatusBarColorResId(activity, ColorConstant.COLOR_STATUS);
        ROOTVG = (ViewGroup) findViewById(R.id.ll_base_root);
        if(getOpes().getUiOpe()!=null && getOpes().getUiOpe().getUiBean().itemView!=null){
            ROOTVG.addView(getOpes().getUiOpe().getUiBean().itemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ButterKnife.bind(activity);
    }

    public BaseOpes<A, B> getOpes() {
        if(opes==null){
            opes = createOpes();
        }
        return opes;
    }

    public abstract BaseOpes<A, B>  createOpes();

    public boolean isFullScreen() {
        return false;
    }

    private void isFullScreen(boolean is) {
        if (is) {
            // 隐藏标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // 隐藏状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    public ViewGroup getROOTVG() {
        return ROOTVG;
    }
}
