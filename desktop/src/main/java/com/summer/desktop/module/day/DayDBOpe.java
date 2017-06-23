package com.summer.desktop.module.day;

//by summer on 2017-06-21.

import android.content.Context;

import com.android.lib.base.ope.BaseDBOpe;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.db.DbHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DayDBOpe extends BaseDBOpe<TimeBean> {

    public DayDBOpe(Context context) {
        super(context, DbHelper.getHelper(context), new TimeBean());
    }

    public void add(TimeBean timeBean) {
        try {
            daoOpe.create(timeBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(final ArrayList<TimeBean> times) {
        try {
            daoOpe.callBatchTasks(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    for (int i = 0; times != null && i < times.size(); i++) {
                        add(times.get(i));
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TimeBean> getList() {
        try {
            return daoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<TimeBean>();
    }

    public List<TimeBean> getList(String str) {
        QueryBuilder q = daoOpe.queryBuilder();
        try {
            q.where().eq("str", str);
            return q.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public void delete(long id) {
        DeleteBuilder builder = daoOpe.deleteBuilder();
        try {
            builder.where().eq(TimeBean.ID, id);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String str) {
        DeleteBuilder builder = daoOpe.deleteBuilder();
        try {
            builder.where().eq("str", str);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void clear() {
        try {
            daoOpe.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateName(String str, String name) {
        List<TimeBean> times = getList(str);
        if (times.size() == 0) {
            return;
        }
        UpdateBuilder u = daoOpe.updateBuilder();
        Where w = u.where();
        try {
            w.eq("str", str);
            u.updateColumnValue("text", name);
            times.get(0).setText(name);
            u.updateColumnValue("str", times.get(0).toString());
            u.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
