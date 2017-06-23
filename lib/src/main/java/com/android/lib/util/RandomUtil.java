package com.android.lib.util;

import java.util.Random;

/**
 * 随机数工具
 */
public class RandomUtil {

    private static Random instance;


    public static Random getInstance() {
        if (instance == null) {
            instance = new Random();
        }
        return instance;
    }


}
