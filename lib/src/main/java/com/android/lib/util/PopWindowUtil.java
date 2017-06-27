package com.android.lib.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.android.lib.R;


/**
 * popup窗口工具
 */
public class PopWindowUtil {

    private static PopWindowUtil instance;

    private PopupWindow popupWindow;

    private PopWindowUtil() {

    }

    public static PopWindowUtil getInstance() {
        if (instance == null) {
            instance = new PopWindowUtil();
        }
        return instance;
    }

    /**
     * 显示窗口
     *
     * @param context
     * @param view
     * @param parent
     * @return
     */
    public View showWindow(Context context, View view, View parent) {


        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);

        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(android.R.color.transparent));
        popupWindow.setAnimationStyle(R.style.popanimstyle);
        //popupWindow.showAsDropDown(parent,(int)( ScreenUtil.getInstance().getScreenSize(context)[0]-context.getResources().getDimension(R.dimen.dimen_120)-context.getResources().getDimension(R.dimen.dimen_1)), 0);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        return view;
    }

    /**
     * 显示窗口
     *
     * @param context
     * @param view
     * @param parent
     * @param onClickListener
     * @return
     */
    public View showWindow(Context context, View view, View parent, View.OnClickListener onClickListener) {
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);

        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(android.R.color.transparent));
        popupWindow.setAnimationStyle(R.style.popanimstyle);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        //popupWindow.showAsDropDown(parent,(int)( ScreenUtil.getInstance().getScreenSize(context)[0]-context.getResources().getDimension(R.dimen.dimen_120)-context.getResources().getDimension(R.dimen.dimen_1)), 0);
        ViewGroup viewGroup = (ViewGroup) (view.findViewById(R.id.root));
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(onClickListener);
        }
        return view;
    }

    /**
     * 取消窗口
     */
    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
