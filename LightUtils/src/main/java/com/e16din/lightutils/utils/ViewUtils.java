package com.e16din.lightutils.utils;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.e16din.lightutils.tools.SimpleTextWatcher;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.e16din.lightutils.utils.UKt.app;
import static com.e16din.lightutils.utils.UKt.resources;

/**
 * Created by e16din on 02.09.15.
 */
public class ViewUtils extends SecureUtils {

    public static final int INVALID_VALUE = -1;

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in setId().
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static void hideView(Activity a, int resId) {
        hide(a.findViewById(resId));
    }

    public static void hideView(Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            hide(view.findViewById(resId));
        }
    }

    public static void gone(View v) {
        v.setVisibility(View.GONE);
    }

    public static void gone(Activity a, int resId) {
        gone(a.findViewById(resId));
    }

    public static void gone(Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            gone(view.findViewById(resId));
        }
    }

    public static void show(View v) {
        v.setVisibility(View.VISIBLE);
    }

    public static void show(Activity a, int resId) {
        show(a.findViewById(resId));
    }

    public static void show(Fragment f, int resId) {
        final View view = f.getView();
        if (view != null) {
            show(view.findViewById(resId));
        }
    }

    public static void show(View... views) {
        updateVisibility(View.VISIBLE, views);
    }

    public static void show(Activity activity, int... viewIds) {
        updateVisibility(activity, View.VISIBLE, viewIds);
    }

    public static void show(View view, int... viewIds) {
        updateVisibility(view, View.VISIBLE, viewIds);
    }

    public static void hide(View... views) {
        updateVisibility(View.INVISIBLE, views);
    }

    public static void hide(Activity activity, int... viewIds) {
        updateVisibility(activity, View.INVISIBLE, viewIds);
    }

    public static void hide(View view, int... viewIds) {
        updateVisibility(view, View.INVISIBLE, viewIds);
    }

    public static void hide(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public static void gone(View... views) {
        updateVisibility(View.GONE, views);
    }

    public static void gone(Activity activity, int... viewIds) {
        updateVisibility(activity, View.GONE, viewIds);
    }

    public static void gone(View view, int... viewIds) {
        updateVisibility(view, View.GONE, viewIds);
    }

    public static void updateVisibility(int visibility, View... views) {
        if (views == null) return;

        for (View v : views) {
            v.setVisibility(visibility);
        }
    }

    public static void updateVisibility(Activity activity, int visibility, int... viewIds) {
        if (viewIds == null) return;

        for (int id : viewIds) {
            activity.findViewById(id).setVisibility(visibility);
        }
    }

    public static void updateVisibility(View view, int visibility, int... viewIds) {
        if (viewIds == null) return;

        for (int id : viewIds) {
            view.findViewById(id).setVisibility(visibility);
        }
    }

    public static void updateVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }


    public static void updateVisibility(Fragment f, int visibility, int... viewIds) {
        final View view = f.getView();
        if (view == null) return;
        if (viewIds == null) return;

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

    public static void gone(View v, boolean condition) {
        if (condition) gone(v);
    }

    public static void show(View v, boolean condition) {
        if (condition) show(v);
    }

    public static void hide(View v, boolean condition) {
        if (condition) hide(v);
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

    public static boolean areVisible(View... views) {
        for (View v : views) {
            if (v.getVisibility() != View.VISIBLE) {
                return false;
            }
        }

        return true;
    }

    public static boolean isInvisible(View v) {
        return v.getVisibility() == View.INVISIBLE;
    }

    public static boolean areInvisible(View... views) {
        for (View v : views) {
            if (v.getVisibility() != View.INVISIBLE) {
                return false;
            }
        }

        return true;
    }

    public static boolean isGone(View v) {
        return v.getVisibility() == View.GONE;
    }

    public static boolean areGone(View... views) {
        for (View v : views) {
            if (v.getVisibility() != View.GONE) {
                return false;
            }
        }

        return true;
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

//    public static void updateTint(View view, int color) {
//        Drawable wrappedDrawable = DrawableCompat.wrap(view.getBackground());
//        DrawableCompat.setTint(wrappedDrawable.mutate(), color);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            view.setBackground(wrappedDrawable);
//        } else {
//            view.setBackgroundDrawable(wrappedDrawable);
//        }
//    }

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
        TypedValue value = new TypedValue();

        if (app().getTheme().resolveAttribute(
                android.R.attr.actionBarSize, value, true)) {

            return TypedValue.complexToDimensionPixelSize(
                    value.data, resources().getDisplayMetrics());
        }

        return U.Companion.getWRONG_VALUE();
    }

    public static void listenOnceOnGlobalLayout(final View view, final Runnable listener) {
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

    public static void recursiveLoopChildren(View view, LoopChildrenCallback callback) {
        recursiveLoopChildren(view, callback, 0);
    }

    public static void recursiveLoopChildren(View view, LoopChildrenCallback callback, int deep) {
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

    public static void bindEnableState(final View view, TextView... vFields) {
        bindEnableState(view, 0, vFields);
    }

    public static void bindEnableState(final View view, final int maxInvalidLength, final TextView... vFields) {
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

    public static void bindEnableState(final View[] views, TextView... vFields) {
        bindEnableState(views, 0, vFields);
    }

    public static void bindEnableState(final View[] views, final int maxInvalidLength, final TextView... vFields) {
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


    public static boolean hasInvalidLength(TextView[] vFields, int maxInvalidLength) {
        boolean hasInvalidValue = false;
        for (TextView vText : vFields) {
            if (vText.length() <= maxInvalidLength) {
                hasInvalidValue = true;
                break;
            }
        }
        return hasInvalidValue;
    }

    public static int getClickedViewId(int[] viewsArray, ViewGroup view, MotionEvent e) {
        for (int viewId : viewsArray) {
            if (isViewClicked(viewId, view, e)) {
                return viewId;
            }
        }

        return INVALID_VALUE;
    }

    public static int getClickedViewId(List<Integer> viewsList, ViewGroup view, MotionEvent e) {
        for (int viewId : viewsList) {
            if (isViewClicked(viewId, view, e)) {
                return viewId;
            }
        }

        return INVALID_VALUE;
    }

    public static boolean isViewClicked(int viewId, ViewGroup vParent, MotionEvent e) {
        Rect rect = new Rect();
        int x = (int) e.getRawX();
        int y = (int) e.getRawY();

        vParent.findViewById(viewId).getGlobalVisibleRect(rect);

        return rect.contains(x, y);
    }

    public static void setPaddingTop(View v, int padding) {
        v.setPadding(v.getPaddingLeft(), padding, v.getPaddingRight(), v.getPaddingBottom());
    }

    public static void setPaddingBottom(View v, int padding) {
        v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), padding);
    }

    public static void setPaddingLeft(View v, int padding) {
        v.setPadding(padding, v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom());
    }

    public static void setPaddingRight(View v, int padding) {
        v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), padding, v.getPaddingBottom());
    }


    public interface LoopChildrenCallback {
        void onChild(View view, ViewGroup viewGroup, int deep);
    }
}
