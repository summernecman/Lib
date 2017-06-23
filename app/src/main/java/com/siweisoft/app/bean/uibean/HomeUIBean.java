package com.siweisoft.app.bean.uibean;

//by summer on 2017-03-31.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lib.base.adapter.AppRecycleAdapter;
import com.siweisoft.app.R;
import com.siweisoft.app.impl.IRecycle;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class HomeUIBean extends BaseUIBean implements IRecycle {

    @BindView(R.id.recycle)
    RecyclerView recyclerView;
    @BindView(R.id.tv_drag)
    ImageView tvDrag;

    public HomeUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_main);
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        //recyclerView.addItemDecoration(new MyItemDecoration(context,2));
    }

    @Override
    public void load(AppRecycleAdapter appRecycleAdapter) {
        recyclerView.setAdapter(appRecycleAdapter);
    }

    public ImageView getTvDrag() {
        return tvDrag;
    }

    @Override
    public void pull() {

    }

    @Override
    public void push() {

    }

    public void move() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0;
                int swipeFlags = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    swipeFlags = 0;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = 0;
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        //Collections.swap(results, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        //Collections.swap(results, i, i - 1);
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
