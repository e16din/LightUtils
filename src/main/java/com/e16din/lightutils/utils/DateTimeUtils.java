package com.e16din.lightutils.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Dmitry Ikryanov
 */
public class DateTimeUtils extends SocialUtils {

    public static final String FORMAT_HH_MM = "HH:mm";
    public static final String FORMAT_DD_MMMM_YYYY = "dd MMMM yyyy";
    public static final String FORMAT_ISO6801 = "yyyy-MM-dd'T'HH:mm:ss";

    public static final DateTimeZone LOCAL_TIME_ZONE = DateTimeZone.forTimeZone(TimeZone.getDefault());
    public static final DateTimeZone UTC_TIME_ZONE = DateTimeZone.UTC;

    public static final DateTimeFormatter UTC_TIME_DATE_PARSER = new DateTimeFormatterBuilder()
            .append(DateTimeFormat.forPattern(FORMAT_ISO6801))
            .toFormatter()
            .withZone(UTC_TIME_ZONE);

    public static String nowUtcIso() {
        return formatISO(DateTime.now(UTC_TIME_ZONE));
    }

    public static String nowLocalIso() {
        return formatISO(DateTime.now(LOCAL_TIME_ZONE));
    }

    public static Date parse(String dateTime) {
        return DateTime.parse(dateTime).toDate();
    }

    // [HH:mm]
    public static String formatTime(String dateTime) {
        return formatTime(fromString(dateTime));
    }

    public static String formatTime(Date date) {
        return formatTime(fromLong(date.getTime()));
    }

    public static String formatEndTime(String startTime, int durationMinutes) {
        return formatTime(fromString(startTime).plusMinutes(durationMinutes));
    }

    // [HH:mm - HH:mm]
    public static String formatStartEndTime(String dateTime, int durationMinutes) {
        return formatStartEndTime(fromString(dateTime), durationMinutes);
    }

    // [DD MMMM YYYY]
    public static String formatFullDate(Date date) {
        return formatFullDate(fromLong(date.getTime()));
    }

    public static String formatFullDate(String dateTime) {
        return formatFullDate(fromString(dateTime));
    }

    // [ISO]
    public static String formatISO(Date date) {
        return formatISO(fromLong(date.getTime()));
    }

    // [Time Zones]
    public static String toLocalTimeISO(String dateTime) {
        return formatISO(UTC_TIME_DATE_PARSER.parseDateTime(dateTime).withZone(LOCAL_TIME_ZONE));
    }

    public static String toUtcTimeISO(String dateTime) {
        return formatISO(fromString(dateTime).withZone(UTC_TIME_ZONE));
    }



    // [Private methods]
    private static String formatTime(DateTime dateTime) {
        return dateTime.toString(FORMAT_HH_MM);
    }

    private static String formatFullDate(DateTime dateTime) {
        return dateTime.toString(FORMAT_DD_MMMM_YYYY);
    }

    private static String formatISO(DateTime dateTime) {
        return dateTime.toString(FORMAT_ISO6801);
    }

    private static String formatStartEndTime(DateTime dateTime, int durationMinutes) {
        return formatTime(dateTime) + " - " + formatTime(dateTime.plusMinutes(durationMinutes));
    }


    private static DateTime fromLong(long time) {
        return new DateTime(time);
    }

    private static DateTime fromString(String date) {
        return DateTime.parse(date);
    }
}
