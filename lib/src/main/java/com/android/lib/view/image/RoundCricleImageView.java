package com.android.lib.view.image;

//by summer on 2017-06-27.

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;

public class RoundCricleImageView extends ImageView {
    public RoundCricleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setOutlineProvider(new ViewOutlineProvider() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), ScreenUtil.mw * 4);
                    LogUtil.E("getoutline");
                }
            });
        }
        setClipToOutline(true);
    }
}
