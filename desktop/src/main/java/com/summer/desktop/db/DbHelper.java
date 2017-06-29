package com.summer.desktop.db;

//by summer on 2017-06-09.

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.lib.database.DatabaseHelper;
import com.android.lib.util.LogUtil;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.bean.dbbean.AppDBBean;

import java.sql.SQLException;

public class DbHelper extends DatabaseHelper {

    protected static String TABLE_NAME = "sqlite-desktop.db";

    protected static DbHelper instance;


    private DbHelper(Context context) {
        super(context, TABLE_NAME, null, 6);
    }

    public static synchronized DbHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null)
                    instance = new DbHelper(context);
            }
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, AppDBBean.class);
            TableUtils.createTableIfNotExists(connectionSource, TimeBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            LogUtil.E("onUpgrade");
            TableUtils.dropTable(connectionSource, TimeBean.class, true);
            TableUtils.createTableIfNotExists(connectionSource, AppDBBean.class);
            TableUtils.createTableIfNotExists(connectionSource, TimeBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
