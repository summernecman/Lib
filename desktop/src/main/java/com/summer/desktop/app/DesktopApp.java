package com.summer.desktop.app;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.summer.lib.aplication.LibAplication;
import com.summer.lib.util.ScreenUtil;

import cn.bmob.v3.Bmob;

/**
 * Created by summer on 2017/5/25 23:47.
 */

public class DesktopApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

        Bmob.initialize(this, "a372099e1546f084af11ba4cfc1b8439");

        ScreenUtil.getInstance().getScreenSize(getApplicationContext());
    }


}
