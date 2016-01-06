package com.e16din.lightutils.utils;

/**
 * Created by e16din on 14.08.15.
 */
public class ColorUtils extends MathUtils {

    public static String hexToRgb(int color) {
        int c = (int) Long.parseLong(color + "", 16);
        String r = ((c >> 16) & 0xFF) + "";
        String g = ((c >> 8) & 0xFF) + "";
        String b = ((c >> 0) & 0xFF) + "";

        return r + g + b;
    }
}
