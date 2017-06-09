package com.summer.lib.base.ope;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.summer.lib.database.DatabaseHelper;

import java.sql.SQLException;

/**
 * Created by ${viwmox} on 2016-05-19.
 */
public class BaseDBOpe<T> {

    protected Context context;
    protected Dao<T, Integer> daoOpe;
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
