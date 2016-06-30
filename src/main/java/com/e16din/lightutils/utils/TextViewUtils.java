package com.e16din.lightutils.utils;

import android.app.Activity;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by e16din on 14.08.15.
 */
public class TextViewUtils extends DeviceUtils {

    @Deprecated
    public static void updateHtml(TextView tv) {
        tv.setText(Html.fromHtml(tv.getText().toString()));
    }

    @Deprecated
    public static void updateHtml(Activity activity, int tvId) {
        TextView tv = (TextView) activity.findViewById(tvId);
        if (tv != null)
            tv.setText(Html.fromHtml(tv.getText().toString()));
    }
}
