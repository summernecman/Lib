package com.siweisoft.app.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.siweisoft.app.bean.uibean.AppUIBean;
import com.siweisoft.app.R;
import com.summer.lib.base.adapter.AppRecycleAdapter;

public class HomeAdapter extends AppRecycleAdapter<AppUIBean> {

    int[] ints = new int[]{R.drawable.icon_delete,R.drawable.icon_arraw,R.drawable.icon_bg_backk,R.drawable.icon_nodata,R.drawable.icon_loading,R.drawable.icon_nursemission_nomal,};


    public HomeAdapter(Context context, int[] ints) {
        super(context);
        this.ints = ints;
    }

    @Override
    public AppUIBean onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppUIBean(context,parent);
    }

    @Override
    public void onBindViewHolder(AppUIBean holder, int position) {
        holder.getAppicon().setImageResource(ints[position]);
    }

    @Override
    public int getItemCount() {
        return ints.length;
    }

    @Override
    public void onClick(View v) {

    }
}