package com.summer.lib.network.bean.res;

import java.io.Serializable;

/**
 * 响应实体基类
 * Created by ${viwmox} on 2016-04-27.
 */
public class BaseResBean implements Serializable {

    private Object data;

    private boolean isException;

    private int errorCode;

    private String errorMessage;

    public BaseResBean() {

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "BaseResBean{" +
                "data=" + data +
                ", isException=" + isException +
                ", errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
