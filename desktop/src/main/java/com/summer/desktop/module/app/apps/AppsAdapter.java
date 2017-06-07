package com.summer.desktop.module.app.apps;

import android.content.Context;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.bean.uibean.AppItemUIBean;
import com.summer.lib.base.adapter.AppRecycleAdapter;
import com.summer.lib.util.LogUtil;

import java.util.ArrayList;

public class AppsAdapter extends AppRecycleAdapter<AppItemUIBean> {

    ArrayList<AppDBBean> appDBBeen;


    public AppsAdapter(Context context, ArrayList<AppDBBean> appDBBeen) {
        super(context);
        this.appDBBeen = appDBBeen;
    }

    @Override
    public AppItemUIBean onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.E("appuibean");
        return new AppItemUIBean(context, parent);
    }

    @Override
    public void onBindViewHolder(AppItemUIBean holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setTag(R.id.data, appDBBeen.get(position));
        holder.getAppicon().setBackgroundDrawable(appDBBeen.get(position).getIcon());
        holder.getTvAppname().setText(appDBBeen.get(position).getAppName());
    }

    @Override
    public int getItemCount() {
        return appDBBeen.size();
    }
}