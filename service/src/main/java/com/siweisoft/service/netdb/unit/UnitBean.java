package com.siweisoft.service.netdb.unit;

//by summer on 17-09-11.

import com.android.lib.bean.BaseBean;

public class UnitBean extends BaseBean {

    private int id;

    private int unittype;

    private String unitname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnittype() {
        return unittype;
    }

    public void setUnittype(int unittype) {
        this.unittype = unittype;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    @Override
    public String toString() {
        return "UnitBean{" +
                "id=" + id +
                ", unittype=" + unittype +
                ", unitname='" + unitname + '\'' +
                '}';
    }
}
