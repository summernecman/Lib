package com.summer.desktop.module.app.folder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.summer.desktop.bean.dbbean.AppDBBean;

import java.util.ArrayList;

/**
 * <p/>
 * Date: 16/6/12 09:51
 * Author: rsshinide38@163.com
 * <p/>
 */
public class NormalFragment extends BaseUIFrag<FolderItemsUIOpe, FolderItemsDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getDa().getApps(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<ArrayList<AppDBBean>> list = (ArrayList<ArrayList<AppDBBean>>) o;
                getOpes().getUi().init(list);
            }
        });
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        if (!MyAdapter.class.getName().equals(event.sender)) {
            return;
        }
        AppDBBean appDBBean = (AppDBBean) event.data;
        if (appDBBean.getAppName().equals("刷新") && NullUtil.isStrEmpty(appDBBean.getPackageName())) {
            getOpes().getDa().clearData();
            getOpes().getDa().getApps(new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    ArrayList<ArrayList<AppDBBean>> list = (ArrayList<ArrayList<AppDBBean>>) o;
                    getOpes().getUi().init(list);
                }
            });
            return;
        }
        IntentUtil.getInstance().IntentTo(activity, appDBBean.getPackageName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getOpes().getDa().saveSort();
    }
}