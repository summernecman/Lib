package com.android.lib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.android.lib.constant.ValueConstant;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.io.File;
import java.util.ArrayList;

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
        if (context == null || pkg == null) {
            return;
        }
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
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            fragment.startActivityForResult(intent, requstCode);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            fragment.startActivityForResult(intent, requstCode);
        }

    }

    public void photosShowFromphone(Fragment fragment, int requstCode) {
        Intent intent = new Intent(fragment.getContext(), ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
        fragment.startActivityForResult(intent, requstCode);
    }

    public void share(Fragment fragment, ArrayList<String> str) {
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (int i = 0; i < imageUris.size(); i++) {
            Uri imageUri = Uri.fromFile(new File(str.get(i)));
            imageUris.add(imageUri);
        }

        Intent intent3 = new Intent();
        intent3.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent3.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent3.setType("image/*");
        fragment.startActivity(Intent.createChooser(intent3, "share"));
    }

    public void shareImage(Fragment fragment, String str) {
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(new File(str));
        intent2.putExtra(Intent.EXTRA_STREAM, uri);
        intent2.setType("image/*");
        fragment.startActivity(Intent.createChooser(intent2, "share"));
    }


}
