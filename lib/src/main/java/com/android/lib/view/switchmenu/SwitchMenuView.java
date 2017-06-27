package com.android.lib.view.switchmenu;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.lib.R;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.listener.BaseOnPagerChangeListener;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-10-18.
 */
public class SwitchMenuView extends LinearLayout implements View.OnClickListener {


    LayoutInflater layoutInflater;

    Context context;

    ViewGroup group;

    ArrayList<Button> buttons = new ArrayList<>();

    OnAppItemSelectListener onAppItemSelectListener;

    ViewPager viewPager;

    public SwitchMenuView(Context context) {
        super(context);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        init();
    }

    public SwitchMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        group = (ViewGroup) layoutInflater.inflate(R.layout.item_switchmenu, null);
        addView(group, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < group.getChildCount(); i++) {
            buttons.add((Button) group.getChildAt(i));
            group.getChildAt(i).setTag(R.id.position, i);
            group.getChildAt(i).setOnClickListener(this);
            if (i == 0) {
                group.getChildAt(i).setSelected(true);
            }
        }
        onClick(group.getChildAt(0));
    }

    public void initNames(ArrayList<String> names) {
        for (int i = 0; i < group.getChildCount(); i++) {
            Button button = (Button) group.getChildAt(i);
            button.setText(names.get(i));
        }
    }


    @Override
    public void onClick(View v) {
        int index = (Integer) v.getTag(R.id.position);
        if (onAppItemSelectListener != null) {
            onAppItemSelectListener.onAppItemSelect(group, v, index);
        }
        for (int i = 0; i < group.getChildCount(); i++) {
            Button button = (Button) group.getChildAt(i);
            if (i == index) {
                button.setSelected(true);
                button.setTextColor(Color.WHITE);
            } else {
                button.setSelected(false);
                button.setTextColor(getResources().getColor(R.color.color_base));
            }
        }
    }

    public void setOnAppItemSelectListener(OnAppItemSelectListener onAppItemSelectListener) {
        this.onAppItemSelectListener = onAppItemSelectListener;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(new BaseOnPagerChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (i == position) {
                        Button button = (Button) group.getChildAt(i);
                        button.setSelected(true);
                        button.setTextColor(Color.WHITE);
                    } else {
                        Button button = (Button) group.getChildAt(i);
                        button.setSelected(false);
                        button.setTextColor(getResources().getColor(R.color.color_base));
                    }
                }
            }
        });
    }
}
