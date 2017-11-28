package com.summer.time.ui.main.thingview;

//by summer on 2017-11-23.

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.ScreenUtil;
import com.summer.time.BR;
import com.summer.time.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ThingView extends RecyclerView {

    double h;
    double hh;

    private int pos = 0;

    private OnFinishListener onScroll;

    public static int maxcount;

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
        h = ScreenUtil.h - 104 * ScreenUtil.mw;
        hh = h * h;
    }

    public void initData(final ArrayList<ThingBean> thingBeans, ViewListener listener) {
        setAdapter(new AppsDataBindingAdapter(getContext(), R.layout.item_thing, BR.abc, thingBeans, listener) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                ViewDataBinding viewDataBinding = holder.viewDataBinding;
                viewDataBinding.getRoot().setTag(R.id.data, list.get(position % thingBeans.size()));
                viewDataBinding.getRoot().setTag(R.id.position, position % thingBeans.size());
                viewDataBinding.getRoot().setOnClickListener(this);
                viewDataBinding.getRoot().setOnLongClickListener(this);
                viewDataBinding.setVariable(vari, list.get(position % thingBeans.size()));
                holder.itemView.setTag(R.id.position, position % thingBeans.size());
                holder.itemView.setTag(R.id.data, thingBeans.get(position % thingBeans.size()));
//                holder.itemView.getLayoutParams().height = (int) (thingBeans.get(position%thingBeans.size()).getHeight() * ScreenUtil.mw);
//                holder.itemView.requestLayout();
                viewDataBinding.executePendingBindings();//加一行，问题解决
                //holder.itemView.setScaleX((float) getScale(holder.itemView));
            }

            @Override
            public int getItemCount() {
                return Integer.MAX_VALUE >> 2;
            }
        });
        //new PagerSnapHelper().attachToRecyclerView(this);
        scrollToPosition(getAdapter().getItemCount() * 1000);
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                maxcount = recyclerView.getLayoutManager().getChildCount();
                for (int i = 0; i < recyclerView.getLayoutManager().getChildCount(); i++) {
                    recyclerView.getLayoutManager().getChildAt(i).setScaleX((float) getScale(recyclerView.getLayoutManager().getChildAt(i)));

                    if (isIn(recyclerView.getLayoutManager().getChildAt(i))) {
                        recyclerView.getLayoutManager().getChildAt(i).setSelected(true);
                        //LogUtil.E(pos + "::::" + recyclerView.getLayoutManager().getChildAt(i).getTag(R.id.data));
                        if (onScroll != null && pos != ((int) recyclerView.getLayoutManager().getChildAt(i).getTag(R.id.position))) {
                            pos = ((int) recyclerView.getLayoutManager().getChildAt(i).getTag(R.id.position));
                            onScroll.onFinish(recyclerView.getLayoutManager().getChildAt(i));
                        }
                    } else {
                        recyclerView.getLayoutManager().getChildAt(i).setSelected(false);
                    }
                    if (isNow((ThingBean) recyclerView.getLayoutManager().getChildAt(i).getTag(R.id.data))) {
                        recyclerView.getLayoutManager().getChildAt(i).setSelected(true);
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

    public boolean isNow(ThingBean thingBean) {
        int s = thingBean.getTimeArea().getStart().hour * 60 + thingBean.getTimeArea().getStart().minute;
        int e = thingBean.getTimeArea().getEnd().hour * 60 + thingBean.getTimeArea().getEnd().minute;
        int n = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 + Calendar.getInstance().get(Calendar.MINUTE);
        if (n >= s && n <= e) {
            return true;
        }
        return false;
    }

    public void setOnScroll(OnFinishListener onScroll) {
        this.onScroll = onScroll;
    }
}
