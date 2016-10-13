package com.e16din.lightutils.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.e16din.lightutils.LightUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by e16din on 14.08.15.
 */
public class SocialUtils extends BitmapUtils {
    public static void printKeyHash() {
        final Context context = LightUtils.getInstance().getContext();

        // Add code to print out the key hash
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", context.getPackageName());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //from https://github.com/VKCOM/vk-android-sdk/blob/master/vksdk_library/src/main/java/com/vk/sdk/util/VKUtil.java
    public static String[] printCertificateFingerprint() {
        final Context context = LightUtils.getInstance().getContext();

        try {
            if (context.getPackageManager() == null)
                return null;
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            assert info.signatures != null;
            String[] result = new String[info.signatures.length];
            int index = 0;
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                result[index++] = toHex(md.digest());
            }

            for (int i = 0; i < result.length; i++) {
                final String f = result[i];

                int pos = 0;
                StringBuffer buf = new StringBuffer();
                while (pos < f.length()) {
                    buf.append(f.substring(pos, pos + 2));
                    pos += 2;

                    if (pos < f.length()) {
                        buf.append(":");
                    }
                }

                Log.d("Fingerprint:", f);
                Log.d("Fingerprint:", buf.toString());
            }

            return result;//Base64 packed SHA fingerprint of your packet certificate
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }
}
