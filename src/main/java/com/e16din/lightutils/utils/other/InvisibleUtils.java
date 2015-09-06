package com.e16din.lightutils.utils.other;

/**
 * Created by e16din on 01.09.15.
 */
public class InvisibleUtils {
    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
//    private boolean checkPlayServices() {
//        Log.i("gcm_x", "checkPlayServices");
//        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
//        Log.i("gcm_x", "resultCode: " + resultCode);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
//                        0).show();
//            } else {
//                Log.i("gcm_x", "This device is not supported.");
//                finish();
//            }
//            return false;
//        }
//        Log.i("gcm_x", "resultCode: " + true);
//        return true;
//    }

	/**
     *
     */
    //   public void recursiveLoopChildren(View parent) {
    //     if (parent == null) return;

    //     if (parent instanceof ViewGroup) {
    //         for (int i = ((ViewGroup) parent).getChildCount() - 1; i >= 0; i--) {
    //             final View child = ((ViewGroup) parent).getChildAt(i);

    //             recursiveLoopChildren(child);

    //             child.setOnTouchListener(new View.OnTouchListener() {
    //                 @Override
    //                 public boolean onTouch(View v, MotionEvent event) {
    //                     return !isTouchEnabled;
    //                 }
    //             });
    //         }
    //     } else {
    //         parent.setOnTouchListener(new View.OnTouchListener() {
    //             @Override
    //             public boolean onTouch(View v, MotionEvent event) {
    //                 return !isTouchEnabled;
    //             }
    //         });
    //     }
    // }
}
