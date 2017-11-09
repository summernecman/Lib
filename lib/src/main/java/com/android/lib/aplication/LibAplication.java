package com.android.lib.aplication;

import android.app.Application;
import android.support.v4.app.FragmentActivity;

import com.android.lib.R;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.SPUtil;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.activity.ActivityUtil;
import com.android.lib.view.image.ImagePickerLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import org.xutils.x;

import java.util.List;


/**
 * 用于一些跟应用程序生命周期一致的处理
 */
public class LibAplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    /**
     * 初始化application设置
     */
    protected void initApplication() {
        initSysConfig();
        initImagePicker();
        initXutils();
    }


    /**
     * 初始化系统常量
     */
    public void initSysConfig() {
        ScreenUtil.getInstance().getScreenSize(getApplicationContext());
        ScreenUtil.getInstance().getStatusBarHeight(getApplicationContext());
        ValueConstant.DIMEN_1 = (int) getResources().getDimension(R.dimen.dimen_1);
        SPUtil.getInstance().init(this);
    }

    /**
     * 初始化图片加载了imagepicker图片选择器
     */
    public void initImagePicker() {
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

    public void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
    }

    /**
     * 退出结束所有界面
     */
    public void exit() {
        List<FragmentActivity> list = ActivityUtil.getInstance().getActList();
        for (int i = 0; i < list.size(); i++) {
            //FragmentUtil2.getInstance().initClear(list.get(i));
            FragmentUtil2.getInstance().clear();
        }
        ActivityUtil.getInstance().destoryActs();
        //System.gc();
    }

}
