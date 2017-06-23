package com.android.lib.view.pinnedheaderexpandablelistview.doubleexpandable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;

import com.android.lib.view.pinnedheaderexpandablelistview.expandable.ui.PinnedHeaderExpandableListView;


/**
 * Created by ${viwmox} on 2016-11-24.
 */
public class DoubleExpandableListView extends PinnedHeaderExpandableListView {


    PinnedHeaderExpandableListView relativeListView;

    int tag = 0;


    public DoubleExpandableListView(Context context) {
        super(context);
    }

    public DoubleExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoubleExpandableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        View v = view.getChildAt(0);
        if (v != null) {
            int y = v.getHeight() * firstVisibleItem - v.getTop();
            if (relativeListView != null && isFocused()) {
                relativeListView.scrollBy(0, y);
            }
        }
    }

    public void setRelativeListView(DoubleExpandableListView relativeListView, int tag) {
        this.relativeListView = relativeListView;
        this.tag = tag;
    }
}
