package com.android.lib.database.sysdb;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by ${viwmox} on 2016-08-01.
 */
public class ProviderDBHelper {

    private static ProviderDBHelper instance;

    public static ProviderDBHelper getInstance() {
        if (instance == null) {
            instance = new ProviderDBHelper();
        }
        return instance;
    }

    public Cursor query(Context context, Uri uri, String[] Projection, String selection, String[] selectionArgs, String SortOrder) {
        Cursor cursor = context.getContentResolver().query(uri, Projection, selection, selectionArgs, SortOrder);
        return cursor;
    }
}
