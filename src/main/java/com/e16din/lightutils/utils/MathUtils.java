package com.e16din.lightutils.utils;

import java.util.List;

/**
 * Created by e16din on 14.08.15.
 */
public class MathUtils extends StringUtils {

    public static boolean isEven(int x) {
        return (x & 1) == 0;
    }

    public static boolean contains(int from, int to, int value) {
        return value >= from && value <= to;
    }

    public static boolean contains(float from, float to, float value) {
        return value >= from && value <= to;
    }

    public static boolean allAbsValuesSmallerThan(List<Float> values, float thanX) {
        boolean isClick = true;

        for (float value : values) {
            if (Math.abs(value) > thanX) {
                isClick = false;
                break;
            }
        }

        return isClick;
    }
}
