package com.summer.desktop.db.ope;

//by summer on 2017-06-09.

import android.content.Context;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.db.DbHelper;
import com.summer.lib.base.ope.BaseDBOpe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class AppsDBOpe extends BaseDBOpe<AppDBBean> {


    public AppsDBOpe(Context context, AppDBBean appDBBean) {
        super(context, DbHelper.getHelper(context), appDBBean);
    }

    public ArrayList<AppDBBean> getApps() {
        ArrayList<AppDBBean> ll = new ArrayList<>();
        try {
            QueryBuilder queryBuilder = daoOpe.queryBuilder();
            queryBuilder.orderBy(AppDBBean.POSITION, true);
            ArrayList<AppDBBean> list = (ArrayList<AppDBBean>) queryBuilder.query();
            if (list != null && list.size() > 0) {
                ll.addAll(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ll;
    }

    public void save(final ArrayList<AppDBBean> list) {
        if (list == null) {
            return;
        }

        try {
            daoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    DeleteBuilder deleteBuilder = daoOpe.deleteBuilder();
                    Where where = deleteBuilder.where();
                    try {
                        where.isNotNull(AppDBBean.APPNAME);
                        deleteBuilder.delete();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < list.size(); i++) {
                        final int finalI = i;
                        try {
                            daoOpe.create(list.get(finalI));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
