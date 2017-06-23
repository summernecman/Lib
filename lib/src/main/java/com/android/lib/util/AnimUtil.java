package com.android.lib.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.android.lib.base.interf.OnFinishListener;


/**
 * Created by ${viwmox} on 2016-06-02.
 */
public class AnimUtil {

    private static AnimUtil instance;

    private AnimUtil() {

    }

    public static AnimUtil getInstance() {
        if (instance == null) {
            instance = new AnimUtil();
        }
        return instance;
    }

    public void animDown(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0, view.getHeight()).setDuration(500);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animDownUp(final View view, final ViewGroup.LayoutParams params, int... ints) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(ints);
        valueAnimator.setDuration(500);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int f = (int) animation.getAnimatedValue();
                params.height = f;
                view.setLayoutParams(params);
            }
        });
        valueAnimator.start();
    }

    public void animUp(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", view.getHeight(), 0).setDuration(500);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animRotaionLeft(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 120, 90).setDuration(500);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animRotaionRight(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, -120, -90).setDuration(500);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animRotaionReset(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 0).setDuration(500);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animIn(View view, final OnFinishListener onFinishListener) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(3000);
        animatorSet.playTogether(animator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                onFinishListener.onFinish(animation);
            }
        });
        animatorSet.start();
    }


    public void animX(View view, float... x) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", x).setDuration(500);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animY(View view, float... y) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", y).setDuration(500);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animY(int duration, View view, float... y) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", y).setDuration(duration);
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void animY(int duration, final View view, final OnFinishListener onFinishListener, float... y) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", y).setDuration(duration);
        animatorSet.playTogether(animator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (onFinishListener != null) {
                    onFinishListener.onFinish(view);
                }
            }
        });
        animatorSet.start();
    }


    public void animMarginTop(int duration, final View view, final OnFinishListener onFinishListener, int... y) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(y);
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int f = (int) animation.getAnimatedValue();
                params.topMargin = f;
                view.setLayoutParams(params);
            }

        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (onFinishListener != null) {
                    onFinishListener.onFinish(view);
                }
            }
        });
        valueAnimator.start();
    }


    public void animXY(View view, float startx, float endx, float starty, float endy, final OnFinishListener onFinishListener) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatory = ObjectAnimator.ofFloat(view, "translationY", starty, endy).setDuration(500);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(view, "translationX", startx, endx).setDuration(500);
        animatorSet.playTogether(animatorx, animatory);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                onFinishListener.onFinish(animation);
            }
        });
        animatorSet.start();
    }

    public void startAnim(Context context, View view, int aimres) {
        view.startAnimation(AnimationUtils.loadAnimation(context, aimres));
    }

    public void startAnim(Context context, View view, int aimres, final OnFinishListener onFinishListener) {
        Animation animation = AnimationUtils.loadAnimation(context, aimres);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void startAnim(Context context, View view, int aimres, final Animation.AnimationListener listener) {
        Animation animation = AnimationUtils.loadAnimation(context, aimres);
        view.startAnimation(animation);
        animation.setAnimationListener(listener);
    }
}
