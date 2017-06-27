package com.android.lib.view.other;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.android.lib.R;


/**
 * Created by ${viwmox} on 2016-06-01.
 */
public class AppLoadingView extends ImageView {
    public AppLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setBackgroundResource(R.drawable.ic_launcher);
        RotateAnimation animation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.anim_rotate);
        startAnimation(animation);
    }


}
