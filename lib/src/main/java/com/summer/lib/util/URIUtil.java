package com.summer.lib.util;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

/**
 * Created by ${viwmox} on 2016-08-02.
 */
public class URIUtil {
    private static URIUtil instance;

    public static URIUtil getInstance() {
        if (instance == null) {
            instance = new URIUtil();
        }
        return instance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getPath(Context context, Uri uri) {
//        uri=Uri.parse("content://com.android.providers.media.documents/document/image%3A128989");
//        FileUtil.getInstance().saveToFile(getClass().getSimpleName() + ":getPath:" + (uri == null ? uri : uri.toString()));
        if (uri == null) {
            return null;
        }

        String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else {
            if (ContentResolver.SCHEME_FILE.equals(scheme)) {
                data = uri.getPath();
            } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    String wholeID = DocumentsContract.getDocumentId(uri);
                    String id = wholeID.split(":")[1];
                    String[] column = {MediaStore.Images.Media.DATA};
                    String sel = MediaStore.Images.Media._ID + " =? ";
                    Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column,
                            sel, new String[]{id}, null);
                    int columnIndex = cursor.getColumnIndex(column[0]);
                    if (cursor.moveToFirst()) {
                        data = cursor.getString(columnIndex);
                    }
                    cursor.close();
                } else {
                    Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
                    if (null != cursor) {
                        if (cursor.moveToFirst()) {
                            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                            if (index > -1) {
                                data = cursor.getString(index);
                            }
                        }
                        cursor.close();
                    }
                }

            } else {
                data = null;
            }
        }
        return data;
    }
}
