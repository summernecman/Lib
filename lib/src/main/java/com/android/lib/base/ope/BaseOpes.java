package com.android.lib.base.ope;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2016-12-21.
 */

public class BaseOpes<A extends BaseUIBean, B extends BaseDAOpe> implements Serializable {

    /**
     * 操作者
     */
    A ui;
    /**
     * 数据操作者
     */
    B da;

    public BaseOpes(A ui, B da) {
        this.ui = ui;
        this.da = da;
    }

    public B getDa() {
        return da;
    }

    public void setDa(B da) {
        this.da = da;
    }

    public A getUi() {
        return ui;
    }

    public void setUi(A ui) {
        this.ui = ui;
    }
}
