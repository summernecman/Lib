package com.android.lib.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.lib.constant.ValueConstant;


/**
 * sharedpreferences的本地xml数据库工具
 */
public class SPUtil {

    private static SPUtil instance;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    public static SPUtil getInstance() {
        if (instance == null) {
            instance = new SPUtil();
        }
        return instance;
    }

    /**
     * 初始化本类
     *
     * @param context
     * @return
     */
    public SPUtil init(Context context) {
        sharedPreferences = context.getSharedPreferences(ValueConstant.ACCOUT_SHAREPRE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return instance;
    }

    /**
     * 保存账号
     *
     * @param account
     */
    public void saveAccount(String account) {
        editor.putString(ValueConstant.ACCOUT, account);
        editor.commit();
    }


    public String getAccount() {
        return sharedPreferences.getString(ValueConstant.ACCOUT, "");

    }

    /***
     * 保存字符串
     * @param strKey
     * @param strValue
     */
    public void saveStr(final String strKey, String strValue) {
        editor.putString(strKey, strValue);
        editor.commit();
    }


    /**
     * 获取string 类型数据
     *
     * @param strKey
     * @return
     */
    public String getStr(final String strKey) {
        return sharedPreferences.getString(strKey, "");

    }

    public void saveInt(final String Key, int Value) {
        editor.putInt(Key, Value);
        editor.commit();
    }

    /**
     * 获取int 类型数据
     *
     * @param Key
     * @return
     */
    public int getInt(final String Key) {
        return sharedPreferences.getInt(Key, 0);

    }


    public void saveBoolean(final String Key, boolean Value) {
        editor.putBoolean(Key, Value);
        editor.commit();
    }

    /**
     * 获取boolean 类型数据
     *
     * @param Key
     * @return
     */
    public boolean getBoolean(final String Key) {
        return sharedPreferences.getBoolean(Key, false);

    }


}
