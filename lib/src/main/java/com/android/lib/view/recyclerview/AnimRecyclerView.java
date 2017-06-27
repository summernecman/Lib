package com.android.lib.view.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.android.lib.R;

/**
 * Created by ${viwmox} on 2017-02-16.
 */

public class AnimRecyclerView extends RecyclerView {

    Context context;

    public AnimRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public AnimRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }


    @Override
    public void setAdapter(Adapter adapter) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_push_left_in_250);
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        lac.setDelay(0.5f);//注意这个地方是以秒为单位，是浮点型数据，所以要加f
        setLayoutAnimation(lac);
        super.setAdapter(adapter);
    }

    public void setAnimRes(int id) {
        Animation animation = AnimationUtils.loadAnimation(context, id);
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        lac.setDelay(0.5f);//注意这个地方是以秒为单位，是浮点型数据，所以要加f
        setLayoutAnimation(lac);
    }

    public void setAnimRes(int id, float delay) {
        Animation animation = AnimationUtils.loadAnimation(context, id);
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        lac.setDelay(delay);//注意这个地方是以秒为单位，是浮点型数据，所以要加f
        setLayoutAnimation(lac);
    }
}
