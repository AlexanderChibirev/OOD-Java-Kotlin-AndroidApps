package com.example.alexander.testapplication.common.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String getLocateDate(String date, Context context) {
        try {
            //"dd, MMM yyyy hh:mm:ss Z" // date time pattern
            SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
            Date pDate = df.parse(date);
            DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
            DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
            return dateFormat.format(pDate) + " " + timeFormat.format(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
