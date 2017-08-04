package com.summer.desktop.module.note.note;

//by summer on 2017-07-31.

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.bean.LayoutDABean;
import com.android.lib.network.NetOpe;
import com.android.lib.util.BitmapUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;
import com.summer.desktop.BR;
import com.summer.desktop.R;
import com.summer.desktop.databinding.ItemEdittextBinding;
import com.summer.desktop.databinding.ItemImageBinding;
import com.summer.desktop.module.note.note.bean.NoteItemBean;
import com.summer.desktop.util.GlideApp;

import java.io.File;
import java.util.ArrayList;


public class NoteAdapter extends RecyclerView.Adapter<AppViewHolder> {

    ArrayList<LayoutDABean> data;
    View.OnClickListener onImageClick;
    private Context context;

    public NoteAdapter(Context context, ArrayList<LayoutDABean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).aint.get();
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AppViewHolder viewHolder = null;
        switch (viewType) {
            case NoteItemBean.TYPE_TXT:
                viewHolder = new AppViewHolder(ItemEdittextBinding.inflate(LayoutInflater.from(context)));
                break;
            case NoteItemBean.TYPE_IMAGE:
                viewHolder = new AppViewHolder(ItemImageBinding.inflate(LayoutInflater.from(context)));
                break;
            default:

                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        switch (data.get(position).aint.get()) {
            case NoteItemBean.TYPE_TXT:
                holder.viewDataBinding.setVariable(BR.edittext, data.get(position));
                break;
            case NoteItemBean.TYPE_IMAGE:
                final ItemImageBinding itemImageBinding = (ItemImageBinding) holder.viewDataBinding;


                itemImageBinding.imageView.getLayoutParams().width = (int) ScreenUtil.w;
                itemImageBinding.imageView.getLayoutParams().height = (int) (ScreenUtil.w * data.get(position).bint.get() / data.get(position).cint.get());
                if (itemImageBinding.imageView.getLayoutParams().height <= itemImageBinding.imageView.getLayoutParams().width * 2) {
                    itemImageBinding.imageView.requestLayout();
                }
                final String[] strs = data.get(position).a.get().toString().split("/");
                LogUtil.E(NetOpe.NET_DOMAIN + "/files/" + strs[strs.length - 1]);
                final File file = new File(data.get(position).a.get().toString());
                if (file.exists()) {
                    GlideApp.with(context).load(data.get(position).a.get().toString()).placeholder(R.drawable.app).into(itemImageBinding.imageView);
                } else {
                    BitmapUtil.saveImage(context, file.getPath(), NetOpe.NET_DOMAIN + "/files/" + strs[strs.length - 1], new OnFinishListener() {
                        @Override
                        public void onFinish(Object o) {
                            GlideApp.with(context).load(file.getPath()).placeholder(R.drawable.app).into(itemImageBinding.imageView);
                        }
                    });
                }
                holder.viewDataBinding.setVariable(BR.imageview, data.get(position));
                ((ItemImageBinding) holder.viewDataBinding).imageView.setTag(R.id.data, NetOpe.NET_DOMAIN + "/files/" + strs[strs.length - 1]);
                ((ItemImageBinding) holder.viewDataBinding).imageView.setTag(R.id.data1, data.get(position).a.get().toString());
                ((ItemImageBinding) holder.viewDataBinding).imageView.setOnClickListener(onImageClick);
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnImageClick(View.OnClickListener onImageClick) {
        this.onImageClick = onImageClick;
    }
}
