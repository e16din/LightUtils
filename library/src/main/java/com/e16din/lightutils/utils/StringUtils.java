package com.e16din.lightutils.utils;

import android.content.res.Resources;
import android.text.TextUtils;

import com.e16din.lightutils.LightUtils;
import com.e16din.lightutils.R;

import java.util.Locale;

/**
 * Created by e16din on 14.08.15.
 */
public class StringUtils extends CardUtils {

    public static boolean areEmpty(CharSequence... strings) {
        for (CharSequence string : strings) {
            if (string == null || string.length() == 0)
                return true;
        }
        return false;
    }

    public static boolean isEmpty(CharSequence string) {
        return TextUtils.isEmpty(string);
    }

    public static boolean hasText(CharSequence... strings) {
        for (CharSequence string : strings) {
            if (isEmpty(string))
                return false;
        }
        return true;
    }

    public static boolean areEquals(CharSequence... strings) {
        for (int i = 1; i < strings.length; i++) {
            if (strings[i] == null || !strings[i].equals(strings[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static String getYearsWithPostfix(int age) {
        return age + " " + getYearsPostfix(age);
    }

    public static String getYearsPostfix(int age) {
        Resources res = LightUtils.getResources();
        Locale current = LightUtils.getCurrentLocale();
        final String locale = current.getLanguage();

        if (locale.toUpperCase().equals("RU")) {
            age = age % 100;
            if (age > 10 && age < 20) {
                return res.getString(R.string.years3);
            }//else

            int n = age % 10;
            switch (n) {
                case (1):
                    return res.getString(R.string.years1);
                case (2):
                case (3):
                case (4):
                    return res.getString(R.string.years2);
                default:
                    return res.getString(R.string.years3);
            }
        }//else

        return res.getString(age == 1 ? R.string.years1 : R.string.years2);
    }

    public static int indexAfter(String allText, String value, String after) {
        return allText.indexOf(value, allText.indexOf(after));
    }
}
