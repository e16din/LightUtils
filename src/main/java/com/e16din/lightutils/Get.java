package com.e16din.lightutils;

import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

/**
 * Created by e16din on 14.08.15.
 */
public final class Get {

    private Get() {
    }

    public static int size(@NonNull Object obj) {
        return size(obj + "");
    }

    public static int size(@NonNull CharSequence str) {
        return str.length();
    }

    public static int size(@NonNull TextView tv) {
        return tv.length();
    }

    public static int size(@NonNull List list) {
        return list.size();
    }

    public static int size(@NonNull Object[] array) {
        return array.length;
    }


    public static int length(@NonNull CharSequence str) {
        return str.length();
    }

    public static int length(@NonNull TextView tv) {
        return tv.length();
    }


    public static String string(@NonNull Object obj) {

        if (obj instanceof List)
            return "size of list: " + ((List) obj).size();
        if (obj instanceof Object[])
            return "size of array: " + ((Object[]) obj).length;

        return obj.toString();
    }

    public static String string(@NonNull TextView tv) {
        return tv.getText().toString();
    }
}
