package com.example.alexander.testapplication.common.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final String DATE_TIME_FORMATTER_PATTERN = "EEE, dd MMM yyyy kk:mm:ss Z";

    public static String getLocateDate(String date, Context context) {
        try {
            //"dd, MMM yyyy hh:mm:ss Z" // date time pattern
            SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMATTER_PATTERN, Locale.ENGLISH);
            Date pDate = df.parse(date);
            DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
            DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
            return dateFormat.format(pDate) + " " + timeFormat.format(pDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
