package com.summer.desktop.module.note.notelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.Note;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> implements View.OnClickListener, View.OnLongClickListener {

    Context context;

    View.OnLongClickListener onLongClickListener;


    ArrayList<Note> notes;

    String id;

    public NewsAdapter(Context context, String id, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
        this.id = id;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.item_note_note, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);
        holder.itemView.setTag(R.id.position, position);
        holder.itemView.setTag(R.id.data, notes.get(position));
        holder.textView.setText(notes.get(position).getName());
        holder.dateTV.setText(DateFormatUtil.yyyyMMdd_HHmmss_to_yyyyMMdd_HHmm(notes.get(position).getCreatedAt()));
        if (notes.get(position).getType().equals(Note.NOTE)) {
            holder.imgIV.setImageResource(R.drawable.note);
        } else {
            holder.imgIV.setImageResource(R.drawable.notebook);
        }
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    @Override
    public void onClick(View v) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.sender = NewsAdapter.class.getName();
        messageEvent.dealer = NoteListFrag.class.getName();
        messageEvent.data = v;
        messageEvent.id = this.id;
        EventBus.getDefault().post(messageEvent);
    }


    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(v);
        }
        return true;
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView textView;

        @BindView(R.id.date)
        TextView dateTV;

        @BindView(R.id.image)
        ImageView imgIV;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}