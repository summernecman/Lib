package com.siweisoft.service.ui.Constant;

//by summer on 2017-07-04.

import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class Value extends ValueConstant {


    public static int ROOTID = R.id.serviceroot;

    public static int ROOTID_ONE = R.id.one;

    public static int ROOTID_TWO = R.id.two;

    public static int ROOTID_THREE = R.id.three;

    public static int position = 0;

    public static ArrayList<Integer> root = new ArrayList<>();
    public static UserBean userBean = new UserBean();

    static {
        root.add(ROOTID_ONE);
        root.add(ROOTID_TWO);
        root.add(ROOTID_THREE);
    }

    public static Integer getNowRoot() {
        return root.get(position);
    }

    public static void setPostion(int postion) {
        Value.position = postion;
    }

}
