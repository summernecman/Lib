package com.summer.desktop.util;

//by summer on 2017-05-25.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.summer.desktop.bean.dabean.NoteDetail;
import com.summer.desktop.module.note.notedetail.NoteDetailAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ViewCreater {

    public static Gson gson = new Gson();


    public static View create(Context context, ArrayList<NoteDetail> data) {

        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (data == null) {
            return recyclerView;
        }
        recyclerView.setAdapter(new NoteDetailAdapter(context, data));
        attachToItemTouch(recyclerView, data);
//        for (int i = 0; i < data.size(); i++) {
//            switch (data.get(i).getType()) {
//                case NoteDetail.IMAGE:
//                    ImageView imageView = new ImageView(context);
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    imageView.setScaleType(ImageView.ScaleType.CENTER);
//                    ImageNote imageNote = gson.fromJson(data.get(i).getData(),ImageNote.class);
//                    Glide.with(context).asBitmap().load(imageNote.getSrc()).into(imageView);
//                    //ImageLoader.getInstance().displayImage(imageNote.getSrc(),imageView);
//                    linearLayout.addView(imageView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    imageView.setTag(R.id.position, i);
//                    break;
//                case NoteDetail.TXT:
//                    EditText textView = new EditText(context);
//                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    linearLayout.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    TxtNote txtNote = gson.fromJson(data.get(i).getData(),TxtNote.class);
//                    textView.setText(txtNote.getTxt());
//                    textView.setTag(R.id.position, i);
//                    break;
//            }
//        }
        return recyclerView;

    }

    public static void attachToItemTouch(RecyclerView recyclerView, final ArrayList<NoteDetail> data) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(data, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(data, i, i - 1);
                    }
                }
                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
