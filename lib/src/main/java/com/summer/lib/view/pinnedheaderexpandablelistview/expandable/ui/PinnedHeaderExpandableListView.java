/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014 singwhatiwanna
 * https://github.com/singwhatiwanna
 * http://blog.csdn.net/singwhatiwanna
 * <p>
 * Permission is hereby granted, free of charge, dealer any person obtaining a copy
 * of this software and associated documentation files (the "Software"), dealer deal
 * in the Software without restriction, including without limitation the rights
 * dealer use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and dealer permit persons dealer whom the Software is
 * furnished dealer do so, subject dealer the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.summer.lib.view.pinnedheaderexpandablelistview.expandable.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;

import com.summer.lib.util.LogUtil;
import com.summer.lib.view.chart.linearchat.bean.databean.Value;
import com.summer.lib.view.swipeview.view.SwipeView;

public class PinnedHeaderExpandableListView extends ExpandableListView implements OnScrollListener {
    private static final String TAG = "PinnedHeaderExpandableListView";
    private static final boolean DEBUG = true;
    protected boolean mIsHeaderGroupClickable = true;
    OnItemClickListener onHeadViewClick;
    Context context;
    Value[] values = new Value[]{new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0), new Value(0, 0)};
    int status = -1;
    private View mHeaderView;
    private int mHeaderWidth;
    private int mHeaderHeight;
    private View mTouchTarget;
    private OnScrollListener mScrollListener;
    private OnHeaderUpdateListener mHeaderUpdateListener;
    private boolean mActionDownHappened = false;
    private int p = 0;
    private View headview;

    public PinnedHeaderExpandableListView(Context context) {
        super(context);
        initView(context);
    }

    public PinnedHeaderExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PinnedHeaderExpandableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        setFadingEdgeLength(0);
        setOnScrollListener(this);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        if (l != this) {
            mScrollListener = l;
        } else {
            mScrollListener = null;
        }
        super.setOnScrollListener(this);
    }

    /**
     * 给group添加点击事件监听
     *
     * @param onGroupClickListener   监听
     * @param isHeaderGroupClickable 表示header是否可点击<br/>
     *                               note : 当不想group可点击的时候，需要在OnGroupClickListener#onGroupClick中返回true，
     *                               并将isHeaderGroupClickable设为false即可
     */
    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener, boolean isHeaderGroupClickable) {
        mIsHeaderGroupClickable = isHeaderGroupClickable;
        super.setOnGroupClickListener(onGroupClickListener);
    }

    public void setOnHeaderUpdateListener(OnHeaderUpdateListener listener) {
        mHeaderUpdateListener = listener;
        if (listener == null) {
            mHeaderView = null;
            mHeaderWidth = mHeaderHeight = 0;
            return;
        }
        mHeaderView = listener.getPinnedHeader();
        int firstVisiblePos = getFirstVisiblePosition();
        int firstVisibleGroupPos = getPackedPositionGroup(getExpandableListPosition(firstVisiblePos));
        listener.updatePinnedHeader(mHeaderView, firstVisibleGroupPos);
        requestLayout();
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        heightMeasureSpec =  MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeaderView == null) {
            return;
        }
        measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
        mHeaderWidth = mHeaderView.getMeasuredWidth();
        mHeaderHeight = mHeaderView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mHeaderView == null) {
            return;
        }
        int delta = mHeaderView.getTop();
        mHeaderView.layout(0, delta, mHeaderWidth, mHeaderHeight + delta);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mHeaderView != null && mHeaderView.getVisibility() == View.VISIBLE) {
            drawChild(canvas, mHeaderView, getDrawingTime());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getHeadview() != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (ev.getY() <= getHeadview().getMeasuredHeight()) {
                        p = (int) ((ev.getX() / (getMeasuredWidth() / 3)) + 1);

                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (p == (int) ((ev.getX() / (getMeasuredWidth() / 3)) + 1)) {
                        onHeadViewClick.onItemClick(this, headview, p, -1);
                    }
                    p = 0;
                    break;
            }
        }
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//        int pos = pointToPosition(x, y);
//        if (mHeaderView != null && y >= mHeaderView.getTop() && y <= mHeaderView.getBottom()) {
//            switch (ev.getAction()){
//                case MotionEvent.ACTION_DOWN:
//                    mTouchTarget = getTouchTarget(mHeaderView, x, y);
//                    mActionDownHappened = true;
//                    downx=x;
//                    downy=y;
//
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    movex=x;
//                    movey=y;
//                    break;
//                case MotionEvent.ACTION_UP:
//                    View touchTarget = getTouchTarget(mHeaderView, x, y);
//                    if (touchTarget == mTouchTarget && mTouchTarget.isClickable()) {
//                        mTouchTarget.performClick();
//                        invalidate(new Rect(0, 0, mHeaderWidth, mHeaderHeight));
//                    } else if (mIsHeaderGroupClickable){
//                        int groupPosition = getPackedPositionGroup(getExpandableListPosition(pos));
//                        if (groupPosition != INVALID_POSITION && mActionDownHappened) {
//                            if (isGroupExpanded(groupPosition)) {
//                                collapseGroup(groupPosition);
//                            } else {
//                                expandGroup(groupPosition);
//                            }
//                        }
//                    }
//                    mActionDownHappened = false;
//                    break;
//            }
//            return true;
//        }

        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int pos = pointToPosition(x, y);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                status = 0;
                values[0].setX(ev.getX());
                values[0].setY(ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                values[1].setX(ev.getX());
                values[1].setY(ev.getY());

                if ((Math.abs((values[1].getY() - values[0].getY())) / Math.abs((values[1].getX() - values[0].getX()))) > 1) {
                    if (status != 1) {
                        for (int i = 0; i < getChildCount(); i++) {
                            if (getChildAt(i) instanceof SwipeView) {
                                SwipeView swipeView = (SwipeView) getChildAt(i);
                                if (swipeView.getFinishView() != null && ViewCompat.getTranslationX(swipeView.getChildView()) != 0) {
                                    swipeView.endAnimatorTranslationX();
                                }
                            }
                        }
                    }
                    status = 1;
                }
                break;
            case MotionEvent.ACTION_UP:
                status = 2;
                values[2].setX(ev.getX());
                values[2].setY(ev.getY());
                break;
        }

        if (getHeadview() != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    LogUtil.E(getHeadview().getBottom());
                    if (ev.getY() <= getHeadview().getBottom()) {
                        p = (int) ((ev.getX() / (getWidth() / 3)) + 1);
                        values[5].setX(-1);
                    } else {
                        values[5].setX(0);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (values[5].getX() == -1 && p == (int) ((ev.getX() / (getWidth() / 3)) + 1)) {
                        onHeadViewClick.onItemClick(this, headview, p, -1);
                    }
                    p = 0;
                    break;
            }
        }
        if (mHeaderView != null && y >= mHeaderView.getTop() && y <= mHeaderView.getBottom()) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchTarget = getTouchTarget(mHeaderView, x, y);
                    mActionDownHappened = true;

                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    View touchTarget = getTouchTarget(mHeaderView, x, y);
                    if (touchTarget == mTouchTarget && mTouchTarget.isClickable()) {
                        mTouchTarget.performClick();
                        invalidate(new Rect(0, 0, mHeaderWidth, mHeaderHeight));
                    } else if (mIsHeaderGroupClickable) {
                        int groupPosition = getPackedPositionGroup(getExpandableListPosition(pos));
                        if (groupPosition != INVALID_POSITION && mActionDownHappened) {
                            if (isGroupExpanded(groupPosition)) {
                                collapseGroup(groupPosition);
                            } else {
                                expandGroup(groupPosition);
                            }
                        }
                    }
                    mActionDownHappened = false;
                    break;
            }
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private View getTouchTarget(View view, int x, int y) {
        if (!(view instanceof ViewGroup)) {
            return view;
        }

        ViewGroup parent = (ViewGroup) view;
        int childrenCount = parent.getChildCount();
        final boolean customOrder = isChildrenDrawingOrderEnabled();
        View target = null;
        for (int i = childrenCount - 1; i >= 0; i--) {
            final int childIndex = customOrder ? getChildDrawingOrder(childrenCount, i) : i;
            final View child = parent.getChildAt(childIndex);
            if (isTouchPointInView(child, x, y)) {
                target = child;
                break;
            }
        }
        if (target == null) {
            target = parent;
        }

        return target;
    }

    private boolean isTouchPointInView(View view, int x, int y) {
        return y >= view.getTop() && y <= view.getBottom()
                && x >= view.getLeft() && x <= view.getRight();
    }

    public void requestRefreshHeader() {
        refreshHeader();
        invalidate(new Rect(0, 0, mHeaderWidth, mHeaderHeight));
    }

    protected void refreshHeader() {
        if (mHeaderView == null) {
            return;
        }
        int firstVisiblePos = getFirstVisiblePosition();
        int pos = firstVisiblePos + 1;
        int firstVisibleGroupPos = getPackedPositionGroup(getExpandableListPosition(firstVisiblePos));
        int group = getPackedPositionGroup(getExpandableListPosition(pos));


        if (group == firstVisibleGroupPos + 1) {
            View view = getChildAt(1);
            if (view == null) {
                LogUtil.E(TAG, "Warning : refreshHeader getChildAt(1)=null");
                return;
            }
            if (view.getTop() <= mHeaderHeight) {
                int delta = mHeaderHeight - view.getTop();
                mHeaderView.layout(0, -delta, mHeaderWidth, mHeaderHeight - delta);
            } else {
                //TODO : note it, when cause bug, remove it
                mHeaderView.layout(0, 0, mHeaderWidth, mHeaderHeight);
            }
        } else {
            mHeaderView.layout(0, 0, mHeaderWidth, mHeaderHeight);
        }

        if (mHeaderUpdateListener != null) {
            mHeaderUpdateListener.updatePinnedHeader(mHeaderView, firstVisibleGroupPos);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount > 0) {
            refreshHeader();
        }
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }


    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        refreshHeader();
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        refreshHeader();
    }

    @Override
    public void addHeaderView(View v) {
        this.headview = v;
        super.addHeaderView(v);
    }

    @Override
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        this.headview = v;
        super.addHeaderView(v, data, isSelectable);
    }

    public View getHeadview() {
        return headview;
    }

    public void setOnHeadViewClick(OnItemClickListener onHeadViewClick) {
        this.onHeadViewClick = onHeadViewClick;
    }

    public interface OnHeaderUpdateListener {
        /**
         * 返回一个view对象即可
         * 注意：view必须要有LayoutParams
         */
        View getPinnedHeader();

        void updatePinnedHeader(View headerView, int firstVisibleGroupPos);
    }
}