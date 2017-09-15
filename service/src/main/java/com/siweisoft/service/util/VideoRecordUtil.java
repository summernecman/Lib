package com.siweisoft.service.util;

//by summer on 17-09-15.

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.support.annotation.RequiresApi;


public class VideoRecordUtil {

    private static VideoRecordUtil instance;

    private MediaProjectionManager projectionManager;

    private MediaProjection mediaProjection;

    public static VideoRecordUtil getInstance() {
        if (instance == null) {
            instance = new VideoRecordUtil();
        }
        return instance;
    }


    public MediaProjectionManager getMediaProjectionManager(Activity activity) {
        if (projectionManager == null) {
            projectionManager = (MediaProjectionManager) activity.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        }
        return projectionManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startRequest(Activity activity) {

        Intent captureIntent = getMediaProjectionManager(activity).createScreenCaptureIntent();
        activity.startActivityForResult(captureIntent, 111);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == 111) {
            mediaProjection = getMediaProjectionManager(activity).getMediaProjection(resultCode, data);
        }
    }

}
