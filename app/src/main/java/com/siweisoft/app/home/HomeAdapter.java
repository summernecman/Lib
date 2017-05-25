package com.siweisoft.app.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.siweisoft.app.bean.dbbean.AppDBBean;
import com.siweisoft.app.bean.uibean.AppUIBean;
import com.siweisoft.app.R;
import com.summer.lib.base.adapter.AppRecycleAdapter;
import com.summer.lib.util.LogUtil;

import java.util.ArrayList;

public class HomeAdapter extends AppRecycleAdapter<AppUIBean> {

    ArrayList<AppDBBean> appDBBeen;


    public HomeAdapter(Context context, ArrayList<AppDBBean> appDBBeen) {
        super(context);
        this.appDBBeen =appDBBeen;
    }

    @Override
    public AppUIBean onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.E("appuibean");
        return new AppUIBean(context,parent);
    }

    @Override
    public void onBindViewHolder(AppUIBean holder, int position) {
        super.onBindViewHolder(holder,position);
        holder.itemView.setTag(R.id.data,appDBBeen.get(position));
        holder.getAppicon().setBackgroundDrawable(appDBBeen.get(position).getIcon());
        holder.getTvAppname().setText(appDBBeen.get(position).getAppName());
    }

    @Override
    public int getItemCount() {
        return appDBBeen.size();
    }
}