package com.android.lib.view.menuview.homebottom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.R2;
import com.android.lib.base.interf.view.OnAppItemLongClickListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.LogUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by ${viwmox} on 2016-11-08.
 */
public class HomeBottomView extends LinearLayout implements View.OnLongClickListener {

    ArrayList<View> tabViews = new ArrayList<>();
    ArrayList<TextView> txtViews = new ArrayList<>();
    OnAppItemSelectListener onAppItemSelectListener;
    OnAppItemLongClickListener onAppItemLongClickListener;
    OnLongClickListener onLongClickListener;
    long time = 0;
    int id = R.id.ll_bed;
    private Context context;

    public HomeBottomView(Context context) {
        super(context);
        init(context, null);
    }


    public HomeBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.activity_home_bottom, null);
        tabViews.add(view.findViewById(R.id.ll_bed));
        tabViews.add(view.findViewById(R.id.ll_mission));
        tabViews.add(view.findViewById(R.id.ll_check));
        tabViews.add(view.findViewById(R.id.ll_info));
        tabViews.add(view.findViewById(R.id.ll_setting));
        txtViews.add((TextView) view.findViewById(R.id.tv_bed));
        txtViews.add((TextView) view.findViewById(R.id.tv_mission));
        txtViews.add((TextView) view.findViewById(R.id.tv_check));
        txtViews.add((TextView) view.findViewById(R.id.tv_info));
        txtViews.add((TextView) view.findViewById(R.id.tv_setting));
        addView(view, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ButterKnife.bind(this, this);
        onClick(tabViews.get(0));
        findViewById(R.id.ll_check).setOnLongClickListener(this);
    }

    @Optional
    @OnClick({R2.id.ll_bed, R2.id.ll_check, R2.id.ll_mission, R2.id.ll_info, R2.id.ll_setting})
    public void onClick(View v) {
        long t = System.currentTimeMillis();
        long d = t - time;
        int idd = v.getId();
        boolean b = v.getId() == id;
        id = v.getId();
        time = t;
        LogUtil.E(d);
        if (d < 500 && b) {
            if (onAppItemLongClickListener != null) {
                for (int i = 0; i < tabViews.size(); i++) {
                    if (v.getId() == tabViews.get(i).getId()) {
                        onAppItemLongClickListener.onAppItemLongClick(v, i);
                    }
                }
            }
        } else {
            for (int i = 0; i < tabViews.size(); i++) {
                if (v.getId() == tabViews.get(i).getId()) {
                    tabViews.get(i).setSelected(true);
                    txtViews.get(i).setTextColor(context.getResources().getColor(R.color.color_text_select));
                    if (onAppItemSelectListener != null) {
                        onAppItemSelectListener.onAppItemSelect(this, v, i);
                    }
                } else {
                    tabViews.get(i).setSelected(false);
                    txtViews.get(i).setTextColor(context.getResources().getColor(R.color.color_text_notsel));
                }
            }
        }
    }


    public void setOnAppItemSelectListener(OnAppItemSelectListener onAppItemSelectListener) {
        this.onAppItemSelectListener = onAppItemSelectListener;
    }

    public void select(int index) {
        for (int i = 0; i < tabViews.size(); i++) {
            if (index == i) {
                tabViews.get(i).setSelected(true);
                txtViews.get(i).setTextColor(context.getResources().getColor(R.color.color_text_select));
            } else {
                tabViews.get(i).setSelected(false);
                txtViews.get(i).setTextColor(context.getResources().getColor(R.color.color_text_notsel));
            }
        }
    }

    public void setOnAppItemLongClickListener(OnAppItemLongClickListener onAppItemLongClickListener) {
        this.onAppItemLongClickListener = onAppItemLongClickListener;
    }

    public void setOnLongClick(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        onLongClickListener.onLongClick(v);
        return true;
    }
}
