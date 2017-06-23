package com.android.lib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.android.lib.bean.databean.ImageDataBean;


/**
 * Created by ${viwmox} on 2016-07-12.
 */
public class PhotoUtil {

    private static final String[] QUERY_COLUMNS = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DISPLAY_NAME
    };
    private static PhotoUtil instance;

    public static PhotoUtil getInstance() {
        if (instance == null) {
            instance = new PhotoUtil();
        }
        return instance;
    }

    public void takePhoto(Activity activity, int requstcode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, requstcode);
    }

    public ImageDataBean getData(Context context, Intent dataIntent) {
        if (context == null || dataIntent == null) {
            return null;
        }
        Uri selectedImage = dataIntent.getData();
        Cursor cursor = null;
        ImageDataBean imageDataBean = null;
        try {
            cursor = context.getContentResolver().query(selectedImage, QUERY_COLUMNS, null, null, null);
            if (cursor.moveToFirst()) {
                String path = cursor.getString(cursor.getColumnIndex(QUERY_COLUMNS[1]));
                String fileName = cursor.getString(cursor.getColumnIndex(QUERY_COLUMNS[3]));
                String mimeType = cursor.getString(cursor.getColumnIndex(QUERY_COLUMNS[2]));
                imageDataBean = new ImageDataBean(fileName, mimeType, path);
            }

        } catch (Exception e) {
            LogUtil.E(e);

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return imageDataBean;
    }
}
