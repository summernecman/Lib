package com.summer.time.ui.main.thingview;

//by summer on 2017-11-23.

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;
import com.summer.time.BR;
import com.summer.time.R;

import java.util.ArrayList;

public class ThingView extends RecyclerView {

    double h;
    double hh;

    private int pos = 0;

    private OnFinishListener onScroll;

    public ThingView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ThingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ThingView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    protected void init(Context context, @Nullable AttributeSet attrs, int defStyle) {
        setLayoutManager(new LinearLayoutManager(context));
    }

    public void initData() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            strings.add("gjr43[qngh9tu4nv发的发生发的服务vgvdwgqi[rrrvjdkjfdkf1");
        }
        h = ScreenUtil.h - 104 * ScreenUtil.mw;
        hh = h * h;
        LogUtil.E("hhhhh" + h);
        setAdapter(new AppsDataBindingAdapter(getContext(), R.layout.item_thing, BR.abc, strings) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setTag(R.id.data, position);
                //holder.itemView.setScaleX((float) getScale(holder.itemView));
            }
        });
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (int i = 0; i < recyclerView.getLayoutManager().getChildCount(); i++) {
                    recyclerView.getLayoutManager().getChildAt(i).setScaleX((float) getScale(recyclerView.getLayoutManager().getChildAt(i)));

                    if (isIn(recyclerView.getLayoutManager().getChildAt(i))) {
                        recyclerView.getLayoutManager().getChildAt(i).setSelected(true);
                        LogUtil.E(pos + "::::" + recyclerView.getLayoutManager().getChildAt(i).getTag(R.id.data));
                        if (onScroll != null && pos != ((int) recyclerView.getLayoutManager().getChildAt(i).getTag(R.id.data))) {
                            pos = ((int) recyclerView.getLayoutManager().getChildAt(i).getTag(R.id.data));
                            onScroll.onFinish(recyclerView.getLayoutManager().getChildAt(i));
                        }
                    } else {
                        recyclerView.getLayoutManager().getChildAt(i).setSelected(false);
                    }
                }
            }
        });
    }

    public double getScale(View v) {//根号 1- (2x-h)*(2x-h)/(h*h)
        double i = (v.getTop() + v.getBottom()) / 2;
        if (i >= h) {
            i = h;
        }
        double d = Math.sqrt(1 - ((double) ((2 * i - h) * (2 * i - h))) / (hh));
        return d * d;
    }

    public boolean isIn(View v) {
        if (v.getTop() < getHeight() / 2 && v.getBottom() > getHeight() / 2) {
            return true;
        }
        return false;
    }

    public void setOnScroll(OnFinishListener onScroll) {
        this.onScroll = onScroll;
    }
}
