package com.android.lib.util;

//import com.siweisoft.imga.R;
//import com.victor.loading.rotate.RotateLoading;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;

import com.android.lib.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-05-10.
 */
public class LoadUtil {
    private static final String[] INDICATORS = new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };
    private static LoadUtil instance;
    ArrayList<MyDialog> dialogs = new ArrayList<>();

    public static LoadUtil getInstance() {
        if (instance == null) {
            instance = new LoadUtil();
        }
        return instance;
    }

    public void onStartLoading(Context activity, String tag) {
        MyDialog dialog = new MyDialog(activity, R.style.swdialog);
        dialogs.add(dialog);
        dialog.setTag(tag);
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().getAttributes().alpha = 1;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.setContentView(R.layout.dialog_loading);
        AVLoadingIndicatorView imageView;
        imageView = (AVLoadingIndicatorView) dialog.findViewById(R.id.av);
//        imageView.setIndicator("LineScalePulseOutRapidIndicator");
        imageView.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    public void onStopLoading(String tag) {
        for (int i = 0; i < dialogs.size(); i++) {
            if (dialogs.get(i) != null && dialogs.get(i).getTag() != null && dialogs.get(i).getTag().equals(tag)) {
                dialogs.get(i).dismiss();
                dialogs.get(i).cancel();
            }
        }
    }

    public class MyDialog extends Dialog {

        private String tag;

        public MyDialog(Context context) {
            super(context);
        }

        protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        public MyDialog(Context context, int themeResId) {
            super(context, themeResId);
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
