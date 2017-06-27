package com.android.lib.view.bottommenu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.listener.BaseOnPagerChangeListener;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-10-18.
 */
public class BottomMenuView extends LinearLayout implements View.OnTouchListener {


    OnAppItemSelectListener onAppItemClickListener;
    ViewPager viewPager;
    ViewGroup viewGroup;
    private Context context;
    private int index = 0;

    public BottomMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bottommenus, null);
        addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void initItems(ArrayList<BottomMenuBean> been) {
        viewGroup = (ViewGroup) findViewById(R.id.ll_container);
        viewGroup.removeAllViews();
        for (int i = 0; i < been.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_bottommenu, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
            TextView textView = (TextView) view.findViewById(R.id.tv_name);
            imageView.setBackgroundResource(been.get(i).drawbleId);
            textView.setText(been.get(i).name);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            viewGroup.addView(view, params);
            view.setTag(R.id.position, i);
        }
        viewGroup.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewGroup viewGroup = (ViewGroup) v;
        float x = event.getX();
        float y = event.getY();

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (x > viewGroup.getChildAt(i).getLeft() && x < viewGroup.getChildAt(i).getRight()) {
                int position = (int) viewGroup.getChildAt(i).getTag(R.id.position);
                if (position != index) {
                    index = position;
                    onAppItemClickListener.onAppItemSelect(viewGroup, viewGroup.getChildAt(i), position);
                }
                viewGroup.getChildAt(i).setSelected(true);
            } else {
                viewGroup.getChildAt(i).setSelected(false);
            }
        }
        return true;
    }

    public void setOnAppItemClickListener(OnAppItemSelectListener onAppItemClickListener) {
        this.onAppItemClickListener = onAppItemClickListener;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new BaseOnPagerChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    if (viewGroup == null) {
                        return;
                    }
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        if (i == position) {
                            index = position;
                            viewGroup.getChildAt(position).setSelected(true);
                            onAppItemClickListener.onAppItemSelect(viewGroup, viewGroup.getChildAt(i), position);
                        } else {
                            viewGroup.getChildAt(i).setSelected(false);
                        }
                    }
                }
            });
        }
    }

    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public int getIndex() {
        return index;
    }
}
