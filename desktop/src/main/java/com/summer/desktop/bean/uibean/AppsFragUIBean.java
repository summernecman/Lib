package com.summer.desktop.bean.uibean;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.lib.base.adapter.AppRecycleAdapter;
import com.summer.lib.bean.uibean.BaseUIBean;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;

public class AppsFragUIBean extends BaseUIBean {


    @BindView(R.id.app_recycle)
    RecyclerView appRecycle;

    public AppsFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_app_apps);
    }

    public void init() {
        getAppRecycle().setLayoutManager(new GridLayoutManager(context, 4));
        //getAppRecycle().addItemDecoration(new MyItemDecoration(context, 2));
    }

    public void loadData(AppRecycleAdapter adapter) {
        getAppRecycle().setAdapter(adapter);
    }

    public void move(final ArrayList<AppDBBean> appDBBeen) {
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
                        Collections.swap(appDBBeen, i, i + 1);
                    }
//                    for (int i = fromPosition; i < toPosition; i++) {
//                        appDBBeen.get(i).setPosition(i);
//                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(appDBBeen, i, i - 1);
                    }
//                    for (int i = fromPosition; i > toPosition; i--) {
//                        appDBBeen.get(i).setPosition(i);
//                    }
                }
                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(appRecycle);
    }

    public RecyclerView getAppRecycle() {
        return appRecycle;
    }
}
