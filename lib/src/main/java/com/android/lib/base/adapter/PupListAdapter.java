//package com.android.lib.base.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.android.lib.R;
//import OnAppItemClickListener;
//import StringUtil;
//
//import java.util.ArrayList;
//
///**
// * 下拉菜单的适配器基类
// */
//public class PupListAdapter extends AppRecycleAdapter {
//
//    /**
//     * 下拉菜单文字
//     */
//    String[] strings;
//    /**菜单item点击事件*/
//    OnAppItemClickListener onAppItemClickListener;
//
//    public PupListAdapter(Context context, String[] strings) {
//        super(context);
//        this.strings = strings;
//    }
//
//    public PupListAdapter(Context context, ArrayList<String> ss) {
//        super(context);
//        strings = new String[ss.size()];
//        strings = ss.toArray(strings);
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = layoutInflater.inflate(R.layout.list_pop, parent, false);
//        PupListUIBean pupListUIBean = new PupListUIBean(context, view);
//        return pupListUIBean;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        PupListUIBean pupListUIBean = (PupListUIBean) holder;
//        pupListUIBean.getTextView().setText(StringUtil.getStr(strings[position]));
//        pupListUIBean.getTextView().setTag(R.id.position, position);
//        pupListUIBean.getTextView().setOnClickListener(this);
//    }
//
//    @Override
//    public int getItemCount() {
//        return strings == null ? 0 : strings.length;
//    }
//
//
//    public void setOnAppItemClickListener(OnAppItemClickListener onAppItemClickListener) {
//        this.onAppItemClickListener = onAppItemClickListener;
//    }
//
//    @Override
//    public void onClick(View v) {
//        int position = (int) v.getTag(R.id.position);
//        if (onAppItemClickListener != null) {
//            onAppItemClickListener.onAppItemClick(v, position);
//        }
//    }
//}
