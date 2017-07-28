package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragBaogongBinding;
import com.summer.factory.databinding.FragWuliaoBinding;
import com.summer.factory.databinding.ItemRecycleBinding;
import com.summer.factory.databinding.ItemTextBinding;
import com.summer.factory.databinding.ItemTextEditBinding;
import com.summer.factory.databinding.ItemTextEditTxtBinding;
import com.summer.factory.databinding.ItemTitle1Binding;
import com.summer.factory.databinding.ItemTitle3Binding;
import com.summer.factory.databinding.ItemTitle4Binding;
import com.summer.factory.databinding.ItemTitleTwoBinding;
import com.summer.factory.databinding.ItemTxtSpiCheTxtBinding;
import com.summer.factory.databinding.ItemWuliaoBottom2Binding;
import com.summer.factory.databinding.ItemWuliaoBottomBinding;

import java.util.ArrayList;

public class WuLiaoUIOpe extends BaseUIOpe<FragWuliaoBinding> {


    ItemTextEditBinding itemTextEditBinding;

    ItemTitleTwoBinding itemTitleTwoBinding;

    ItemRecycleBinding itemRecycleBinding;

    ItemWuliaoBottomBinding itemWuliaoBottomBinding;

    ItemTxtSpiCheTxtBinding itemTxtSpiCheTxtBinding;

    ItemWuliaoBottom2Binding itemWuliaoBottom2Binding;

    ItemTextBinding itemTextBinding;

    ItemTextEditTxtBinding itemTextEditTxtBinding;

    FragBaogongBinding fragBaogongBinding;

    ItemTitle1Binding itemTitle1Binding;

    public WuLiaoUIOpe(Context context) {
        super(context);
    }

    public void initTitleTxtEdit(LayoutDABean bean) {
        itemTextEditBinding = ItemTextEditBinding.inflate(LayoutInflater.from(context), bind.llTop, false);
        bind.llTop.addView(itemTextEditBinding.getRoot());
        itemTextEditBinding.setItemtextedit(bean);
    }

    public void initTitleTxt(LayoutDABean bean) {
        ItemTextBinding itemTextBinding = ItemTextBinding.inflate(LayoutInflater.from(context), bind.llTop, false);
        bind.llTop.addView(itemTextBinding.getRoot());
        itemTextBinding.setItemtext(bean);
    }

    public void initTitleTxtEditTxt(LayoutDABean bean) {
        itemTextEditTxtBinding = ItemTextEditTxtBinding.inflate(LayoutInflater.from(context), bind.llTop, false);
        bind.llTop.addView(itemTextEditTxtBinding.getRoot());
        itemTextEditTxtBinding.setItemtextedittxt(bean);
    }

    public void initTitleTxtSpiCheckTxt(LayoutDABean bean) {
        itemTxtSpiCheTxtBinding = ItemTxtSpiCheTxtBinding.inflate(LayoutInflater.from(context), bind.llTop, false);
        bind.llTop.addView(itemTxtSpiCheTxtBinding.getRoot());
        itemTxtSpiCheTxtBinding.setItemtxtspichetxt(bean);
    }

    public void initText3(LayoutDABean bean) {
        ItemTitle3Binding itemTitle3Binding = ItemTitle3Binding.inflate(LayoutInflater.from(context), bind.llTitle, false);
        bind.llTitle.addView(itemTitle3Binding.getRoot());
        itemTitle3Binding.setItemtitle3(bean);
    }

    public void initText4(LayoutDABean bean) {
        ItemTitle4Binding itemTitle4Binding = ItemTitle4Binding.inflate(LayoutInflater.from(context), bind.llTitle, false);
        bind.llTitle.addView(itemTitle4Binding.getRoot());
        itemTitle4Binding.setItemtitle4(bean);
    }

    public void initText2(LayoutDABean bean) {
        itemTitleTwoBinding = ItemTitleTwoBinding.inflate(LayoutInflater.from(context), bind.llTitle, false);
        bind.llTitle.addView(itemTitleTwoBinding.getRoot());
        itemTitleTwoBinding.setItemtitletwo(bean);
    }

    public void initText1(LayoutDABean bean) {
        itemTitle1Binding = ItemTitle1Binding.inflate(LayoutInflater.from(context), bind.llTitle, false);
        bind.llTitle.addView(itemTitle1Binding.getRoot());
        itemTitle1Binding.setItemtitle1(bean);
    }

    public void initRecycle() {
        itemRecycleBinding = ItemRecycleBinding.inflate(LayoutInflater.from(context), bind.llMid, false);
        bind.llMid.addView(itemRecycleBinding.getRoot());
    }

    public void initBottom(LayoutDABean bean) {
        itemWuliaoBottomBinding = ItemWuliaoBottomBinding.inflate(LayoutInflater.from(context), bind.llBottom, false);
        bind.llBottom.addView(itemWuliaoBottomBinding.getRoot());
        itemWuliaoBottomBinding.setItemwuliaobottom(bean);
    }

    public void initBottom2(LayoutDABean bean) {
        itemWuliaoBottom2Binding = ItemWuliaoBottom2Binding.inflate(LayoutInflater.from(context), bind.llBottom, false);
        bind.llBottom.addView(itemWuliaoBottom2Binding.getRoot());
        itemWuliaoBottom2Binding.setItemwuliaobottom2(bean);
    }

    public void initBottom3(LayoutDABean bean) {
        itemTextEditBinding = ItemTextEditBinding.inflate(LayoutInflater.from(context), bind.llBottom, false);
        bind.llBottom.addView(itemTextEditBinding.getRoot());
        itemTextEditBinding.setItemtextedit(bean);
    }

    public void initBottom4(LayoutDABean bean) {
        itemTextBinding = ItemTextBinding.inflate(LayoutInflater.from(context), bind.llBottom, false);
        bind.llBottom.addView(itemTextBinding.getRoot());
        itemTextBinding.setItemtext(bean);
    }

    public void initBottom5(LayoutDABean bean) {
        ItemTextEditTxtBinding itemTextEditTxtBinding = ItemTextEditTxtBinding.inflate(LayoutInflater.from(context), bind.llBottom, false);
        bind.llBottom.addView(itemTextEditTxtBinding.getRoot());
        itemTextEditTxtBinding.setItemtextedittxt(bean);
    }


    public void initRecycle3(ArrayList<LayoutDABean> list) {
        itemRecycleBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        itemRecycleBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_title_3, BR.itemtitle3, list));
    }

    public void initRecycle4(ArrayList<LayoutDABean> list) {
        itemRecycleBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        itemRecycleBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_title_4, BR.itemtitle4, list));
    }

    public void initRecycle2(ArrayList<LayoutDABean> list) {
        itemRecycleBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        itemRecycleBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_title_two, BR.itemtitletwo, list));
    }

    public void initRecycle1(ArrayList<LayoutDABean> list) {
        itemRecycleBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        itemRecycleBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_title_1, BR.itemtitle1, list));
    }

    public void addBangGongItem(LayoutDABean bean) {
        fragBaogongBinding = FragBaogongBinding.inflate(LayoutInflater.from(context), bind.llTitle, false);
        bind.llTitle.addView(fragBaogongBinding.getRoot());
        fragBaogongBinding.setBaogong(bean);
    }

    public void addWorkSiteCheck(LayoutDABean bean) {
        fragBaogongBinding = FragBaogongBinding.inflate(LayoutInflater.from(context), bind.llTitle, false);
        bind.llTitle.addView(fragBaogongBinding.getRoot());
        fragBaogongBinding.setBaogong(bean);
    }

}
