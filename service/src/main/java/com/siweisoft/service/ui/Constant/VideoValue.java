package com.siweisoft.service.ui.Constant;

//by summer on 2017-07-06.

import android.os.Environment;

import java.io.File;

public class VideoValue {

    public static File getRecordFileDir() {
        File file = new File(Environment.getExternalStorageDirectory(), "videorecord");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
