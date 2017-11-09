package com.android.lib.network.bean.res;

import java.io.Serializable;

/**
 * 响应实体基类
 * Created by ${viwmox} on 2016-04-27.
 */
public class BaseResBean implements Serializable {

    private Object other;

    private boolean isException = false;

    private int errorCode = 0;

    private String errorMessage;

    private Object data;

    public BaseResBean() {

    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isException() {
        return isException;
    }

    public void setException(boolean exception) {
        isException = exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "BaseResBean{" +
                "other=" + other +
                ", isException=" + isException +
                ", errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
