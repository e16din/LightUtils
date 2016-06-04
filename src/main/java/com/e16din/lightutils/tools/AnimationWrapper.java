package com.e16din.lightutils.tools;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class AnimationWrapper {

    private static final int DEFAULT_DURATION = 800;

    private View view = null;

    public AnimationWrapper(View view) {
        this.view = view;
    }

    public void changeHeight(final int newHeight) {
        changeHeight(newHeight, null);
    }

    public void changeHeight(final int newHeight, final AnimationListener listener) {
        changeHeight(newHeight, listener, DEFAULT_DURATION);
    }

    public void changeHeight(final int newHeight, final AnimationListener listener, int duration) {
        view.measure(MeasureSpec.makeMeasureSpec(view.getHeight(),
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                newHeight, MeasureSpec.AT_MOST));
        final int initialHeight = view.getHeight();

        view.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {

                view.getLayoutParams().height = (int) (initialHeight + (newHeight - initialHeight)
                        * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setAnimationListener(listener);

        a.setDuration(duration);
        view.startAnimation(a);
    }

    public void animatePacman(int duration, final AnimationListener listener) {
        final boolean isImageView = (view instanceof ImageView);
        Drawable drawable = isImageView ? ((ImageView) view).getDrawable()
                : view.getBackground();
        final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);

        final Paint clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));

        final Canvas canvas = new Canvas(output);
        final RectF rect = new RectF(0, 0, bitmap.getWidth(),
                bitmap.getHeight());
        final Rect rectInt = new Rect();
        rect.round(rectInt);

        if (isImageView)
            ((ImageView) view).setImageBitmap(output);
        else
            view.setBackgroundDrawable(new BitmapDrawable(output));

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                canvas.drawPaint(clearPaint);
                canvas.drawArc(rect, 270, 360 * interpolatedTime, true, paint);
                canvas.drawBitmap(bitmap, rectInt, rect, paint);
            }
        };

        a.setAnimationListener(listener);

        a.setDuration(duration);
        view.startAnimation(a);
    }
}
