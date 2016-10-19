package com.e16din.lightutils.utils;

import android.content.res.ColorStateList;

/**
 * Created by e16din on 14.08.15.
 */
public class ColorUtils extends MathUtils {

    public static String rgbToHex(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    public static String argbToHex(int a, int r, int g, int b) {
        return String.format("#%02x%02x%02x%02x", a, r, g, b);
    }

    public static String hexToRgb(int color) {
        int c = (int) Long.parseLong(color + "", 16);
        String r = ((c >> 16) & 0xFF) + "";
        String g = ((c >> 8) & 0xFF) + "";
        String b = ((c) & 0xFF) + "";

        return r + g + b;
    }

    public static ColorStateList createSelector(int normalColor, int pressedColor) {
        return new ColorStateList(
                new int[][]
                        {
                                new int[]{android.R.attr.state_pressed},
                                new int[]{android.R.attr.state_focused},
                                new int[]{android.R.attr.state_activated},
                                new int[]{}
                        },
                new int[]
                        {
                                pressedColor,
                                pressedColor,
                                pressedColor,
                                normalColor
                        }
        );
    }
}
