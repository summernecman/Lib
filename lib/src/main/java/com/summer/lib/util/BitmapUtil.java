//package com.summer.lib.util;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.summer.lib.R;
//import com.summer.lib.bean.dbbean.ImageDBBean;
//import com.summer.lib.base.interf.OnFinishListener;
//
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//
//
///**
// * Created by summer on 2016/3/20 0020 15:53.
// */
//public class BitmapUtil {
//
//
//    private static BitmapUtil instance;
//
//
//    private BitmapUtil() {
//
//    }
//
//    public static BitmapUtil getInstance() {
//        if (instance == null) {
//            instance = new BitmapUtil();
//        }
//        return instance;
//    }
//
//    public boolean setBg(Context context, ImageView imageView, Uri uri) {
//        //x.image().bind(imageView,uri.toString(),imageOptions);
//        if (uri == null) {
//            return false;
//        }
//        setBg(context, imageView, uri.toString());
//        return true;
//    }
//
//    public boolean setBg(Context context, ImageView imageView, String uri) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).into(imageView);
//            Glide.with(context).load(uri).asGif().fitCenter().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//    public boolean setBg(Context context, final ImageView imageView, String uri, final OnFinishListener onFinishListener) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//            onFinishListener.onFinish(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    imageView.setImageBitmap(resource);
//                    onFinishListener.onFinish(imageView);
//                }
//
//                @Override
//                public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                    super.onLoadFailed(e, errorDrawable);
//                    onFinishListener.onFinish(imageView);
//                }
//            });
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//
//    public boolean setFitBg(Context context, final float w, final ImageView imageView, final String uri) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                    params.height = (int) ((w * resource.getHeight()) / resource.getWidth());
//                    LogUtil.E(imageView.getLayoutParams().width);
//                    params.width = (int) w;
//                    imageView.setLayoutParams(params);
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    imageView.setImageBitmap(resource);
//                    imageView.setTag(R.id.uri, uri);
//                }
//
//                @Override
//                public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                    super.onLoadFailed(e, errorDrawable);
//                }
//            });
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//
//    public boolean setFitBg(Context context, final float w, final ImageView imageView, final String uri, boolean scale) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                    params.height = (int) ((w * resource.getHeight()) / resource.getWidth());
//                    LogUtil.E(imageView.getLayoutParams().width);
//                    params.width = (int) w;
//                    imageView.setLayoutParams(params);
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    imageView.setImageBitmap(resource);
//                    imageView.setTag(R.id.uri, uri);
//                }
//
//                @Override
//                public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                    super.onLoadFailed(e, errorDrawable);
//                }
//            });
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//    public boolean setFitBg(Context context, final float w, final float h, final ImageView imageView, final String uri) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        ViewGroup.LayoutParams params = imageView.getLayoutParams();
//        params.width = (int) w;
//        params.height = (int) h;
//        imageView.setLayoutParams(params);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().override((int) w, (int) h).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//
//    public boolean setmidFitBg(Context context, final float w, final float h, final ImageView imageView, final String uri) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        ViewGroup.LayoutParams params = imageView.getLayoutParams();
//        params.width = (int) w;
//        params.height = (int) h;
//        imageView.setLayoutParams(params);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().override(256, 256).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//    public boolean setFitBg(Context context, final float w, final ImageView imageView, final String uri, final OnFinishListener onFinishListener) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                    params.height = (int) ((w * resource.getHeight()) / resource.getWidth());
//                    LogUtil.E(imageView.getLayoutParams().width);
//                    params.width = (int) w;
//                    imageView.setLayoutParams(params);
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    imageView.setImageBitmap(resource);
//                    imageView.setTag(R.id.uri, uri);
//                    if (onFinishListener != null) {
//                        onFinishListener.onFinish(new ImageDBBean((int) w, params.height, uri));
//                    }
//                }
//
//                @Override
//                public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                    super.onLoadFailed(e, errorDrawable);
//                }
//            });
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//
//    public boolean setFitBg(boolean override, Context context, final float w, final ImageView imageView, final String uri, final OnFinishListener onFinishListener) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        if (uri.toLowerCase().endsWith(".gif")) {
//            Glide.with(context).load(uri).asGif().override(256, 256).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        } else {
//            Glide.with(context).load(uri).asBitmap().override(256, 256).diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                    params.height = (int) ((w * resource.getHeight()) / resource.getWidth());
//                    LogUtil.E(imageView.getLayoutParams().width);
//                    params.width = (int) w;
//                    imageView.setLayoutParams(params);
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    imageView.setImageBitmap(resource);
//                    imageView.setTag(R.id.uri, uri);
//                    if (onFinishListener != null) {
//                        onFinishListener.onFinish(new ImageDBBean((int) w, params.height, uri));
//                    }
//                }
//
//                @Override
//                public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                    super.onLoadFailed(e, errorDrawable);
//                }
//            });
//        }
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//
//    public boolean setBgWithThumbnail(Context context, ImageView imageView, String uri) {
//        if (uri == null) {
//            return false;
//        }
//        if (uri.startsWith("http://") || uri.startsWith("https://")) {
//
//        }
////      x.image().bind(imageView,uri.toString(),imageOptions);
//        Glide.clear(imageView);
//        Glide.with(context).load(uri).thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//        LogUtil.E("setBg:" + uri);
//        return true;
//    }
//
//
//    public boolean setBg(Context context, View view, String uri) {
//        if (uri == null) {
//            return false;
//        }
//        String path = URIUtil.getInstance().getPath(context, Uri.parse(uri));
//        if (path == null) {
//            return false;
//        }
//        view.setBackgroundDrawable(BitmapDrawable.createFromPath(path));
//        return true;
//    }
//
//    public boolean setBg(Context activity, ImageView imageView, int resid) {
//        Glide.with(activity).load(resid).into(imageView);
//        return false;
//    }
//
//    public boolean setGifBg(Context activity, ImageView imageView, int resid) {
//        Glide.with(activity).load(resid).asGif().into(imageView);
//        return false;
//    }
//
//
//    public Bitmap getBitmap(Context activity, int resId) {
//        return BitmapFactory.decodeResource(activity.getResources(), resId);
//    }
//
//    public ArrayList<File> getThumbnails(ArrayList<File> files) {
//        if (files == null) {
//            return new ArrayList<File>();
//        }
//        ArrayList<File> newFiles = new ArrayList<>();
//        for (int i = 0; i < files.size(); i++) {
//            if (files.get(i) == null || files.get(i).getPath() == null || files.get(i).getPath().equals("")) {
//                continue;
//            }
//            BitmapFactory.Options newOpts = new BitmapFactory.Options();
//            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
//            newOpts.inJustDecodeBounds = true;
//            Bitmap bitmap = BitmapFactory.decodeFile(files.get(i).getPath(), newOpts);//此时返回bm为空
//            newOpts.inJustDecodeBounds = false;
//            int w = newOpts.outWidth;
//            int h = newOpts.outHeight;
//            //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//            float hh = 800f;//这里设置高度为800f
//            float ww = 480f;//这里设置宽度为480f
//            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//            int be = 1;//be=1表示不缩放
//            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//                be = (int) (newOpts.outWidth / ww);
//            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//                be = (int) (newOpts.outHeight / hh);
//            }
//            if (be <= 0)
//                be = 1;
//            newOpts.inSampleSize = be;//设置缩放比例
//            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//            bitmap = BitmapFactory.decodeFile(files.get(i).getPath(), newOpts);
//            try {
//                File file = new File(IntentUtil.getInstance().getPhotoShortFileFolder(), files.get(i).getName());
//                newFiles.add(file);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, new FileOutputStream(file));
//                bitmap.recycle();
//                bitmap = null;
//                System.gc();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return newFiles;
//    }
//}
