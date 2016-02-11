package com.e16din.lightutils.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by e16din on 14.08.15.
 */
public class TextViewUtils extends SdkUtils {

    public static void updateHtml(@NonNull TextView tv) {
        tv.setText(Html.fromHtml(tv.getText().toString()));
    }

    public static void updateHtml(@NonNull Activity activity, @IdRes int tvId) {
        TextView tv = (TextView) activity.findViewById(tvId);
        if (tv != null)
            tv.setText(Html.fromHtml(tv.getText().toString()));
    }
}
