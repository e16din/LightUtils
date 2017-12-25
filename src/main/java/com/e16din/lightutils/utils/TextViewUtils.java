package com.e16din.lightutils.utils;

import android.app.Activity;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
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
        TextView tv = activity.findViewById(tvId);
        if (tv != null)
            tv.setText(HtmlCompat.fromHtml(tv.getText().toString()));
    }

    /**
     * Sets the text with colored substring to your TextView
     *
     * @param vText     the TextView
     * @param fulltext  original string
     * @param substring substring for coloring
     * @param color     color of substring
     */
    public static void setTextWithColoredSpan(TextView vText, String fulltext, String substring, int color) {
        vText.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable spannable = (Spannable) vText.getText();

        int i = fulltext.indexOf(substring);

        spannable.setSpan(new ForegroundColorSpan(color), i, i + substring.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static boolean isEmpty(TextView vText) {
        return vText.length() == 0;
    }

    public static boolean hasText(TextView... views) {
        for (TextView view : views) {
            if (isEmpty(view))
                return false;
        }
        return true;
    }
}
