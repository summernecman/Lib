package com.summer.desktop.bean.dabean;

//by summer on 2017-06-13.

import com.android.lib.bean.dbbean.BaseDbBean;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "timebean")
public class TimeBean extends BaseDbBean {

    public static final String TYPE_DAY = "type_day";
    public static final String TYPE_EVERYDAY = "type_everyday";
    @DatabaseField(columnName = "sh")
    public int sh;
    @DatabaseField(columnName = "sm")
    public int sm;
    @DatabaseField(columnName = "eh")
    public int eh;
    @DatabaseField(columnName = "em")
    public int em;
    @DatabaseField(columnName = "type")
    public String type;
    @DatabaseField(columnName = "text")
    public String text = "新建时段";
    @DatabaseField(columnName = "str")
    public String str;


    public TimeBean(int sh, int sm, int eh, int em) {
        this.sh = sh;
        this.sm = sm;
        this.eh = eh;
        this.em = em;
        this.type = TYPE_DAY;
        str = toString();
    }

    public TimeBean(int sh, int sm, int eh, int em, String type) {
        this.sh = sh;
        this.sm = sm;
        this.eh = eh;
        this.em = em;
        this.type = type;
        str = toString();
    }

    public TimeBean() {

    }

    public int getSh() {
        return sh;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }

    public int getSm() {
        return sm;
    }

    public void setSm(int sm) {
        this.sm = sm;
    }

    public int getEh() {
        return eh;
    }

    public void setEh(int eh) {
        this.eh = eh;
    }

    public int getEm() {
        return em;
    }

    public void setEm(int em) {
        this.em = em;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return type + "" + sh + ":" + sm + "--" + eh + ":" + em + "---" + text;
    }
}
