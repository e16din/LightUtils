package com.e16din.lightutils;

import android.widget.TextView;

import java.util.List;

/**
 * Created by e16din on 14.08.15.
 */
public final class Get {

    private Get() {
    }

    public static int size(String str) {
        return str == null ? 0 : str.length();
    }

    public static int size(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    public static int size(TextView tv) {
        return tv == null ? 0 : tv.length();
    }

    public static int size(List list) {
        return list == null ? 0 : list.size();
    }

    public static int size(Object[] array) {
        return array == null ? 0 : array.length;
    }

    public static String string(Object obj) {
        if (obj instanceof List) {
            final List list = (List) obj;
            return "Size: " + size(list) + ", " + list;
        } else if (obj instanceof Object[]) {
            final Object[] array = (Object[]) obj;
            return "Size: " + size(array) + ", " + array;
        }

        return obj + "";
    }

    public static String string(TextView tv) {
        return tv == null ? null : tv.getText() + "";
    }

    @Deprecated
    public static int length(CharSequence str) {
        return str.length();
    }

    @Deprecated
    public static int length(TextView tv) {
        return tv.length();
    }
}
