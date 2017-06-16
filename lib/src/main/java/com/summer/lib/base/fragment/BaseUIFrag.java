package com.summer.lib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.lib.R;
import com.summer.lib.base.ope.BaseDAOpe;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.LogUtil;
import com.summer.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by summer on 2016/4/16 0016 16:03.
 */
public abstract class BaseUIFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseFrg implements View.OnClickListener, View.OnLongClickListener {

    /**
     * fragment所属的层次
     */
    protected int index;

    Unbinder unbinder;
    /**fragment的操作类*/
    BaseOpes<A,B> opes;

    public BaseUIFrag() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            index = getArguments().getInt(ValueConstant.FRAG_POSITION);
        }
        View group = inflater.inflate(getLayoutID(), null);
        ViewGroup parent = (ViewGroup) group.findViewById(R.id.container);
        if(getOpes().getUiOpe()!=null && getOpes().getUiOpe().getUiBean().itemView!=null){
            parent.addView(getOpes().getUiOpe().getUiBean().itemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        unbinder = ButterKnife.bind(this, group);
        return group;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    /**重新此方法获取布局文件*/
    public int getLayoutID() {
        return R.layout.layout_baseui;
    }

    /**获取操作类*/
    public BaseOpes<A, B> getOpes() {
        if (opes == null) {
            Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Class<B> b = (Class<B>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
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
        }
        return opes;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    public void onResult(int req, Bundle bundle) {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(MessageEvent event) {
        LogUtil.E(event.dealer + ":" + getClass().getName());
        if (!event.dealer.equals(getClass().getName())) {
            return;
        }
    }
}
