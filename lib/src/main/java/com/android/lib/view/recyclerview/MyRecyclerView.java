package com.android.lib.view.recyclerview;

//by summer on 17-10-18.

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.android.lib.util.LogUtil;

public class MyRecyclerView extends RecyclerView {


    public interface OnScroll {
        public void onScrollToEnd(MyRecyclerView myRecyclerView);
    }

    private OnScroll onScroll;

    private OnScrollListener onScrollListener;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //找到数组中的最大值
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void setOnScroll(final OnScroll onScroll) {
        this.onScroll = onScroll;
        if (onScrollListener == null) {
            onScrollListener = new OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (recyclerView.getLayoutManager().getChildCount() == 0) {
                        return;
                    }
                    //得到当前显示的最后一个item的view
                    View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                    //得到lastChildView的bottom坐标值
                    int lastChildBottom = lastChildView.getBottom();
                    //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                    int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                    //通过这个lastChildView得到这个view当前的position值
                    int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);

                    //判断lastChildView的bottom值跟recyclerBottom
                    //判断lastPosition是不是最后一个position
                    //如果两个条件都满足则说明是真正的滑动到了底部
                    LogUtil.E(lastChildBottom + "----------" + recyclerBottom);
                    if (lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                        if (onScroll != null) {
                            onScroll.onScrollToEnd(MyRecyclerView.this);
                        }
                    }
                }
            };
        }
        addOnScrollListener(onScrollListener);
    }
}
