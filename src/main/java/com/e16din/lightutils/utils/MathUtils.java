package com.e16din.lightutils.utils;

/**
 * Created by e16din on 14.08.15.
 */
public class MathUtils extends StringUtils {

    public static boolean isEven(int x) {
        return (x & 1) == 0;
    }
}
