package com.summer.lib.base.ope;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.summer.lib.database.DatabaseHelper;

import java.sql.SQLException;

/**
 * 数据库操作类
 */
public class BaseDBOpe<T> {

    /**
     * 上下文
     */
    protected Context context;
    /**数据库操作者*/
    protected Dao<T, Integer> daoOpe;
    /**数据库helper*/
    protected DatabaseHelper helper;

    public BaseDBOpe(Context context, T t) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            daoOpe = helper.getDao(t.getClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BaseDBOpe(Context context, DatabaseHelper helper, T t) {
        this.context = context;
        this.helper = helper;
        try {
            daoOpe = helper.getDao(t.getClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
