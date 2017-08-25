package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.bean.RemarkBean;

import java.util.ArrayList;

public class UserInfoDAOpe extends BaseDAOpe {


    public UserInfoDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("业务熟练");
        data.add("态度好");
        data.add("专业");
        data.add("解决问题");
        data.add("未解决问题");
        data.add("态度差");

        return data;
    }

    public ArrayList<RemarkBean> getRemarks() {
        ArrayList<RemarkBean> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RemarkBean remarkBean = new RemarkBean();
            remarkBean.setCreated("1992:2:3");
            remarkBean.setRemark("fjds;flkj;;;;;;;;;;;;;;;;;;;;;;;;;;;sgjoignreg;dflkvg;发的发的1了1了1就11了囧记1解耦囧看看看解决1后哦哦卡片机解耦");
            remarkBean.setAgreenum(i);
            data.add(remarkBean);
        }
        return data;
    }
}
