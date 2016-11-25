package com.e16din.lightutils.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;

import com.e16din.lightutils.LightUtils;
import com.e16din.lightutils.model.Size;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by e16din on 14.08.15.
 */
public class DisplayUtils extends ColorUtils {

    public static float pxToSp(float px) {
        final Context context = LightUtils.getContext();

        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    public static float pxToMm(final float px) {
        final Context context = LightUtils.getContext();

        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return px / TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 1, dm);
    }

    public static int dpToPx(final int dp) {
        return (int) dpToPxF(dp);
    }

    public static int spToPx(final int sp) {
        final Context context = LightUtils.getContext();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static float dpToPxF(final float dp) {
        final Context context = LightUtils.getContext();

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float pxToDpF(final float px) {
        final Context context = LightUtils.getContext();

        return px / context.getResources().getDisplayMetrics().density;
    }

    private int pxToDp(final float px) {
        return (int) pxToDpF(px);
    }

    public static void setElevation(final View view, final int levelPx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(levelPx);
        }
    }

    /**
     * Method is deprecated please use getScreenSize method
     */
    @Deprecated
    public static Size getScreenRealSize(Display display) {
        return getScreenSize(display);
    }

    public static Size getScreenSize(@NonNull Activity activity) {
        return getScreenSize(activity.getWindowManager().getDefaultDisplay());
    }

    public static Size getScreenSize(@NonNull Display display) {
        int realWidth;
        int realHeight;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //new pleasant way to get real metrics
            DisplayMetrics realMetrics = new DisplayMetrics();
            display.getRealMetrics(realMetrics);
            realWidth = realMetrics.widthPixels;
            realHeight = realMetrics.heightPixels;

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            //reflection for this weird in-between time
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e) {
                //this may not be 100% accurate, but it's all we've got
                realWidth = display.getWidth();
                realHeight = display.getHeight();
            }

        } else {
            //This should be close, as lower API devices should not have window navigation bars
            realWidth = display.getWidth();
            realHeight = display.getHeight();
        }

        return new Size(realWidth, realHeight);
    }


    public static Bitmap takeScreenshot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Drawable backgroundDrawable = view.getBackground();
        if (backgroundDrawable != null) {
            backgroundDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);

        return bitmap;
    }
}
