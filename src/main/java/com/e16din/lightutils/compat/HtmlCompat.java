package com.e16din.lightutils.compat;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

public class HtmlCompat {

    public static Spanned fromHtml(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

    public static Spanned fromHtml(String html, Html.ImageGetter imageGetter,
                                   Html.TagHandler tagHandler) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, imageGetter, tagHandler);
        } else {
            return Html.fromHtml(html, imageGetter, tagHandler);
        }
    }
}
