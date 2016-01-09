package com.e16din.lightutils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.Html;
import android.widget.TextView;

public class HtmlUpdater {

    public HtmlUpdater(@NonNull TextView tv) {
        if (tv != null)
            tv.setText(Html.fromHtml(tv.getText().toString()));
    }

    public HtmlUpdater(@NonNull Activity activity, @IdRes int tvId) {
        TextView tv = (TextView) activity.findViewById(tvId);
        if (tv != null)
            tv.setText(Html.fromHtml(tv.getText().toString()));
    }
}
