package com.android.lib.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.view.bottomdialogmenuview.BottomDialogMenuView;

//import com.siweisoft.imga.R;

/**
 * 底部弹出框菜单
 */
public class SheetDialogUtil {

    private static SheetDialogUtil instance;
    private AlertDialog dialog;

    public static SheetDialogUtil getInstance() {
        if (instance == null) {
            instance = new SheetDialogUtil();
        }
        return instance;
    }

    /**
     * 弹出菜单
     *
     * @param activity
     * @param view
     * @param onClickListener
     */
    public void showBottomSheet(Context activity, View view, View.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        dialog.getWindow().getAttributes().width = activity.getResources().getDisplayMetrics().widthPixels;
        dialog.getWindow().setWindowAnimations(R.style.sheetstyle);
        dialog.getWindow().setContentView(view);
//        ViewGroup.LayoutParams params = view.getLayoutParams();
//        params.width = activity.getResources().getDisplayMetrics().widthPixels;
//        view.setLayoutParams(params);
        ViewGroup viewGroup = (ViewGroup) (view.findViewById(R.id.root));
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            ViewGroup group = (ViewGroup) viewGroup.getChildAt(i);
            if (group.getChildAt(0) instanceof TextView) {
                TextView textView = (TextView) group.getChildAt(0);
//                LogUtil.E(textView.getText());
                textView.setTag(R.id.position, i);
                textView.setOnClickListener(onClickListener);
            }
        }
        if (view instanceof BottomDialogMenuView) {
            BottomDialogMenuView bottomDialogMenuView = (BottomDialogMenuView) view;
            bottomDialogMenuView.getSweetView().show();
        }
    }

    /**
     * 弹出菜单
     *
     * @param activity
     * @param view
     * @param style
     * @param onClickListener
     */
    public void showBottomSheet(Context activity, View view, int style, View.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        dialog.getWindow().getAttributes().width = activity.getResources().getDisplayMetrics().widthPixels;
        dialog.getWindow().setWindowAnimations(style);
        dialog.getWindow().setContentView(view);
//        ViewGroup.LayoutParams params = view.getLayoutParams();
//        params.width = activity.getResources().getDisplayMetrics().widthPixels;
//        view.setLayoutParams(params);
        ViewGroup viewGroup = (ViewGroup) (view.findViewById(R.id.root));
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            ViewGroup group = (ViewGroup) viewGroup.getChildAt(i);
            if (group.getChildAt(0) instanceof TextView) {
                TextView textView = (TextView) group.getChildAt(0);
//                LogUtil.E(textView.getText());
                textView.setOnClickListener(onClickListener);
            }
        }

        if (view instanceof BottomDialogMenuView) {
            BottomDialogMenuView bottomDialogMenuView = (BottomDialogMenuView) view;
            bottomDialogMenuView.getSweetView().show();
        }
    }

    /**
     * 弹出菜单
     *
     * @param activity
     * @param view
     */
    public void showBottomSheet(Context activity, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        dialog.getWindow().getAttributes().width = activity.getResources().getDisplayMetrics().widthPixels;
        dialog.getWindow().setWindowAnimations(R.style.sheetstyle_more);
        dialog.getWindow().setContentView(view);
//        ViewGroup.LayoutParams params = view.getLayoutParams();
//        params.width = activity.getResources().getDisplayMetrics().widthPixels;
//        view.setLayoutParams(params);
    }

    /**
     * 取消弹出菜单
     */
    public void dismess() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
        }
    }
}
