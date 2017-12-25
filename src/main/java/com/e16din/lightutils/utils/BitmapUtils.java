package com.e16din.lightutils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Build;

import java.io.IOException;
import java.io.InputStream;

import static com.e16din.topactivity.TopActivityKt.app;

/**
 * Created by e16din on 14.08.15.
 */
public class BitmapUtils extends DisplayUtils {

    public static Bitmap getThumbnail(Uri uri, int sizeDp) throws IOException, NullPointerException {
        final Context context = app();

        InputStream input = context.getContentResolver().openInputStream(uri);
        assert input != null;

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;// optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth)
                ? onlyBoundsOptions.outHeight
                : onlyBoundsOptions.outWidth;

        double size = dpToPx(sizeDp);
        double ratio = (originalSize > size) ? (originalSize / size) : 1;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;// optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional

        input = context.getContentResolver().openInputStream(uri);
        assert input != null;

        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0)
            return 1;
        else
            return k;
    }

    public static Bitmap getMaskedBitmap(Bitmap bmpSource, Bitmap bmpMask) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            options.inMutable = true;
        }

        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bitmap;
        if (bmpSource.isMutable()) {
            bitmap = bmpSource;
        } else {
            bitmap = bmpSource.copy(Bitmap.Config.ARGB_8888, true);
            bmpSource.recycle();
        }

        if (DeviceUtils.hasHoneycombMR1()) {
            bitmap.setHasAlpha(true);
        }
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bmpMask, 0, 0, paint);
        bmpMask.recycle();

        return bitmap;
    }
}
