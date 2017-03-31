package com.summer.lib.util.math;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-11-28.
 */
public class MathUtil implements Serializable {

    public static float max(ArrayList<Float> values) {
        float max = values.get(0);
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) > max) {
                max = values.get(i);
            }
        }
        return max;
    }


    public static float min(ArrayList<Float> values) {
        float min = values.get(0);
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) < min) {
                min = values.get(i);
            }
        }
        return min;
    }

}
