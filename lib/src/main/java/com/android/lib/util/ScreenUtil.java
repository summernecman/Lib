package com.android.lib.util;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.android.lib.R;
import com.android.lib.constant.ValueConstant;

/**
 * 屏幕工具类
 */
public class ScreenUtil {
    /**
     * 宽度
     */
    public static float w;
    /**
     * 1dp对应的屏幕px
     */
    public static float mw;
    /**
     * 高度
     */
    public static float h;
    /**
     * 状态栏高度
     */
    public static float sbh;

    private static ScreenUtil instance;

    public static ScreenUtil getInstance() {
        if (instance == null) {
            instance = new ScreenUtil();
        }
        return instance;
    }

    /**
     * 测量屏幕宽高
     *
     * @param context
     * @return
     */
    public int[] getScreenSize(Context context) {
        int[] size = new int[]{context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels};
        w = size[0];
        h = size[1];
        mw = context.getResources().getDimension(R.dimen.dimen_1);
        return size;
    }

    /**
     * 设置全屏
     *
     * @param activity
     */
    public void setFullScreen(Activity activity) {
        activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public int getIndex(int x, int y, int sx, int sy, int ex, int ey, int num) {
        if (y < sy || y > ey || x < sx || x > ex) {
            return -1;
        }
        return ((x - sx) * num) / (ex - sx);
    }

    /***
     * 获取状态栏高度
     * @param activity
     * @return
     */
    public float getStatusBarHeight(Context activity) {
        sbh = ValueConstant.DIMEN_1 * 20;
        //获取status_bar_height资源的ID
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            sbh = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return sbh;
    }
}
