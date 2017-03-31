package com.summer.lib.view.weekview.dayview.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.summer.lib.bean.dbbean.BaseDbBean;

/**
 * Created by ${viwmox} on 2016-11-04.
 */

@DatabaseTable(tableName = "table_plantip")
public class PlanTipBean extends BaseDbBean {


    public static final String STAR_HOUR = "STAR_HOUR";
    @DatabaseField(columnName = STAR_HOUR)
    private int starHour;

    public static final String STAR_MINUTE = "STAR_MINUTE";
    @DatabaseField(columnName = STAR_MINUTE)
    private int starMinite;


    public static final String END_HOUR = "END_HOUR";
    @DatabaseField(columnName = END_HOUR)
    private int endHour;


    public static final String END_MINUTE = "END_MINUTE";
    @DatabaseField(columnName = END_MINUTE)
    private int endMinite;


    public static final String BG_COLOR = "BG_COLOR";
    @DatabaseField(columnName = BG_COLOR)
    private int bgColor;

    public static final String BORDER_COLOR = "BORDER_COLOR";
    @DatabaseField(columnName = BORDER_COLOR)
    private int borderColor;

    public static final String TXT = "TXT";
    @DatabaseField(columnName = TXT)
    private String txt;

    public static final String TITLE = "TITLE";
    @DatabaseField(columnName = TITLE)
    private String title;


    public static final String WIDTH = "WIDTH";
    @DatabaseField(columnName = WIDTH)
    private int width;

    public static final String SELECT = "SELECT";
    @DatabaseField(columnName = SELECT)
    private boolean select = false;

    public int getStartY(int eachY) {
        if (starHour >= 6) {
            return (int) (((float) ((starHour - 6) * 60 + starMinite) * eachY) / 60);
        } else {
            return (int) (((float) ((starHour + 19) * 60 + starMinite) * eachY) / 60);
        }

    }

    public int getEndY(int eachY) {
        if (endHour >= 6) {
            return (int) (((float) ((endHour - 6) * 60 + endMinite) * eachY) / 60);
        } else {
            return (int) (((float) ((endHour + 19) * 60 + endMinite) * eachY) / 60);
        }
    }

    public int getHeight(int eachY) {
        return getEndY(eachY) - getStartY(eachY);
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinite() {
        return endMinite;
    }

    public void setEndMinite(int endMinite) {
        this.endMinite = endMinite;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getStarHour() {
        return starHour;
    }

    public void setStarHour(int starHour) {
        this.starHour = starHour;
    }

    public int getStarMinite() {
        return starMinite;
    }

    public void setStarMinite(int starMinite) {
        this.starMinite = starMinite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
