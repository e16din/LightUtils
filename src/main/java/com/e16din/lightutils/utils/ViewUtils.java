package com.e16din.lightutils.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;

/**
 * Created by e16din on 02.09.15.
 */
public class ViewUtils extends SecureUtils {

    public static void hideView(View v) {
        v.setVisibility(View.INVISIBLE);
    }

    public static void hideView(Activity a, int resId) {
        hideView(a.findViewById(resId));
    }

    public static void goneView(View v) {
        v.setVisibility(View.GONE);
    }

    public static void goneView(Activity a, int resId) {
        goneView(a.findViewById(resId));
    }

    public static void showView(View v) {
        v.setVisibility(View.VISIBLE);
    }

    public static void showView(Activity a, int resId) {
        showView(a.findViewById(resId));
    }

    public static void swipeVisibility(View v1, View v2) {
        int visibility1 = v1.getVisibility();
        int visibility2 = v2.getVisibility();

        v1.setVisibility(visibility2);
        v2.setVisibility(visibility1);
    }

    public static boolean isVisible(View v) {
        return v.getVisibility() == View.VISIBLE;
    }

    public static boolean isInvisible(View v) {
        return v.getVisibility() == View.INVISIBLE;
    }

    public static boolean isGone(View v) {
        return v.getVisibility() == View.GONE;
    }


    public static View setOnClickListener(Activity activity,
                                          int viewId,
                                          View.OnClickListener onClickListener) {
        return setOnClickListener(activity.findViewById(viewId), onClickListener);
    }

    public static View setOnClickListener(View v,
                                          int viewId,
                                          View.OnClickListener onClickListener) {
        return setOnClickListener(v.findViewById(viewId), onClickListener);
    }

    public static View setOnClickListener(View v, View.OnClickListener onClickListener) {
        v.setOnClickListener(onClickListener);
        return v;
    }

    public static void updateTint(View view, @ColorInt int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(view.getBackground());
        DrawableCompat.setTint(wrappedDrawable.mutate(), color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(wrappedDrawable);
        }else{
            view.setBackgroundDrawable(wrappedDrawable);
        }
    }
}
