package com.e16din.lightutils.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Handler
import android.util.SparseArray
import android.view.WindowManager
import android.webkit.CookieManager
import com.e16din.topactivity.app


fun app(): Application? = app
fun resources(): Resources? = resources

class U private constructor() : ResourcesUtils() {

    interface SparseForEachCallback<T> {
        fun onValue(value: T, position: Int, key: Int)
    }

    companion object {

        val WRONG_VALUE = -100500

        val handler = Handler()

        val isEmulator: Boolean
            get() = (Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")
                    || Build.MANUFACTURER.contains("Genymotion")
                    || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                    || "google_sdk" == Build.PRODUCT)

        val isGpsEnabled: Boolean
            get() {
                val manager = app?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

                return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            }

        val isOnline: Boolean
            get() {
                val cm = app?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnectedOrConnecting
            }

        fun runIfOnline(isOnline: Boolean, callback: Runnable): Boolean {
            val result = isOnline
            if (result == isOnline)
                callback.run()

            return result
        }

        fun isCallable(context: Context, intent: Intent): Boolean {
            val list = context.packageManager.queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY)
            return list.size > 0
        }

        @JvmOverloads
        fun tryThis(runnable: Runnable, needIgnore: Boolean = true): Boolean {
            if (!needIgnore) {
                runnable.run()
                return true
            }

            try {
                runnable.run()
                return true
            } catch (e: NullPointerException) {
                e.printStackTrace()
            } catch (e: WindowManager.BadTokenException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }

            return false
        }

        fun clearCookie() {
            val cookieManager = CookieManager.getInstance()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.removeAllCookies(null)
            } else {
                cookieManager.removeAllCookie()
            }
        }

        fun <T> forEach(array: SparseArray<T>, callback: SparseForEachCallback<T>) {
            for (i in 0 until array.size()) {
                callback.onValue(array.valueAt(i), i, array.keyAt(i))
            }
        }

        /**
         * Function from Objects(api level 19) for compatibility with old versions
         *
         *
         * Null-safe equivalent of `a.equals(b)`.
         */
        fun equals(a: Any?, b: Any?): Boolean {
            return if (a == null) b == null else a == b
        }

        /**
         * Get activity from context object
         *
         * @param context the context
         * @return object of Activity or null if it is not Activity
         */
        fun scanForActivity(context: Context?): Activity? {
            if (context == null)
                return null
            else if (context is Activity)
                return context
            else if (context is ContextWrapper)
                return scanForActivity(context.baseContext)

            return null
        }
    }
}
