package com.e16din.lightutils.utils;

import android.app.Activity;
import android.widget.TextView;

import com.e16din.lightutils.compat.HtmlCompat;

/**
 * Created by e16din on 14.08.15.
 */
public class TextViewUtils extends DeviceUtils {

    public static void updateHtml(TextView tv) {
        tv.setText(HtmlCompat.fromHtml(tv.getText().toString()));
    }

    public static void updateHtml(Activity activity, int tvId) {
        TextView tv = (TextView) activity.findViewById(tvId);
        if (tv != null)
            tv.setText(HtmlCompat.fromHtml(tv.getText().toString()));
    }
}
