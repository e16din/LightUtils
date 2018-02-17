package com.e16din.lightutils.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.provider.Settings
import android.telephony.TelephonyManager
import com.e16din.topactivity.app
import java.util.*

/**
 * Created by e16din on 14.08.15.
 */
open class IdUtils : SocialUtils() {
    companion object {

        val imei: String
            get() {
                val tm = app?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                return tm.deviceId
            }

        // for android sdk >= 9
        val udid: String
            get() = Settings.Secure.getString(app?.contentResolver, Settings.Secure.ANDROID_ID)

        val packageVersionName: String
            @Throws(PackageManager.NameNotFoundException::class)
            get() = packageInfo.versionName

        val versionCode: Int
            @Throws(PackageManager.NameNotFoundException::class)
            get() = packageInfo.versionCode

        val packageInfo: PackageInfo
            @Throws(PackageManager.NameNotFoundException::class)
            get() {
                val context = app!!
                return context.packageManager.getPackageInfo(context.packageName, 0)
            }

        fun getDevGuid(activity: Activity): String {
            val tm = activity.baseContext
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            val tmDevice = "" + tm.deviceId
            val tmSerial = "" + tm.simSerialNumber
            val androidId = "" + android.provider.Settings.Secure.getString(activity.contentResolver,
                    android.provider.Settings.Secure.ANDROID_ID)

            val deviceUuid = UUID(androidId.hashCode().toLong(),
                    tmDevice.hashCode().toLong() shl 32 or tmSerial.hashCode().toLong())

            return deviceUuid.toString()
        }

        fun getAppGuid(activity: Activity): String {
            val androidId = "" + android.provider.Settings.Secure.getString(activity.contentResolver,
                    android.provider.Settings.Secure.ANDROID_ID)

            val deviceUuid = UUID(androidId.hashCode().toLong(), System.currentTimeMillis())

            return deviceUuid.toString()
        }

        fun randomUUID(): String {
            return UUID.randomUUID().toString()
        }
    }
}
