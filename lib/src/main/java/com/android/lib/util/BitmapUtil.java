package com.android.lib.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import com.android.lib.base.interf.OnFinishListener;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * 图像处理的工具类
 */
public class BitmapUtil {

    private static BitmapUtil instance;

    private BitmapUtil() {

    }

    /**
     * 图像处理工具类单例模式
     */
    public static BitmapUtil getInstance() {
        if (instance == null) {
            instance = new BitmapUtil();
        }
        return instance;
    }

    public static void saveImage(final Context context, final String url) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                String s[] = url.split("/");
                File dir = new File(Environment.getExternalStorageDirectory() + "/files");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, s[s.length - 1]);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream fileOutputStream = null;
                FileInputStream fileInputStream = null;
                HttpURLConnection conn = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    URL u = new URL(url);
                    conn = (HttpURLConnection) u.openConnection();
                    InputStream input = conn.getInputStream();
                    byte[] bytes = new byte[1024];
                    int bytec = input.read(bytes);
                    while (bytec != -1) {
                        fileOutputStream.write(bytes, 0, bytec);
                        bytec = input.read(bytes);
                    }
                    fileOutputStream.flush();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (conn != null) {
                    conn.disconnect();
                }
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                ToastUtil.getInstance().showShort(context, "保存成功");
            }
        }.execute();

    }

    public static void saveImage(final Context context, final String src, final String url) {
        final File file = new File(src);
        if (file.exists()) {
            return;
        }
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {

                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream fileOutputStream = null;
                FileInputStream fileInputStream = null;
                HttpURLConnection conn = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    URL u = new URL(url);
                    conn = (HttpURLConnection) u.openConnection();
                    InputStream input = conn.getInputStream();
                    byte[] bytes = new byte[1024];
                    int bytec = input.read(bytes);
                    while (bytec != -1) {
                        fileOutputStream.write(bytes, 0, bytec);
                        bytec = input.read(bytes);
                    }
                    fileOutputStream.flush();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (conn != null) {
                    conn.disconnect();
                }
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                //ToastUtil.getInstance().showShort(context, "保存成功");
            }
        }.execute();

    }

    public static void saveImage(final Context context, final String src, final String url, final OnFinishListener listener) {
        final File file = new File(src);
        if (file.exists()) {
            return;
        }
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {

                if (!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream fileOutputStream = null;
                FileInputStream fileInputStream = null;
                HttpURLConnection conn = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    URL u = new URL(url);
                    conn = (HttpURLConnection) u.openConnection();
                    InputStream input = conn.getInputStream();
                    byte[] bytes = new byte[1024];
                    int bytec = input.read(bytes);
                    while (bytec != -1) {
                        fileOutputStream.write(bytes, 0, bytec);
                        bytec = input.read(bytes);
                    }
                    fileOutputStream.flush();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (conn != null) {
                    conn.disconnect();
                }
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                //ToastUtil.getInstance().showShort(context, "保存成功");
                listener.onFinish(src);
            }
        }.execute();

    }

    /**
     * 设置imageview的src
     */
    public void setImageSrc(Context context, ImageView imageView, String imageSrc) {
        if (imageView == null) {
            LogUtil.E("imageview is null");
            return;
        }
        if (NullUtil.isStrEmpty(imageSrc)) {
            LogUtil.E("imagesrc is null");
        }
        Glide.with(context).load(imageSrc).into(imageView);
    }

    public ArrayList<File> getThumbnails(ArrayList<File> files) {
        if (files == null) {
            return new ArrayList<File>();
        }
        ArrayList<File> newFiles = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i) == null || files.get(i).getPath() == null || files.get(i).getPath().equals("")) {
                continue;
            }
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(files.get(i).getPath(), newOpts);//此时返回bm为空
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 800f;//这里设置高度为800f
            float ww = 480f;//这里设置宽度为480f
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;//设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(files.get(i).getPath(), newOpts);
            try {
                File file = new File(IntentUtil.getInstance().getPhotoShortFileFolder(), files.get(i).getName());
                newFiles.add(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, new FileOutputStream(file));
                bitmap.recycle();
                bitmap = null;
                System.gc();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return newFiles;
    }

    public Drawable getBgDrawable(Context context) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        // 获取当前壁纸
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        return wallpaperDrawable;
    }


}
