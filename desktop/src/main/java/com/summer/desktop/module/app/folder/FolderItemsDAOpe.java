package com.summer.desktop.module.app.folder;

//by summer on 2017-06-28.

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.summer.desktop.R;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.db.ope.AppsDBOpe;
import com.summer.desktop.module.app.appitems.AppListDAOpe;

import java.util.ArrayList;
import java.util.HashMap;

public class FolderItemsDAOpe extends BaseDAOpe {

    AppListDAOpe appListDAOpe;

    AppsDBOpe appsDBOpe;

    ArrayList<AppDBBean> list = new ArrayList<>();

    ArrayList<ArrayList<AppDBBean>> ll = new ArrayList<ArrayList<AppDBBean>>();

    public FolderItemsDAOpe(Context context) {
        super(context);
        appListDAOpe = new AppListDAOpe(context);
        appsDBOpe = new AppsDBOpe(context, new AppDBBean());
    }

    public void getApps(final OnFinishListener listener) {
        list = appsDBOpe.getApps();
        if (list.size() == 0) {
            appListDAOpe.getApps("全部", new OnFinishWithObjI<ArrayList<AppDBBean>>() {
                @Override
                public void onNetFinish(ArrayList<AppDBBean> o) {
                    AppDBBean appDBBean = new AppDBBean();
                    appDBBean.setAppName("刷新");
                    appDBBean.setPackageName("");
                    appDBBean.setIcon(new BitmapDrawable(BitmapFactory.decodeResource(context.getResources(), R.drawable.app)));
                    o.add(appDBBean);
                    for (int i = 0; i < list.size(); i++) {
                        LogUtil.E(list.get(i).getAppName() + "----" + list.get(i).getPosition());
                        list.get(i).setPosition(i);
                    }
                    appsDBOpe.save(o);
                    list.addAll(o);
                    ll.clear();
                    ll = sortList(list);
                    listener.onFinish(ll);
                }
            });
        } else {
            final PackageManager pm = context.getPackageManager();
            for (int i = 0; i < list.size(); i++) {
                try {
                    if (list.get(i).getPosition() == null) {
                        list.get(i).setPosition(i);
                    }
                    list.get(i).setIcon(pm.getApplicationIcon(list.get(i).getPackageName()));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            ll.clear();
            ll = sortList(list);
            listener.onFinish(ll);
        }
    }

    public void clearData() {
        appsDBOpe.clear();
    }

    public void saveSort() {
        list.clear();
        for (int i = 0; i < ll.size(); i++) {
            ArrayList<AppDBBean> l = ll.get(i);
            for (int j = 0; j < l.size(); j++) {
                ll.get(i).get(j).setPosition(i);
                list.add(ll.get(i).get(j));
            }
        }
        appsDBOpe.save(list);
    }

    public ArrayList<ArrayList<AppDBBean>> sortList(ArrayList<AppDBBean> data) {
        HashMap<Integer, ArrayList<AppDBBean>> map = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            if (map.get(data.get(i).getPosition()) == null) {
                ArrayList<AppDBBean> l = new ArrayList<AppDBBean>();
                l.add(data.get(i));
                map.put(i, l);
            } else {
                map.get(data.get(i).getPosition()).add(data.get(i));
            }
        }
        Integer[] i = new Integer[map.keySet().size()];
        i = map.keySet().toArray(i);
        desc(i);
        ArrayList<ArrayList<AppDBBean>> ll = new ArrayList<>();
        for (int a = 0; a < i.length; a++) {
            ll.add(map.get(i[a]));
        }
        return ll;
    }

    public void desc(Integer[] i) {
        for (int a = 0; a < i.length; a++) {
            for (int b = 0; (b + 1) < (i.length - a); b++) {
                if (i[b] > i[b + 1]) {
                    int x = i[b + 1];
                    i[b + 1] = i[b];
                    i[b] = x;
                }
            }
        }
        for (int z = 0; z < i.length; z++) {
            LogUtil.E(i[z]);
        }
    }


}
