package com.summer.lib.util;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.summer.lib.R;
import com.summer.lib.constant.ValueConstant;

/**
 * Created by ${viwmox} on 2016-06-15.
 */
public class ScreenUtil {
    private static ScreenUtil instance;

    public static float w;

    public static float mw;

    public static float h;

    public static float sbh;

    public static ScreenUtil getInstance() {
        if (instance == null) {
            instance = new ScreenUtil();
        }
        return instance;
    }

    public int[] getScreenSize(Context context) {
        int[] size = new int[]{context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels};
        w = size[0];
        h = size[1];
        mw = context.getResources().getDimension(R.dimen.dimen_1);
        return size;
    }

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

    public float getStatusBarHeight(Activity activity) {
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
