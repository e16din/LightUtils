package com.e16din.lightutils;

import android.widget.TextView;

import java.util.List;

/**
 * Created by e16din on 14.08.15.
 */
public class Get {

    private Get() {
    }

    public static int size(Object obj) {
        return size(obj + "");
    }

    public static int size(CharSequence str) {
        return str.length();
    }

    public static int size(TextView tv) {
        return tv.length();
    }

    public static int size(List list) {
        return list.size();
    }

    public static int size(Object[] array) {
        return array.length;
    }


    public static int length(CharSequence str) {
        return str.length();
    }

    public static int length(TextView tv) {
        return tv.length();
    }


    public static String string(Object obj) {

        if (obj instanceof List)
            return "size of list: " + ((List) obj).size();
        if (obj instanceof Object[])
            return "size of array: " + ((Object[]) obj).length;

        return obj.toString();
    }

    public static String string(TextView tv) {
        return tv.getText().toString();
    }
}
