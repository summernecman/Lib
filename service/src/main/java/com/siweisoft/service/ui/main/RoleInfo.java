package com.siweisoft.service.ui.main;

import java.io.Serializable;

public class RoleInfo implements Serializable {
    private String mStrName;
    private String mStrUserID;
    private int mRoleIconID;

    public String getName() {
        return mStrName;
    }

    public void setName(String strName) {
        mStrName = strName;
    }

    public String getUserID() {
        return mStrUserID;
    }

    public void setUserID(String strUserID) {
        mStrUserID = strUserID;
    }

    public int getRoleIconID() {
        return mRoleIconID;
    }

    public void setRoleIconID(int resID) {
        mRoleIconID = resID;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "mStrName='" + mStrName + '\'' +
                ", mStrUserID='" + mStrUserID + '\'' +
                ", mRoleIconID=" + mRoleIconID +
                '}';
    }
}