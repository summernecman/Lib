package com.android.lib.database;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by ${viwmox} on 2017-03-06.
 */

public interface DBListener {

    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource);

    public void onUpdate(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion);
}
