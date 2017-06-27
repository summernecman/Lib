package com.android.lib.util.menu.popup;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.PopupWindow;

import com.android.lib.R;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.ScreenUtil;

/**
 * Created by ${viwmox} on 2016-11-14.
 */
public class PopupUtil {

    public static final int LEFT = 0;
    public static final int MID = 1;
    public static final int RIGHT = 2;
    private static PopupUtil instance;
    private PopupWindow popupWindow;

    public static PopupUtil getInstance() {
        if (instance == null) {
            instance = new PopupUtil();
        }
        return instance;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void show(Context context, View view, View archerview, int type) {
        ScreenUtil.getInstance().getScreenSize(context);
        popupWindow = new PopupWindow(context);
        popupWindow.setHeight((int) (ScreenUtil.w / 2));
        popupWindow.setWidth(102 * ValueConstant.DIMEN_1);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popanimstyle);
//        popupWindow.getBackground().setAlpha(125);
        popupWindow.setContentView(view);
//        if(context instanceof BaseActivity){
//            final BaseActivity activity = (BaseActivity) context;
//            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//            lp.alpha=0.7f;
//            activity.getWindow().setAttributes(lp);
//            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//                    lp.alpha=1f;
//                    activity.getWindow().setAttributes(lp);
//                }
//            });
//        }
        int xoff = (archerview.getWidth() - popupWindow.getWidth()) / 2;
//        switch (type){
//            case LEFT:
//                xoff+=ValueConstant.DIMEN_1*10;
//                break;
//            case MID:
//                break;
//            case RIGHT:
//                xoff-=ValueConstant.DIMEN_1*10;
//                break;
//        }
//        popupWindow.setElevation(ValueConstant.DIMEN_1*10);
//        popupWindow.showAtLocation(archerview,Gravity.CENTER,0,0);
        popupWindow.showAsDropDown(archerview, xoff, -0);
    }

    public void show(Context context, View view, View archerview) {
        ScreenUtil.getInstance().getScreenSize(context);
        popupWindow = new PopupWindow(context);
        popupWindow.setHeight((int) (ScreenUtil.w / 2));
        popupWindow.setWidth(102 * ValueConstant.DIMEN_1);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popanimstyle);
//        popupWindow.getBackground().setAlpha(125);
        popupWindow.setContentView(view);
//        if(context instanceof BaseActivity){
//            final BaseActivity activity = (BaseActivity) context;
//            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//            lp.alpha=0.7f;
//            activity.getWindow().setAttributes(lp);
//            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//                    lp.alpha=1f;
//                    activity.getWindow().setAttributes(lp);
//                }
//            });
//        }
        int xoff = (archerview.getWidth() - popupWindow.getWidth()) / 2;
        popupWindow.showAsDropDown(archerview, xoff, 0);
    }

    public void dimess() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
