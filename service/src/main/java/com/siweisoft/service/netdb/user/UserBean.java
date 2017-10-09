package com.siweisoft.service.netdb.user;

//by summer on 2017-07-03.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.android.lib.util.NullUtil;
import com.siweisoft.service.netdb.unit.UnitBean;

public class UserBean extends BaseBean {

    private String chatid;

    private int id;

    private String phone;

    private String name;

    private String pwd;

    private Integer usertype;

    private String belong;

    private String headurl = "";

    private float rate;

    private String uuuid;

    private float avg;

    private int unitid;

    private UnitBean unit;

    private int state;


    public static final int CUSTOME = 2;

    public static final int SERVER = 0;

    public static final int ENGINEER = 1;

    public static final int STATE_ONLINE = 1;

    public static final int STATE_OFFLINE = 0;

    public static final int STATE_INVIDEO = 2;

    public static final int USER_TYPE_SERVER = 0;

    public static final int USER_TYPE_ENGINEER = 1;

    public static final int USER_TYPE_CUSTOMER = 2;


    private int pagesize = 5;

    private int pagestart;

    public UserBean() {
    }

    public UserBean(String phone) {
        this.phone = phone;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Bindable
    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    @Bindable
    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    @Bindable
    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    @Bindable
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Bindable
    public String getUuuid() {
        return uuuid;
    }

    public void setUuuid(String uuuid) {
        this.uuuid = uuuid;
    }

    @Bindable
    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public UnitBean getUnit() {
        return unit;
    }

    public void setUnit(UnitBean unit) {
        this.unit = unit;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Bindable
    public String getPhoneOrName() {
        if (NullUtil.isStrEmpty(getName())) {
            return getPhone();
        }
        return getName();
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagestart() {
        return pagestart;
    }

    public void setPagestart(int pagestart) {
        this.pagestart = pagestart;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "chatid='" + chatid + '\'' +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", usertype=" + usertype +
                ", belong='" + belong + '\'' +
                ", headurl='" + headurl + '\'' +
                ", rate=" + rate +
                ", uuuid='" + uuuid + '\'' +
                ", avg=" + avg +
                ", unitid=" + unitid +
                ", unit=" + unit +
                ", state=" + state +
                '}';
    }
}
