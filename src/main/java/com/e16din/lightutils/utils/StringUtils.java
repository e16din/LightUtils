package com.e16din.lightutils.utils;

/**
 * Created by e16din on 14.08.15.
 */
public class StringUtils extends CardUtils{

    public static boolean areEmpty(CharSequence... strings) {
        for (CharSequence string : strings) {
            if (string == null || string.length() == 0)
                return true;
        }
        return false;
    }

    public static boolean areEquals(CharSequence... strings) {
        for (int i = 1; i < strings.length; i++) {
            if (strings[i] == null || !strings[i].equals(strings[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
