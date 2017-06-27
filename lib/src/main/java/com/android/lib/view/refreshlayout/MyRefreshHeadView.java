package com.android.lib.view.refreshlayout;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.util.thread.ThreadUtil;

/**
 * Created by ${viwmox} on 2017-02-23.
 */

public class MyRefreshHeadView extends RelativeLayout implements MaterialHeadListener {

    Context context;

    TextView txtTV;

    int color;

    View view;
    int[] colors = new int[]{Color.RED, Color.BLUE, Color.YELLOW, Color.MAGENTA};
    ThreadUtil threadUtil = null;

    public MyRefreshHeadView(Context context) {
        super(context);
        init(context);
    }


    public MyRefreshHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        view = LayoutInflater.from(context).inflate(R.layout.layout_refresh_head, null);
        addView(view, params);
        txtTV = (TextView) findViewById(R.id.tv_txt);
    }

    @Override
    public void onComlete(MaterialRefreshLayout br) {
        txtTV.setText("刷新成功");
        //threadUtil.stop();
//        txtTV.getLayoutParams().height = 0;
//        txtTV.requestLayout();
//        ValueAnimator animator =ValueAnimator.ofInt(DefaulHeadHeight,0);
//        animator.setDuration(200);
//        animator.setInterpolator(new DecelerateInterpolator());
//        animator.start();
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int value = (int) animation.getAnimatedValue();
//                txtTV.getLayoutParams().height = value;
//                txtTV.requestLayout();
//            }
//        });
    }

    @Override
    public void onBegin(MaterialRefreshLayout br) {
        txtTV.setText("下拉刷新");
    }

    @Override
    public void onPull(MaterialRefreshLayout br, float fraction) {
        //txtTV.getLayoutParams().height = (int) (Util.dip2px(getContext(), DefaulHeadHeight) * Util.limitValue(1, fraction));
        //txtTV.requestLayout();
        // txtTV.setText("下拉刷新");
    }

    @Override
    public void onRelease(MaterialRefreshLayout br, float fraction) {
        txtTV.setText("放开刷新");
    }

    @Override
    public void onRefreshing(MaterialRefreshLayout br) {
        final String s = "正在刷新";
        final SpannableStringBuilder builder = new SpannableStringBuilder("正在刷新");
//        threadUtil = new ThreadUtil();
//        threadUtil.run(300, new OnLoadingInterf() {
//            @Override
//            public Void onStarLoading(Object o) {
//                Integer i= (Integer) o;
//                builder.setSpan(new ForegroundColorSpan(colors[(i)%colors.length]),0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                builder.setSpan(new ForegroundColorSpan(colors[(i+1)%colors.length]),1,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                builder.setSpan(new ForegroundColorSpan(colors[(i+2)%colors.length]),2,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                builder.setSpan(new ForegroundColorSpan(colors[(i+3)%colors.length]),3,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                txtTV.setText(builder);
//                return null;
//            }
//
//            @Override
//            public Void onStopLoading(Object o) {
//                return null;
//            }
//        });
        txtTV.setText(s);
        // AnimUtil.getInstance().startAnim(context,txtTV,R.anim.anim_rotate);
//        LogUtil.E("onRefreshing");
//        txtTV.getLayoutParams().height = (int) (Util.dip2px(getContext(), DefaulHeadHeight));
//        txtTV.requestLayout();
//        ValueAnimator animator = ValueAnimator.ofInt(getHeight(),0);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Log.i("anim", "value--->" + (int) animation.getAnimatedValue());
//                txtTV.getLayoutParams().height =((int) animation.getAnimatedValue());
//                txtTV.requestLayout();
//            }
//        });
//        animator.setInterpolator(new BounceInterpolator());
//        animator.setDuration(200);
//        animator.start();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        setBackgroundColor(Color.RED);
        this.color = color;
    }
}
