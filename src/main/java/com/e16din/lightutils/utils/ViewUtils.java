package com.e16din.lightutils.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by e16din on 02.09.15.
 */
public class ViewUtils extends LogUtils {

    public static void hideView(@NonNull View v) {
        v.setVisibility(View.INVISIBLE);
    }

    public static void hideView(@NonNull Activity a, @IdRes int resId) {
        hideView(a.findViewById(resId));
    }

    public static void goneView(@NonNull View v) {
        v.setVisibility(View.GONE);
    }

    public static void goneView(@NonNull Activity a, @IdRes int resId) {
        goneView(a.findViewById(resId));
    }

    public static void showView(@NonNull View v) {
        v.setVisibility(View.VISIBLE);
    }

    public static void showView(@NonNull Activity a, @IdRes int resId) {
        showView(a.findViewById(resId));
    }

    public static void swipeVisibility(@NonNull View v1, @NonNull View v2) {
        int visibility1 = v1.getVisibility();
        int visibility2 = v2.getVisibility();

        v1.setVisibility(visibility2);
        v2.setVisibility(visibility1);
    }

    public static boolean isVisible(@NonNull View v) {
        return v.getVisibility() == View.VISIBLE;
    }

    public static boolean isInvisible(@NonNull View v) {
        return v.getVisibility() == View.INVISIBLE;
    }

    public static boolean isGone(@NonNull View v) {
        return v.getVisibility() == View.GONE;
    }
}
