package com.summer.desktop.module.app.appitems;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.android.lib.util.BitmapUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.summer.desktop.R;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.databinding.FragAppAppsBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppItemsUIBean extends BaseUIBean<FragAppAppsBinding> {


    public AppItemsUIBean(Context context) {
        super(context);
        init();
    }

    public void initList(ArrayList<AppDBBean> appDBBeen) {
        loadData(new AppsDataBindingAdapter(context, R.layout.list_app_main, com.summer.desktop.BR.item, appDBBeen) {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                MessageEvent messageEvent = new MessageEvent(AppItemsUIBean.class.getName(), AppItemsFrag.class.getName(), v.getTag(R.id.data));
                EventBus.getDefault().post(messageEvent);
            }
        });
        move(appDBBeen);
    }

    public void init() {
        getViewDataBinding().appRecycle.setLayoutManager(new GridLayoutManager(context, 5));
        viewDataBinding.appsIv.setImageDrawable(BitmapUtil.getInstance().getBgDrawable(context));
    }


    public void loadData(RecyclerView.Adapter adapter) {
        getViewDataBinding().appRecycle.setAdapter(adapter);
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
                LogUtil.E(fromPosition + "--" + toPosition);
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

            @Override
            public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder selected, List<RecyclerView.ViewHolder> dropTargets, int curX, int curY) {
                return super.chooseDropTarget(selected, dropTargets, curX, curY);
            }
        });
        itemTouchHelper.attachToRecyclerView(getViewDataBinding().appRecycle);

    }

}
