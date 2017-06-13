package com.summer.desktop.module.day.main;

//by summer on 2017-06-13.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.bean.uibean.DayMainItemUIBean;
import com.summer.lib.base.adapter.AppRecycleAdapter;
import com.summer.lib.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class DayMainAdapter extends AppRecycleAdapter {

    MinuteDAOpe minuteDAOpe;

    View.OnLongClickListener onLongClickListener;

    ArrayList<TimeBean> times = new ArrayList<>();

    public DayMainAdapter(Context context) {
        super(context);
        minuteDAOpe = new MinuteDAOpe(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DayMainItemUIBean(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DayMainItemUIBean dayMainItemUIBean = (DayMainItemUIBean) holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        LogUtil.E("1");
        DayMainItemUIBean dayMainItemUIBean = (DayMainItemUIBean) holder;
        int h = 5 + (position % 25);
        int m = position / 25;
        if (h < minuteDAOpe.nowH()) {
            dayMainItemUIBean.getItem().setBackgroundColor(Color.GRAY);
        }
        if (h > minuteDAOpe.nowH()) {
            dayMainItemUIBean.getItem().setBackgroundColor(Color.WHITE);
        }
        if (h == minuteDAOpe.nowH()) {
            if (m < minuteDAOpe.nowM()) {
                dayMainItemUIBean.getItem().setBackgroundColor(Color.GRAY);
            } else {
                dayMainItemUIBean.getItem().setBackgroundColor(Color.WHITE);
            }
        }
        if (m == 0 && h == 0) {
            dayMainItemUIBean.getItem().setText("");
        }

        if (m == minuteDAOpe.nowM() && h == minuteDAOpe.nowH() ||
                h == 5 && m == minuteDAOpe.nowM() ||
                m == 0 && h == minuteDAOpe.nowH()) {
            dayMainItemUIBean.getItem().setBackgroundResource(com.summer.lib.R.color.pink);
        }

        for (int i = 0; i < times.size(); i++) {
            int s = times.get(i).sh * 60 + times.get(i).sm;
            int e = times.get(i).eh * 60 + times.get(i).em;
            if ((h * 60 + m) >= s && (h * 60 + m) <= e) {
                dayMainItemUIBean.getItem().setBackgroundResource(com.summer.lib.R.color.blue);
            }
        }

        if (m == 0) {
            dayMainItemUIBean.getItem().setBackgroundColor(Color.WHITE);
            dayMainItemUIBean.getItem().setText("" + (h) % 24);
        }
        if (h == 5) {
            dayMainItemUIBean.getItem().setBackgroundColor(Color.WHITE);
            dayMainItemUIBean.getItem().setText("" + m);
        }


        dayMainItemUIBean.itemView.setOnLongClickListener(this);
    }

    @Override
    public int getItemCount() {
        return 61 * 25;
    }

    @Override
    public boolean onLongClick(View v) {
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(v);
        }
        return true;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public void setTimes(ArrayList<TimeBean> times) {
        this.times = times;
    }
}
