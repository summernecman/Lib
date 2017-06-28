package com.summer.desktop.module.app.folder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.summer.desktop.R;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.module.classify.simple.SimpleAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * <p/>
 * Date: 16/6/7 16:40
 * Author: rsshinide38@163.com
 * <p/>
 */
public class MyAdapter extends SimpleAdapter<AppDBBean, MyAdapter.FloderHolder> {


    public MyAdapter(ArrayList<ArrayList<AppDBBean>> mData) {
        super(mData);
    }


    @Override
    protected FloderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample_vertical, parent, false);
        view.getLayoutParams().width = (int) (ScreenUtil.w / 4);
        view.getLayoutParams().height = view.getLayoutParams().width;
        view.requestFocus();
        return new FloderHolder(view);
    }


    @Override
    public View getView(ViewGroup parent, View convertView, int mainPosition, int subPosition) {
        ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inner, parent, false);
            itemHolder = new ItemHolder(convertView);
            convertView.setTag(R.id.data, itemHolder);
        }
        if (subPosition > -1) {
            LogUtil.E(mainPosition + ":::" + subPosition);
            itemHolder = (ItemHolder) convertView.getTag(R.id.data);
            itemHolder.nametv.setText(mData.get(mainPosition).get(subPosition).getAppName());
            itemHolder.appicon.setImageDrawable(mData.get(mainPosition).get(subPosition).getIcon());
        }
        //mData.get(mainPosition).get(subPosition).setPosition(mainPosition);
        return convertView;
    }

    @Override
    protected void onItemClick(View view, int parentIndex, int index) {
        LogUtil.E(parentIndex + "---" + index);
        index = index == -1 ? 0 : index;
        MessageEvent messageEvent = new MessageEvent(MyAdapter.class.getName(), NormalFragment.class.getName(), mData.get(parentIndex).get(index));
        EventBus.getDefault().post(messageEvent);
    }

    static class FloderHolder extends SimpleAdapter.ViewHolder {

        public FloderHolder(View itemView) {
            super(itemView);
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        ImageView appicon;

        TextView nametv;

        public ItemHolder(View itemView) {
            super(itemView);
            appicon = (ImageView) itemView.findViewById(R.id.appicon);
            nametv = (TextView) itemView.findViewById(R.id.tv_appname);
        }
    }

}
