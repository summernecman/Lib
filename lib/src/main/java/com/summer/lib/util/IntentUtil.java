package com.summer.lib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.summer.lib.constant.ValueConstant;

import java.io.File;

//import com.siweisoft.imga.constant.ValueConstant;

/**
 * 跳转
 * Created by SWSD on 2016/4/18.
 */
public class IntentUtil {

    private static IntentUtil instance;

    private static Uri uri;

    public static IntentUtil getInstance() {
        if (instance == null) {
            instance = new IntentUtil();
        }
        return instance;
    }

    public static Uri getUri() {
        return uri;
    }

    /**
     * 拍照返回图片
     *
     * @param activity
     */
    public Uri takeGetPhoto(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = getPhotoFileFolder();
        if (f == null) {
            return null;
        }
        File file = new File(f, System.currentTimeMillis() + ".jpg");
        if (!f.exists()) {
            f.mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        activity.startActivityForResult(intent, ValueConstant.CODE_REQUSET_TAKE_PHOTO);
        uri = Uri.fromFile(file);
        return uri;
    }

    public File getPhotoFileFolder() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File f = new File(Environment.getExternalStorageDirectory(), "/IMGA/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public File getPhotoShortFileFolder() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File f = new File(getPhotoFileFolder(), "/Thumbnails/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public void IntentTo(Context context, String pkg) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkg, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            Intent intent = new Intent();
            intent = context.getPackageManager().getLaunchIntentForPackage(pkg);
            if (intent != null) {
                context.startActivity(intent);
            }
        }
    }

    public void uninstall(Context context, String pkg) {
        Uri packageURI = Uri.parse("package:" + pkg);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }

    public void photoShowFromphone(Activity activity, int requstCode) {
        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
        getImage.addCategory(Intent.CATEGORY_OPENABLE);
        getImage.setType("image/*");
        activity.startActivityForResult(getImage, requstCode);
    }

    public void photoShowFromphone(Fragment fragment, int requstCode) {
        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
        getImage.addCategory(Intent.CATEGORY_OPENABLE);
        getImage.setType("image/*");
        fragment.startActivityForResult(getImage, requstCode);
    }
}
