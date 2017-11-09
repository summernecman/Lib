package com.android.lib.base.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.android.lib.R;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseOpes;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

/**
 * Created by summer on 2016/4/16 0016 11:51.
 */
public abstract class BaseUIActivity<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseActivity {

    /**
     * 添加内容界面的容器
     */
    protected ViewGroup ACT_ROOT_VIEW;

    /**
     * 操作类
     */
    protected BaseOpes<A, B> opes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFullScreen(isFullScreen());
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
//        StatusBarUtil.getInstance().setStatusBarColor(activity, ColorConstant.COLOR_STATUS);
//        StatusBarUtil.getInstance().hideNavigationBar(activity);
        ACT_ROOT_VIEW = (ViewGroup) findViewById(R.id.act_base_root);
        if (getP().getU() != null && getP().getU().getBind().getRoot() != null) {
            ACT_ROOT_VIEW.addView(getP().getU().getBind().getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ButterKnife.bind(activity);
    }

    /**
     * 重新此方法获取布局文件
     */
    public int getLayoutID() {
        return R.layout.layout_baseui_withouttitle;
    }

    /**
     * 获取操作类
     */
    public BaseOpes<A, B> getP() {
        if (opes == null) {
            getaabb(getClass());
        }
        return opes;
    }

    private BaseOpes<A, B> getaabb(Class<?> c) {
        if (c == null) {
            opes = (BaseOpes<A, B>) new BaseOpes<>(new BaseUIOpe<ViewDataBinding>(activity), new BaseDAOpe(activity));
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            Class<B> b = (Class<B>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor<A> ac = a.getConstructor(Context.class);
                Constructor<B> bc = b.getConstructor(Context.class);
                A aa = ac.newInstance(activity);
                B bb = bc.newInstance(activity);
                opes = new BaseOpes<>(aa, bb);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            getaabb(c.getSuperclass());
        }

        return opes;
    }

    /**
     * 重写此方法设置是否全屏
     */
    public boolean isFullScreen() {
        return false;
    }

    private void isFullScreen(boolean is) {
        if (is) {
            // 隐藏标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // 隐藏状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    /**
     * 获取activity容器根布局
     */
    public ViewGroup getACT_ROOT_VIEW() {
        return ACT_ROOT_VIEW;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(MessageEvent event) {
        LogUtil.E(getClass().getName());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
