package com.e16din.lightutils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.e16din.lightutils.utils.U;

@Deprecated
public class HtmlUpdater {

    public HtmlUpdater(@NonNull TextView tv) {
        U.updateHtml(tv);
    }

    public HtmlUpdater(@NonNull Activity activity, @IdRes int tvId) {
        U.updateHtml(activity, tvId);
    }


}
