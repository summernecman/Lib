package com.summer.lib.util;

import java.util.Random;

/**
 * Created by ${viwmox} on 2016-10-20.
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
