package com.summer.desktop.util;

//by summer on 2017-05-26.

import java.util.ArrayList;

public class TitleUtil {
    private static TitleUtil instance;

    private ArrayList<String> name = new ArrayList<>();

    public static TitleUtil getInstance() {
        if (instance == null) {
            instance = new TitleUtil();
        }
        return instance;
    }

    public ArrayList<String> getName() {
        return name;
    }
}
