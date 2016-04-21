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
import android.support.annotation.NonNull;

import com.e16din.lightutils.LightUtils;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by e16din on 14.08.15.
 */
public class BitmapUtils extends DisplayUtils {

    public static Bitmap getThumbnail(@NonNull Uri uri) throws IOException {
        final Context context = LightUtils.getInstance().getContext();

        InputStream input = context.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;// optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight
                : onlyBoundsOptions.outWidth;

        int size = dpToPx(80);
        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;// optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        input = context.getContentResolver().openInputStream(uri);
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

    public static Bitmap getMaskedBitmap(@NonNull Bitmap bmpSource, @NonNull Bitmap bmpMask) {
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

        if (SdkUtils.hasHoneycombMR1()) {
            bitmap.setHasAlpha(true);
        }
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bmpMask, 0, 0, paint);
        bmpMask.recycle();

        return bitmap;
    }

    public static void loadFrescoBitmap(Context context, String url, BaseBitmapDataSubscriber subscriber) {
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder()
                .build();

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setImageDecodeOptions(decodeOptions)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(request, context);

        dataSource.subscribe(subscriber, CallerThreadExecutor.getInstance());
    }
}
