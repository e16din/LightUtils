package com.e16din.lightutils.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.e16din.lightutils.LightUtils;
import com.e16din.lightutils.tools.SimpleTextWatcher;

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

    public static void hideView(Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            hideView(view.findViewById(resId));
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void hideView(android.app.Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            hideView(view.findViewById(resId));
        }
    }

    public static void goneView(View v) {
        v.setVisibility(View.GONE);
    }

    public static void goneView(Activity a, int resId) {
        goneView(a.findViewById(resId));
    }

    public static void goneView(Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            goneView(view.findViewById(resId));
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void goneView(android.app.Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            goneView(view.findViewById(resId));
        }
    }

    public static void showView(View v) {
        v.setVisibility(View.VISIBLE);
    }

    public static void showView(Activity a, int resId) {
        showView(a.findViewById(resId));
    }

    public static void showView(Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            showView(view.findViewById(resId));
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void showView(android.app.Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            showView(view.findViewById(resId));
        }
    }

    public static void showViews(View... views) {
        updateVisibility(View.VISIBLE, views);
    }

    public static void showViews(Activity activity, int... viewIds) {
        updateVisibility(activity, View.VISIBLE, viewIds);
    }

    public static void showViews(View view, int... viewIds) {
        updateVisibility(view, View.VISIBLE, viewIds);
    }

    public static void hideViews(View... views) {
        updateVisibility(View.INVISIBLE, views);
    }

    public static void hideViews(Activity activity, int... viewIds) {
        updateVisibility(activity, View.INVISIBLE, viewIds);
    }

    public static void hideViews(View view, int... viewIds) {
        updateVisibility(view, View.INVISIBLE, viewIds);
    }

    public static void goneViews(View... views) {
        updateVisibility(View.GONE, views);
    }

    public static void goneViews(Activity activity, int... viewIds) {
        updateVisibility(activity, View.GONE, viewIds);
    }

    public static void goneViews(View view, int... viewIds) {
        updateVisibility(view, View.GONE, viewIds);
    }

    public static void updateVisibility(int visibility, View... views) {
        for (View v : views) {
            v.setVisibility(visibility);
        }
    }

    public static void updateVisibility(Activity activity, int visibility, int... viewIds) {
        for (int id : viewIds) {
            activity.findViewById(id).setVisibility(visibility);
        }
    }

    public static void updateVisibility(View view, int visibility, int... viewIds) {
        for (int id : viewIds) {
            view.findViewById(id).setVisibility(visibility);
        }
    }

    public static void updateVisibility(Fragment f, int visibility, int... viewIds) {
        final View view = f.getView();
        if (view == null) return;

        for (int id : viewIds) {
            view.findViewById(id).setVisibility(visibility);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void updateVisibility(android.app.Fragment f, int visibility, int... viewIds) {
        final View view = f.getView();
        if (view == null) return;

        for (int id : viewIds) {
            view.findViewById(id).setVisibility(visibility);
        }
    }

    /**
     * if invisible or gone then update to visible, else update to invisible
     *
     * @param v the view
     */
    public static void swapVisibility(View v) {
        swapVisibility(v, v.getVisibility() == View.INVISIBLE || v.getVisibility() == View.GONE);
    }

    /**
     * Update view visibility
     *
     * @param v         the view
     * @param condition condition to visible
     */
    public static void swapVisibility(View v, boolean condition) {
        v.setVisibility(condition ? View.VISIBLE : View.INVISIBLE);
    }

    public static void swapVisibleInvisible(View v) {
        swapVisibility(v, v.getVisibility() == View.VISIBLE);
    }

    public static void swapVisibleGone(View v) {
        swapVisibleGone(v, v.getVisibility() == View.VISIBLE);
    }

    public static void swapVisibleGone(View v, boolean condition) {
        v.setVisibility(condition ? View.GONE : View.VISIBLE);
    }

    public static void swapVisibility(View v1, View v2) {
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

    public static void setOnClickListener(Fragment f, int viewId,
                                          View.OnClickListener onClickListener) {
        final View view = f.getView();
        if (view == null) return;

        setOnClickListener(view.findViewById(viewId), onClickListener);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void setOnClickListener(android.app.Fragment f, int viewId,
                                          View.OnClickListener onClickListener) {
        final View view = f.getView();
        if (view == null) return;

        setOnClickListener(view.findViewById(viewId), onClickListener);
    }

    public static void updateTint(View view, @ColorInt int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(view.getBackground());
        DrawableCompat.setTint(wrappedDrawable.mutate(), color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(wrappedDrawable);
        } else {
            view.setBackgroundDrawable(wrappedDrawable);
        }
    }

    public static void setHeight(View v, int height) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.height = height;
        v.setLayoutParams(params);
    }

    public static void setWidth(View v, int width) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.width = width;
        v.setLayoutParams(params);
    }

    public static void setSize(View v, int width, int height) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.width = width;
        params.height = height;
        v.setLayoutParams(params);
    }

    /**
     * Get action bar size
     *
     * @return the height of action bar (?actionBarSize value)
     */
    public static int getActionBarSize() {
        final LightUtils lightUtils = LightUtils.getInstance();

        TypedValue value = new TypedValue();

        if (lightUtils.getContext().getTheme().resolveAttribute(
                android.R.attr.actionBarSize, value, true)) {

            return TypedValue.complexToDimensionPixelSize(
                    value.data, lightUtils.getResources().getDisplayMetrics());
        }

        return U.WRONG_VALUE;
    }

    public static void listenOnceOnGlobalLayout(@NonNull final View view, @NonNull final Runnable listener) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                listener.run();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    public static void recursiveLoopChildren(View view, @NonNull LoopChildrenCallback callback) {
        recursiveLoopChildren(view, callback, 0);
    }

    public static void recursiveLoopChildren(View view, @NonNull LoopChildrenCallback callback, int deep) {
        if (view == null) return;

        deep += 1;

        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup) view;
            callback.onChild(null, viewGroup, deep);

            for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
                recursiveLoopChildren(viewGroup.getChildAt(i), callback);
            }
        } else {//if !ViewGroup
            callback.onChild(view, null, deep);
        }
    }

    public static void bindEnableState(@NonNull final View view, @NonNull TextView... vFields) {
        bindEnableState(view, 0, vFields);
    }

    public static void bindEnableState(@NonNull final View view, final int maxInvalidLength, @NonNull final TextView... vFields) {
        final SimpleTextWatcher watcher = new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean hasInvalidValue = hasInvalidLength(vFields, maxInvalidLength);

                view.setEnabled(!hasInvalidValue);
            }
        };

        for (TextView vText : vFields) {
            vText.addTextChangedListener(watcher);
        }
    }

    public static void bindEnableState(@NonNull final View[] views, @NonNull TextView... vFields) {
        bindEnableState(views, 0, vFields);
    }

    public static void bindEnableState(@NonNull final View[] views, final int maxInvalidLength, @NonNull final TextView... vFields) {
        final SimpleTextWatcher watcher = new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean hasInvalidValue = hasInvalidLength(vFields, maxInvalidLength);

                for (View v : views) {
                    v.setEnabled(!hasInvalidValue);
                }
            }
        };

        for (TextView vText : vFields) {
            vText.addTextChangedListener(watcher);
        }
    }


    public static boolean hasInvalidLength(@NonNull TextView[] vFields, int maxInvalidLength) {
        boolean hasInvalidValue = false;
        for (TextView vText : vFields) {
            if (vText.length() <= maxInvalidLength) {
                hasInvalidValue = true;
                break;
            }
        }
        return hasInvalidValue;
    }

    public interface LoopChildrenCallback {
        void onChild(View view, ViewGroup viewGroup, int deep);
    }
}
