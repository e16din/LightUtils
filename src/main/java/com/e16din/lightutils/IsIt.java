package com.e16din.lightutils;

import android.support.annotation.NonNull;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by e16din on 02.09.15.
 */
public final class IsIt {
    private IsIt() {
    }

    public static boolean phone(@NonNull CharSequence s, int countryId) {
        switch (countryId) {
            case Countries.ID_RUSSIA:
                //todo
                break;
            case Countries.ID_NONE:
            default:
                Pattern pattern = Patterns.PHONE;
                return pattern.matcher(s).matches();
        }

        return false;
    }

    public static boolean phone(@NonNull CharSequence s) {
        return phone(s, Countries.ID_NONE);
    }

    public static boolean email(@NonNull CharSequence s) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(s).matches();
    }

    public static boolean number(@NonNull String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean floatNumber(@NonNull String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean nil(Object obj) {
        return obj == null;
    }
}
