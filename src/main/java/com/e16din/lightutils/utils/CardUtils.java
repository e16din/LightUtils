package com.e16din.lightutils.utils;

import java.util.regex.Pattern;

/**
 * Created by e16din on 14.08.15.
 */
public class CardUtils extends ViewUtils {

    //LUHN Formula (mod10)
    public static boolean validateCardNumber(String n) {
        n = n.replace(" ", "");
        int j = n.length();

        String[] s1 = new String[j];
        for (int i = 0; i < n.length(); i++) s1[i] = "" + n.charAt(i);

        int checksum = 0;

        for (int i = s1.length - 1; i >= 0; i -= 2) {
            int k;

            if (i > 0) {
                k = Integer.parseInt(s1[i - 1]) * 2;
                if (k > 9) {
                    String s = "" + k;
                    k = Integer.parseInt(s.substring(0, 1)) + Integer.parseInt(s.substring(1));
                }
                checksum += Integer.parseInt(s1[i]) + k;
            } else
                checksum += Integer.parseInt(s1[0]);
        }

        return ((checksum % 10) == 0);
    }

    //from http://stackoverflow.com/a/23814692
    public enum CardType {
        UNKNOWN,
        VISA("^4[0-9]{12}(?:[0-9]{3})?$"),
        MASTERCARD("^5[1-5][0-9]{14}$"),
        AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
        DINERS_CLUB("^3(?:0[0-5]|[68][0-9])[0-9]{11}$"),
        DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$"),
        JCB("^(?:2131|1800|35\\d{3})\\d{11}$");

        private Pattern pattern;

        CardType() {
            this.pattern = null;
        }

        CardType(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        public static CardType detect(String cardNumber) {

            for (CardType cardType : CardType.values()) {
                if (null == cardType.pattern) continue;
                if (cardType.pattern.matcher(cardNumber).matches()) return cardType;
            }

            return UNKNOWN;
        }
    }
}
