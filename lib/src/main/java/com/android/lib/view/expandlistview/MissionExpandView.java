package com.android.lib.view.expandlistview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.android.lib.R;
import com.android.lib.util.LogUtil;

/**
 * Created by ${viwmox} on 2016-11-09.
 */
public class MissionExpandView extends RelativeLayout implements AbsListView.OnScrollListener, View.OnClickListener {


    ExpandableListView expandableListView;

    View headView;

    onScrollListener onScrollListener;

    int groupPosition = 0;

    Context context;
    int top = 0;
    int bottom = 0;
    View v0, v1;
    Handler handler = new Handler();

    public MissionExpandView(Context context) {
        super(context);
        this.context = context;
        init(context, null);
    }

    public MissionExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_expandlist, null);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandlistview);
        addView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        getExpandableListView().setGroupIndicator(null);
        getExpandableListView().setOnScrollListener(this);
        getExpandableListView().setFriction(ViewConfiguration.getScrollFriction() * 3);
        getExpandableListView().setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                } else {
                    //第二个参数false表示展开时是否触发默认滚动动画
                    parent.expandGroup(groupPosition, false);
                }
                //telling the listView we have handled the group click, and don't want the default actions.
                return true;
            }
        });

    }

    public void setHeadView(Context context, int id) {
        headView = LayoutInflater.from(context).inflate(id, null);
        addView(headView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headView.setOnClickListener(this);

    }

    public ExpandableListView getExpandableListView() {
        return expandableListView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        switch (scrollState) {
            case SCROLL_STATE_TOUCH_SCROLL:
                break;
            case SCROLL_STATE_IDLE:
                if (view == null || headView == null) {
                    return;
                }
                v0 = view.getChildAt(0);
                v1 = view.getChildAt(1);
                if (v0 == null || v1 == null) {
                    return;
                }
                if (v0.getId() == headView.getId() && ((int) v0.getTag(R.id.groupposition)) == 0) {
                    headView.setTranslationY(0);
                }
                if (v0.getTag(R.id.groupposition) != null) {
                    groupPosition = (int) v0.getTag(R.id.groupposition);
                }
                if (v1.getId() == headView.getId() && v1.getTop() < headView.getHeight()) {
                    headView.setTranslationY(v1.getTop() - headView.getHeight());
                    if (onScrollListener != null) {
                        if (top > v1.getTop()) {

                        } else {
                            groupPosition = (Integer) v1.getTag(R.id.groupposition) - 1;
                            onScrollListener.onHeadAtOne(headView, groupPosition);
                        }

                    }
                    top = v1.getTop();
                } else if (v0.getId() == headView.getId() && v0.getBottom() < headView.getHeight()) {
                    headView.setTranslationY(0);
                    if (onScrollListener != null) {
                        if (bottom > v0.getBottom()) {
                            //上推
                            groupPosition = (Integer) v0.getTag(R.id.groupposition);
                            onScrollListener.onHeadAtOne(headView, groupPosition);
                        }
                    }
                    bottom = v0.getBottom();
                } else {
                    if (onScrollListener != null) {
                        onScrollListener.onHeadAtOne(headView, groupPosition);
                    }
                    headView.setTranslationY(0);

                }
                break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view == null || headView == null) {
            return;
        }
        v0 = view.getChildAt(0);
        v1 = view.getChildAt(1);
        if (v0 == null || v1 == null) {
            return;
        }
        if (v0.getTag(R.id.groupposition) != null) {
            groupPosition = (int) v0.getTag(R.id.groupposition);
        }
        if (v1.getId() == headView.getId() && v1.getTop() <= headView.getHeight()) {
            headView.setTranslationY(v1.getTop() - headView.getHeight());
            if (onScrollListener != null) {
                if (top > v1.getTop()) {

                } else {
                    groupPosition = (Integer) v1.getTag(R.id.groupposition) - 1;
                    onScrollListener.onHeadAtOne(headView, groupPosition);
                }

            }
            top = v1.getTop();
        } else if (v0.getId() == headView.getId() && v0.getBottom() <= headView.getHeight()) {
            headView.setTranslationY(0);
            if (onScrollListener != null) {
                if (bottom > v0.getBottom()) {
                    //上推
                    groupPosition = (Integer) v0.getTag(R.id.groupposition);
                    onScrollListener.onHeadAtOne(headView, groupPosition);
                }
            }
            bottom = v0.getBottom();
        } else {
            if (onScrollListener != null) {
                onScrollListener.onHeadAtOne(headView, groupPosition);
            }
            headView.setTranslationY(0);

        }

    }

    @Override
    public void onClick(View v) {
        LogUtil.E("onclick" + groupPosition);
        if (expandableListView.isGroupExpanded(groupPosition)) {
            expandableListView.collapseGroup(groupPosition);
        } else {
            expandableListView.expandGroup(groupPosition);
        }
        expandableListView.setSelectedGroup(groupPosition);
        if (onScrollListener != null) {
            onScrollListener.onHeadAtOne(headView, groupPosition);
        }
    }

    public void setOnScrollListener(MissionExpandView.onScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public View getHeadView() {
        return headView;
    }

    public interface onScrollListener {

        void onHeadAtOne(View view, int position);
    }
}
