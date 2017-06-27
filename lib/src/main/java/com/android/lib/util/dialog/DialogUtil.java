//package com.android.lib.util.dialog;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.support.v4.app.FragmentManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//
//import com.android.lib.R;
//import AVLoadingIndicatorView;
//import TimePickerDialog;
//import Type;
//import OnDateSetListener;
//
//import java.util.HashMap;
//
///**
// * Created by ${viwmox} on 2016-11-30.
// */
//public class DialogUtil {
//
//    private static DialogUtil instance;
//
//    private HashMap<String, AlertDialog> dialogs = new HashMap<>();
//
//    public static DialogUtil getInstance() {
//        if (instance == null) {
//            instance = new DialogUtil();
//        }
//        return instance;
//    }
//
//    public void showDialog(Context context, View view, View.OnClickListener listener, int... id) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setCancelable(false);
//        dialogs.put(System.currentTimeMillis() + "", alertDialog);
////        alertDialog.setView(view);
//        alertDialog.getWindow().setWindowAnimations(R.style.fadein);
//        // alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        alertDialog.show();
//        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        alertDialog.getWindow().setContentView(view);
//
//        for (int i = 0; i < id.length; i++) {
//            view.findViewById(id[i]).setOnClickListener(listener);
//        }
//        show = true;
//    }
//
//    public boolean show = false;
//
//    public boolean isShow() {
//        return show;
//    }
//
//
//    public void showDialogWithTag(Context context, String o, View view, View.OnClickListener listener, int... id) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        AlertDialog alertDialog = builder.create();
//        dialogs.put(o, alertDialog);
//        alertDialog.setCancelable(false);
////        alertDialog.setView(view);
//        alertDialog.getWindow().setWindowAnimations(R.style.fadein);
//        // alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        alertDialog.show();
//        alertDialog.getWindow().setContentView(view);
//        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        for (int i = 0; i < id.length; i++) {
//            view.findViewById(id[i]).setOnClickListener(listener);
//        }
//        show = true;
//    }
//
//
//    public void showLoadDialog(Context context) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        View view = LayoutInflater.sender(context).inflate(R.layout.dialog_loading, null);
//        AVLoadingIndicatorView v = (AVLoadingIndicatorView) view.findViewById(R.id.av);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        builder.setView(view);
//        v.show();
//        show = true;
//    }
//
//    public void dismiss() {
//        String[] longs = dialogs.keySet().toArray(new String[dialogs.size()]);
//        for (int i = 0; i < longs.length; i++) {
//            dialogs.get(longs[i]).dismiss();
//            dialogs.remove(longs[i]);
//        }
//        show = false;
//    }
//
//    public AlertDialog getDialog(String o) {
//        return dialogs.get(o);
//    }
//
//    public static void showTimePick(Context context, FragmentManager fragmentManager, String name, Type type, OnDateSetListener onDateSetListener) {
//        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
//        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
//                .setCallBack(onDateSetListener)
//                .setCancelStringId("取消")
//                .setSureStringId("确定")
//                .setTitleStringId("选择日期")
//                .setYearText("年")
//                .setMonthText("月")
//                .setDayText("日")
//                .setHourText("时")
//                .setMinuteText("分")
//                .setCyclic(false)
//                .setMinMillseconds(System.currentTimeMillis() - tenYears)
//                .setMaxMillseconds(System.currentTimeMillis())
//                .setCurrentMillseconds(System.currentTimeMillis())
//                .setThemeColor(context.getResources().getColor(R.color.color_base_txt_gray))
//                .setType(type)
//                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
//                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.timepicker_toolbar_bg))
//                .setWheelItemTextSize(12)
//                .build();
//        mDialogAll.show(fragmentManager, name);
//    }
//}
