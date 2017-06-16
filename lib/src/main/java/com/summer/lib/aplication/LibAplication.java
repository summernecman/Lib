package com.summer.lib.aplication;

import android.app.Activity;
import android.app.Application;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.summer.lib.R;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.exception.exception.CrashHander;
import com.summer.lib.util.ScreenUtil;
import com.summer.lib.view.image.ImagePickerLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * 用于一些跟应用程序生命周期一致的处理
 */
public class LibAplication extends Application {

    /**
     * 键值对存储activity
     */
    HashMap<String, Activity> actMap = new HashMap<>();
    /**列表存储activity*/
    ArrayList<Activity> acts = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    /**
     * 初始化application设置
     */
    protected void initApplication() {
        initImageLoader();
        initSysConfig();
        initImagePicker();
        initCrash();
    }

    /**
     * 初始化图片加载了imageloader
     */
    public void initImageLoader(){
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    /**初始化系统常量*/
    public void initSysConfig(){
        ScreenUtil.getInstance().getScreenSize(getApplicationContext());
        ScreenUtil.getInstance().getStatusBarHeight(getApplicationContext());
        ValueConstant.DIMEN_1 = (int) getResources().getDimension(R.dimen.dimen_1);
    }

    /**初始化图片加载了imagepicker图片选择器*/
    public void initImagePicker(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImagePickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    /**
     * 系统报错处理
     */
    public void initCrash() {
        CrashHander.getInstance().init(this);
    }

    /**
     * 退出结束所有界面
     */
    public void exit() {
        Iterator iterator = actMap.keySet().iterator();
        while (iterator.hasNext()) {
            actMap.get(iterator.next()).finish();
        }
        actMap.clear();
    }

    public HashMap<String, Activity> getActMap() {
        return actMap;
    }

    public ArrayList<Activity> getActs() {
        return acts;
    }
}
